package Utils;

import java.util.Random;

public class Randomizer {

	// creates a random double in between two given values using a formula
	public static double weightsAndBias(double minimum, double maximum) {
		Random random = new Random();
		return random.nextDouble() * (maximum - minimum) + minimum;
	}
}
