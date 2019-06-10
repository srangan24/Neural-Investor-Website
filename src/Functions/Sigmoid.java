package Functions;

public class Sigmoid implements ActivationImplementation {

	@Override
	public float activate(double x) {
		// TODO Auto-generated method stub
		// formula for Sigmoid graph
		return (float) (1/(1+Math.exp(-x)));
	}

}
