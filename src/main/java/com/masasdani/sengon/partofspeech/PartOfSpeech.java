package com.masasdani.sengon.partofspeech;

/**
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public interface PartOfSpeech {

	/**
	 * @param corpus
	 * @param modelOut
	 */
	public void train(String corpus, String modelOut);
	
	/**
	 * @param words
	 * @return
	 */
	public String[] test(String[] words);
	
}
