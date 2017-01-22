package com.masasdani.sengon.sentencedetector;

/**
 * Sentence Segmentation / Detection
 * Will parse every sentence using sentence separator
 * 
 * @author masasdani
 * Created Date Oct 4, 2015
 * 
 * @see OpenNLP SentenceDetection
 */
public interface SentenceDetector {
	
	/**
	 * @param text
	 * @return
	 */
	public String[] parseSentence(String text);
		
}
