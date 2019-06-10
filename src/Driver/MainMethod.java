package Driver;

import Network.Calculate;
import Network.Networking;
import ResultManager.PredictOutput;
import Utils.FileReader;

public class MainMethod {

	public static void main(String[] args) {
		
		int functionType;
		String functionName = "H";
		switch(functionName) {
			case "HyperbolicTangent": case "Hyperbolic Tangent":
			case "hyperbolictangent": case "hyperbolic tangent":
			case "HT": case "ht": functionType = 1;
			break;
			default: functionType = 0; break;
		}
		
		// TODO Auto-generated method stub
		// notes down the start time
		long startTime = System.currentTimeMillis();
		
		// tells the user that the program is running
		System.out.println("Initiating neural network. Please wait...");

		// reads the two text files that contain all the known inputs and respective results
		float[][] knownInputs = FileReader.readSampleData("data/TestInputs2.txt");
		int[] knownResults = FileReader.readSampleResults("data/KnownResults.txt");

		// creates the network object that does all the work
		Networking neuralNetwork = new Networking(knownInputs, knownResults, functionType);
		
		// iterations will start and will result in a final adjusted network object
		neuralNetwork.networkLearning();
		
		// displays object information to the user (such as reliability)
		System.out.println("\nNumber of nodes: " + neuralNetwork.getDimension());
		System.out.println("Success percentage: " + neuralNetwork.getSuccessPercentage());
		
		// this is where the user can input what values he or she wants to test
		float[] valueToPredict = new float[] { -0.8075f, 0.7209f };
		
		// creates new PredictOutput object in order to calculate if the user prediction will be 1 or 0 
		PredictOutput po = new PredictOutput();
		Calculate analyze = neuralNetwork.getCalculate();
		int result = po.prediction(analyze, valueToPredict, functionType);
		
		// displays output to the user
		System.out.println("Prediction: " + result);
		
		// notes down the end time
		long endTime = System.currentTimeMillis();
		
		// displays the total execution time of the predictor
		System.out.println("\nExecution time: " + (endTime-startTime) + " milliseconds");
	}

}
