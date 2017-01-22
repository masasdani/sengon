package com.masasdani.sengon.classifier;

import com.masasdani.sengon.model.Document;
import com.masasdani.sengon.model.SentimentDocument;
import com.masasdani.sengon.model.SentimentFeature;

import java.util.List;

/**
 * Interface of sentiment classifier
 * 
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public interface SentimentClassifier {

	/**
	 * @param text
	 * @return
	 */
	public SentimentDocument getSentimentDocumentOrientation(String text);
	
	/**
	 * @param text
	 * @return
	 */
	public Document getDocumentOrientation(String text);
	
	/**
	 * @param text
	 * @param feature
	 * @return
	 */
	public List<SentimentFeature> getFeatureOrientation(String text, String[] feature);
		
}
