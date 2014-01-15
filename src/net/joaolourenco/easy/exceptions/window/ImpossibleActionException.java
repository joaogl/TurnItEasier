package net.joaolourenco.easy.exceptions.window;

public class ImpossibleActionException extends UnsupportedOperationException {
	private static final long serialVersionUID = 1L;

	public ImpossibleActionException() {
		super("The requested action is not possible on frames.");
	}

	public ImpossibleActionException(String args) {
		super("The requested action(" + args + ") is not possible on frames.");
	}

}
