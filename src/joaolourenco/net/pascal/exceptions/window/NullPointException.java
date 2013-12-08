package joaolourenco.net.pascal.exceptions.window;

public class NullPointException extends UnsupportedOperationException {
	private static final long serialVersionUID = 1L;

	public NullPointException() {
		super("The requested action is not possible on frames.");
	}

	public NullPointException(String args) {
		super("The requested action(" + args + ") is not possible on frames.");
	}

	public NullPointException(int a, String args) {
		super("You cannot stop something that doesnt exist.(" + args + ")");
	}

	public NullPointException(String args, int a) {
		super("You cannot use something that doesnt exist.(" + args + ")");
	}

}
