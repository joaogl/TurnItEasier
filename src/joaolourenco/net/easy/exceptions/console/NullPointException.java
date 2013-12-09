package joaolourenco.net.easy.exceptions.console;

public class NullPointException extends NullPointerException {
	private static final long serialVersionUID = 1L;

	public NullPointException() {
		super("Null Pointer Exception");
	}

	public NullPointException(String args) {
		super("The default console doesnt have " + args + "");
	}

	public NullPointException(String args, String argsw) {
		super(args);
	}

}
