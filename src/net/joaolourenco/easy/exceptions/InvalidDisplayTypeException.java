package net.joaolourenco.easy.exceptions;

public class InvalidDisplayTypeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidDisplayTypeException(){
		super("That DisplayType doesnt exist!");
	}
	
	public InvalidDisplayTypeException(String args){
		super(args + " is not a valid DisplayType!");
	}

}
