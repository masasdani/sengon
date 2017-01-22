package com.masasdani.sengon.exception;

/**
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public class LanguageNotSupportedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6410263807565011590L;
	private static final String MESSAGE = "The Specified Language you entered not supported yet!";
	
	public LanguageNotSupportedException() {
		super(MESSAGE);
	}
	
}
