package net.joaolourenco.easy.exceptions.window;

public class FailedBuildPointerException extends UnsupportedOperationException {
	private static final long serialVersionUID = 1L;

	public FailedBuildPointerException() {
		super("The requested pointer is not possible to be stablished.");
	}

}