package ResultManager;

import Network.Calculate;

public class PredictOutput {
	
	// instance variables
	private float SuccessfulChance; // the amount of successful outcomes over the amount of simulations
	
	// calls upon the Calculate class to generate a output
	// this output is then converted to a one or zero using the BinaryInterpreter class
	public int prediction(Calculate calculate, float[] input, int functionType) {
		BinaryInterpreter bi = new BinaryInterpreter(functionType);
		return bi.getResult(calculate.calcOutput(input, functionType));
		
	}
	
	// gets the SuccessfulChance of the network
	public float getSuccessfulChance() {
		return SuccessfulChance;
	}

	// sets the SuccessfulChance of the network
	public void setSuccessfulChance(float successfulChance) {
		SuccessfulChance = successfulChance;
	}
	
}
