package Network;

public class BackwardPropagation {
	// instance variables
	private float[] functionBias;
	private float[][] weight_1;
	private float[] weight_2;

	// constructor initializes the variables
	public BackwardPropagation(float result, float output, float[] outputArray, float[] weight_2, float[][] weight_1,
			float[] bias, int nodes, float[] data, int functionType) {
		this.functionBias = new float[nodes];
		this.weight_2 = new float[nodes];
		this.weight_1 = new float[nodes][nodes];

		// calls upon the fix method to go back and change the network
		fix(result, output, outputArray, weight_2, weight_1, bias, nodes, data, functionType);
	}
	
	// alters the network if there was something wrong
	private void fix(float result, float output, float[] outputArray, float[] weight_2, float[][] weight_1,
			float[] bias, int nodes, float[] data, int functionType) {
		
		// initializes variables (tell how much they were wrong by)
		// this way, the program can change the values so that it will now work for the given data row
		float marginOfError = result - output;

		float[] deltaWeight_2 = new float[nodes];
		float[][] deltaWeight_1 = new float[nodes][nodes];

		float[] deltaBias = new float[nodes];

		// multiplies the derivative of the sigmoid by the margin of error to get the change in the sum
		
		float deltaOutputSum;
		if(functionType == 1) {
			deltaOutputSum = hyperbolicTangentDerivative(marginOfError, output);
		}
		else {
			deltaOutputSum = sigmoidDerivative(marginOfError, output);
		}
		

		// uses the change in the sum to alter the weights and the bias accordingly using specific formulas
		
		for (int i = 0; i < nodes; i++) {
			// 1D weights are changed
			this.weight_2[i] = weight_2[i] + deltaOutputSum * outputArray[i];
		}

		for (int i = 0; i < nodes; i++) {
			deltaWeight_2[i] = outputArray[i] * (1 - outputArray[i]) * weight_2[i] * deltaOutputSum;
			for (int j = 0; j < nodes; j++) {
				//2D weights are changed based on other weight
				deltaWeight_1[j][i] = deltaWeight_2[i] * data[j];
				this.weight_1[j][i] = weight_1[j][i] + deltaWeight_1[j][i];
			}
		}

		for (int i = 0; i < nodes; i++) {
			// bias is changed 
			deltaBias[i] = outputArray[i] * (1 - outputArray[i]) * weight_2[i] * deltaOutputSum;
			functionBias[i] = bias[i] + deltaBias[i];
		}
	}

	// gets functionBias
	public float[] getBias() {
		return functionBias;
	}
	
	// gets weight_2
	public float[] getWeight_2() {
		return weight_2;
	}

	// gets weight_1
	public float[][] getWeight_1() {
		return weight_1;
	}
	
	private float sigmoidDerivative(float marginOfError, float output) {
		return (float) ((Math.exp(output)) / (Math.pow((Math.exp(output)) + 1, 2))) * marginOfError;
	}
	
	private float hyperbolicTangentDerivative(float marginOfError, float output) {
		return (float) ((4 * Math.exp(2 * output) / Math.pow(Math.exp(2 * output) + 1, 2))) * marginOfError;
	}

}
