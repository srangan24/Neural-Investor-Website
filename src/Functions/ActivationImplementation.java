package Functions;

public interface ActivationImplementation {
	
	// used by all types of graphs
	
	public float activate(double value);
	
	/*
	 * Default transfer function is Sigmoid. Instead of Sigmoid, user can choose to select
	 * HyperbolicTangent or Linear. Depends on user preferences. This network is
	 * most effectively configured to Sigmoid (range 0 to 1). If you change this to
	 * another relationship, all number bounds and references throughout the project
	 * must be updated. If changed, check out this link on the different functions:
	 * https://stevenmiller888.github.io/mind-how-to-build-a-neural-network/
	 */
	
}
