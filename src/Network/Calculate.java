package Network;

import Functions.*;

public class Calculate {
	// instance variables
	private float[] outputArray;
	private float[][] weight_1;
	private float[] weight_2;
	private float[] functionBias;
	private float output;
	private int nodes; //neurons

	// constructor that initializes the variables
	public Calculate(float[] row, float[][] weight_1, float[] bias, float[] weight_2, int nodes, int functionType) {
		this.outputArray = new float[nodes];
		this.weight_1 = weight_1;
		this.functionBias = bias;
		this.weight_2 = weight_2;
		this.nodes = nodes;
		this.output = calcOutput(row, functionType);
	}

	// calculates the output of the network
	public float calcOutput(float[] rowValues, int functionType) {
		/*
		 * Default transfer function. Instead of Sigmoid, user can choose to select
		 * HyperbolicTangent or Linear. Depends on user preferences. This network is
		 * most effectively configured to Sigmoid (range 0 to 1). If you change this to
		 * another relationship, all number bounds and references throughout the project
		 * must be updated. If changed, check out this link on the different functions:
		 * https://stevenmiller888.github.io/mind-how-to-build-a-neural-network/
		 */
		ActivationImplementation function;
		if(functionType == 1) {
			function = new HyperbolicTangent();
		}
		else {
			function = new Sigmoid();
		}
			
		// gives the value of the input with only weight_1
		float sum;
		output = 0;
		for (int i = 0; i < nodes; i++) {
			sum = 0;
			for (int j = 0; j < nodes; j++) {
				sum += (rowValues[j] * weight_1[j][i]);
			}
			// adds the bias to the summation
			double x = (double) sum + functionBias[i];
			outputArray[i] = function.activate(x);
		}

		// all of the values are affected by weight_2
		for (int i = 0; i < nodes; i++) {
			output += outputArray[i] * weight_2[i];
		}
		
		// the Sigmoid function will return a value from 0 to 1
		// the Hyperbilic Tangent function will return a value from -1 to 1
		return function.activate(output);
	}

	// gets the outputArray
	public float[] getOutputArray() {
		return outputArray;
	}

	// gets the output
	public float getOutput() {
		return output;
	}
}
