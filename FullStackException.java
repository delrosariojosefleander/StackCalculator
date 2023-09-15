package edu.ics211.h08;

public class FullStackException extends RuntimeException {

	final static long serialVersionUID = 1;

	public FullStackException() {
		super("FullStackException has occurred");
	}

	public FullStackException(String msg) {
		super(msg);
	}
}
