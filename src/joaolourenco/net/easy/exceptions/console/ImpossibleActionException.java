package joaolourenco.net.easy.exceptions.console;

public class ImpossibleActionException extends UnsupportedOperationException {
	private static final long serialVersionUID = 1L;

	public ImpossibleActionException() {
		super("The requested action is not possible on default console.");
	}

	public ImpossibleActionException(String args) {
		super("The requested action(" + args + ") is not possible on default console.");
	}

}
