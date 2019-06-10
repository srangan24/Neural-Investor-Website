package ResultManager;

public class BinaryInterpreter {
	
	private int functionType;
	
	public BinaryInterpreter(int functionType) {
		this.functionType = functionType;
	}
	
	// checks to see if the output was successful or not and counts the amount of times it was
	public int countSuccess(int successes, float output, int result) {
		
		// checks the see if the output returns the correct integer
		if ((output < 0.5 && result == 0) || (output >= 0.5 && result == 1)) {
			// increments each time that it was successful
			successes += 1;
		}

		return successes;
	}

	public int getResult(float test) {
		// sees what number an output would return
		if (test >= 0.5) {
			return 1;
		} else
			return 0;
	}
}
