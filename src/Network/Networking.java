package Network;

import ResultManager.BinaryInterpreter;
import ResultManager.PredictOutput;
import Utils.InputError;

// main class of the network
// does most of the work
// learns from the input data and generates a result
public class Networking {
	// instance variables
	private int outputSize;
	private int dimension;
	private float[][] inputs;
	private int[] outputData;
	private float[] bias;
	private float[][] weight_1;
	private float[] weight_2;
	private float calculateOutput;
	private int neurons;
	private float successPercentage;
	// initializes program iterations and the success counter
	private int iterations = 12345;
	private int successCount = 0;

	// creates an instance of different classes
	private Calculate calculate;
	private BackwardPropagation bp;
	private BinaryInterpreter bi;
	@SuppressWarnings("unused")
	private int functionType;
	
	// constructor that initializes variables based on sample data and sample results
	public Networking(float[][] inputs, int[] output, int functionType) {
		/*
		 * Default transfer function. Instead of Sigmoid, user can choose to select
		 * HyperbolicTangent or Linear. Depends on user preferences. This network is
		 * most effectively configured to Sigmoid (range 0 to 1). If you change this to
		 * another relationship, all number bounds and references throughout the project
		 * must be updated. If changed, check out this link on the different functions:
		 * https://stevenmiller888.github.io/mind-how-to-build-a-neural-network/
		 */

		// program uses ones and zeroes to denotes the outcome
		// rules for what determines a 1 or 0 can be seen in the BinaryInterpreter class
		this.bi = new BinaryInterpreter(functionType);

		// initializing variables
		this.inputs = inputs;
		this.outputData = output;
		this.outputSize = output.length;

		// checks to see if there are any data values even present in the user input
		try {
			// dimension is the number of inputs in each row
			dimension = inputs[0].length;
		} catch (ArrayIndexOutOfBoundsException e) {
			InputError.message("There are no input values given.");
		}
		
		// neurons are the nodes of the program that adjust for bias and weights
		// initially there is one for each input which is why its the same as the dimension at first
		neurons = dimension;
	}

	// starts the learning process on a new thread
	public void networkLearning() {

		// checks to see if the sample inputs and results are valid 
		if (inputs.length != outputData.length) {
			InputError.message("Number of sample inputs isn't the same.");
		}
		if (dimension == 0) {
			InputError.message("There is no sample input data.");
		}

		else {
			// uses the HiddenNeuron class to initialize the different weights and the bias
			HiddenNeurons hiddenNeurons = new HiddenNeurons(neurons);
			this.bias = hiddenNeurons.getBias();
			this.weight_1 = hiddenNeurons.getWeight_1();
			this.weight_2 = hiddenNeurons.getWeight_2();

			// runs the thread
			new CalculationThread().run();
		}

	}

	// gets the value of the number located in the row
	private float[] getRow(int row) {
		// testCase will store the value of one row in the input array in a 1D array
		float[] testCase = new float[dimension];
		for (int i = 0; i < dimension; i++) {
			testCase[i] = this.inputs[row][i];
		}
		return testCase;
	}
	
	// gets the number of nodes
	public int getNeurons() {
		return neurons;
	}

	// gets the number of iterations
	public int getIterations() {
		return iterations;
	}

	// makes the network correct itself on another thread
	public class CalculationThread implements Runnable {
		@Override
		public void run() {
			float[] outputArray;
			// runs the numbers again and again until it reaches the iteration limit
			for (int i = 0; i <= iterations; i++) {
				successCount = 0;
				for (int j = 0; j < outputSize; j++) {
					// creates new instance of Calculate class to show the output for each number in the sample inputs
					// goes through every row in the sample inputs
					calculate = new Calculate(getRow(j), weight_1, bias, weight_2, neurons, functionType);
					// uses the above class to assign a value to the the output and outputArray
					outputArray = calculate.getOutputArray();
					calculateOutput = calculate.getOutput();
					// creates new instance of BackwardPropagation class to go back and correct the error as much as possible
					// goes through every row in the sample inputs
					bp = new BackwardPropagation(outputData[j], calculateOutput, outputArray, weight_2, weight_1, bias,
							neurons, getRow(j), functionType);
					/* Below, the values of the variables are only used for the final iteration. 
					 * This gives the final weight and bias amounts for the final iteration.
					 */
					weight_2 = bp.getWeight_2();
					weight_1 = bp.getWeight_1();
					bias = bp.getBias();
					
					/*Checks to see if the generated output is correct when compared to the sample results.
					 * This gives the final successCount for the final iteration.
					 */
					
					successCount = bi.countSuccess(successCount, calculateOutput, outputData[j]);

				}

			}
			// calculates the percentage of successes in the final iteration
			successPercentage = (successCount / (float) outputSize) * 100;
			
			// sets the successPercentage in the PredictOutput class
			PredictOutput po = new PredictOutput();
			po.setSuccessfulChance(successPercentage);
		}
	}

	// gets the successPercentage
	public float getSuccessPercentage() {
		return successPercentage;
	}
	
	// gets the Calculate object
	public Calculate getCalculate() {
		return calculate;
	}

	// gets the dimension value
	public int getDimension() {
		return dimension;
	}
}
