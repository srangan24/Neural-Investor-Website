package Network;

import Utils.InputError;
import Utils.Randomizer;

public class HiddenNeurons {
	
	// instance variables
	private float[] functionBias;
	private float[][] weight_1;
	private float[] weight_2;

	// constructor used to initialize variables
	public HiddenNeurons(int nodes) {
		functionBias = new float[nodes];
		weight_1 = new float[nodes][nodes];
		weight_2 = new float[nodes];

		// randomizes the bias and weights
		randomizer(nodes);
	}

	// gets the bias 
	public float[] getBias() {
		return functionBias;
	}
	
	// gets the 1D weight value
	public float[][] getWeight_1() {
		return weight_1;
	}

	// gets the 2D weight value
	public float[] getWeight_2() {
		return weight_2;
	}


	// sets a random value to the weights and the bias at first
	private void randomizer(int nodes) {
		if (nodes == 0) {
			InputError.message("Files must contain data values.");
		}
		for (int i = 0; i < nodes; i++) {
			// assigns a random number to each spot in the arrays that show the weight and bias amounts
			this.functionBias[i] = (float) Randomizer.weightsAndBias(-0.5, 0.5);
			this.weight_2[i] = (float) Randomizer.weightsAndBias(-0.5, 0.5);
			for (int j = 0; j < nodes; j++) {
				this.weight_1[j][i] = (float) Randomizer.weightsAndBias(-0.5, 0.5);
			}
		}
	}
}
