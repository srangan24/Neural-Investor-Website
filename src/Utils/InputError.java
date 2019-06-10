package Utils;

import java.sql.Timestamp;

public class InputError {

	// helps user debug by locating specific location of error
	// called upon when an error is caught
	public static void message(String s) {
		// gets the current time in milliseconds
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// displays an error message to the user
		System.out.println("Error at " + timestamp);
		System.out.println(s);
	}

}
