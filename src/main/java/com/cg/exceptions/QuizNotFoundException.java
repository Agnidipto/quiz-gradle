package com.cg.exceptions;

public class QuizNotFoundException extends Exception { // handles exceptions for Quiz module
	public QuizNotFoundException(String msg) {
		super(msg); // prints custom msg when used
	}

}