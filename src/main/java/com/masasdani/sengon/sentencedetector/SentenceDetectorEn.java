package com.masasdani.sengon.sentencedetector;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.IOException;
import java.io.InputStream;

/**
 * Implementation of sentence segmentation using Englisg Model
 * 
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public class SentenceDetectorEn implements SentenceDetector {

	public static final String EN_SENT_MODEL_URL = "en_sent.bin";
	
	/**
	 * 
	 */
	SentenceModel model;
	/**
	 * 
	 */
	SentenceDetectorME sentenceDetector;
	
	/**
	 * 
	 */
	public SentenceDetectorEn() {
		ClassLoader classLoader = getClass().getClassLoader();
		model = loadModel(classLoader.getResourceAsStream(EN_SENT_MODEL_URL));
		sentenceDetector = new SentenceDetectorME(model);
	}

	/**
	 * @param modelIn
	 */
	public SentenceDetectorEn(InputStream modelIn) {
		model = loadModel(modelIn);
		sentenceDetector = new SentenceDetectorME(model);
	}
	
	@Override
	public String[] parseSentence(String text){
		return sentenceDetector.sentDetect(text);
	}
	
	/**
	 * @param modelIn
	 * @return
	 */
	public SentenceModel loadModel(InputStream modelIn){
		SentenceModel model = null;
		try {
			model = new SentenceModel(modelIn);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }catch (IOException e) {
		    	
		    }
		  }
		}
		return model;
	}
}
