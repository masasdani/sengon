package com.masasdani.sengon;

import com.optimaize.langdetect.DetectedLanguage;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;
import com.masasdani.sengon.exception.LanguageNotSupportedException;
import com.masasdani.sengon.model.Language;

import java.io.IOException;
import java.util.List;

/**
 * This Class main class of this a wrapper class of language detection library
 * 
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public class LanguageDetect {
	
	/**
	 * LanguageDetector contain method to do langauge detection
	 */
	private LanguageDetector languageDetector;
	
	/**
	 * used to generate clean processing for large text
	 */
	private TextObjectFactory textObjectFactory;
	
	/**
	 * Default Constructor
	 */
	public LanguageDetect() {
		this.languageDetector = languageDetector();
		this.textObjectFactory = textObjectFactory();
	}
	
	/**
	 * Create LanguageDetector Object
	 * 
	 * @return 
	 * 
	 * @see https://github.com/masasdani/language-detector
	 */
	public LanguageDetector languageDetector(){
		List<LanguageProfile> languageProfiles;
		try {
			languageProfiles = new LanguageProfileReader().readAllBuiltIn();
			LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
			        .withProfiles(languageProfiles)
			        .build();
			return languageDetector;
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * Create TextObjectFactory object for detecting large text
	 * 
	 * @return 
	 * 
	 * @see https://github.com/masasdani/language-detector
	 */
	public TextObjectFactory textObjectFactory(){
		return CommonTextObjectFactories.forDetectingOnLargeText();
	}
	
	/**
	 * Method to do language Detection,
	 * 
	 * @param text
	 * @return
	 */
	public String detect(String text){
		try{
			TextObject textObject = textObjectFactory.forText(text);
			List<DetectedLanguage> lang = languageDetector.getProbabilities(textObject);
			return lang.get(0).getLocale().getLanguage();
		}catch(Exception exception){
			return null;
		}
	}

	/**
	 * Method to do Language Detection
	 * 
	 * @param text
	 * @return
	 */
	public List<DetectedLanguage> detectProbabilities(String text){
		try{
			TextObject textObject = textObjectFactory.forText(text);
			List<DetectedLanguage> lang = languageDetector.getProbabilities(textObject);
			return lang;
		}catch(Exception exception){
			return null;
		}
	}
	
	/**
	 * convert return language to mentalis supported language so far
	 * 
	 * @param text
	 * @return
	 * @throws LanguageNotSupportedException
	 */
	public Language getLanguage(String text) throws LanguageNotSupportedException {
		String lang = detect(text);
		switch (lang) {
		case "id":
			return Language.INDONESIA;
		case "en":
			return Language.ENGLISH;
		default:
			throw new LanguageNotSupportedException();
		}
	}
}
