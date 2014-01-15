package net.joaolourenco.easy.exceptions;

public class NotIntegerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotIntegerException() {
		super("The input is not an Integer!");
	}

	public NotIntegerException(String args, String args2) {
		super("The " + args + " is not " + args2 + "!");
	}

}
