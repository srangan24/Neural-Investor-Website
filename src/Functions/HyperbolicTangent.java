package Functions;

public class HyperbolicTangent implements ActivationImplementation {

	@Override
	public float activate(double value) {
		// TODO Auto-generated method stub
		// formula for hyperbolic tangent graph
		return (float) (((Math.exp(value)-Math.exp(-value)))/(Math.exp(value)+Math.exp(-value)));
	}

}
