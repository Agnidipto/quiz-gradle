package com.cg.exceptions;

public class QuestionNotFoundException extends Exception  //this class is dedicated for Exception Handling for Questions
{
	public QuestionNotFoundException(String msg)
	{
		super(msg);  // will get the custom message written when used 
	}
}