package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {

	// sorts the sample data into a 2-dimensional array by accepting the local file
	// destination
	public static float[][] readSampleData(String fileDestination) {
		// initializes array
		float[][] inputArray = null;
		try {
			// stores sample data into a list
			List<String> samples = Files.readAllLines(Paths.get(fileDestination));
			int numberOfInputs = samples.size();
			inputArray = new float[numberOfInputs][];
			// uses list size to fill up the input array
			for (int i = 0; i < numberOfInputs; i++) {
				// splits the line in the sample data with a comma
				inputArray[i] = convertStringToFloat(samples.get(i).split(","));
			}

		} catch (IOException e) {
			// sends an error message
			InputError.message("An error occured with your data file.");
		}

		return inputArray;
	}

	// sorts the sample results into a 1-dimensional array by accepting the local
	// file destination
	public static int[] readSampleResults(String fileDestination) {
		// initializes array
		int[] inputArray = new int[0];

		try {
			// stores sample data into a list
			List<String> samples = Files.readAllLines(Paths.get(fileDestination));
			inputArray = new int[samples.size()];
			// uses list size to fill up the input array
			for (int i = 0; i < samples.size(); i++) {
				inputArray[i] = Integer.parseInt(samples.get(i));
			}
		} catch (IOException e) {
			// sends an error message
			System.out.println("An error occured with your data file.");
		}

		return inputArray;
	}

	// converts an array of strings to an array of floats
	private static float[] convertStringToFloat(String[] rowNumber) {
		// checks to see if there is even a value in the first row
		if (rowNumber != null) {
			// gets the length of the array
			float floatArray[] = new float[rowNumber.length];
			// fills up the float array after converting it from a string
			for (int i = 0; i < rowNumber.length; i++) {
				floatArray[i] = Float.parseFloat(rowNumber[i]);
			}
			return floatArray;
		}
		return null;
	}

}
