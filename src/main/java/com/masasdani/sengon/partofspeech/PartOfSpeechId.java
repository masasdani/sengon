package com.masasdani.sengon.partofspeech;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.postag.WordTagSampleStream;
import opennlp.tools.util.*;

import java.io.*;

/**
 * Wrapper for Apache Open NLP English
 * 
 * @author masasdani
 * Created Date Oct 4, 2015
 * 
 * @see Apache Open NLP 
 */
public class PartOfSpeechId implements PartOfSpeech {
	
	public static final String POS_MODEL_URL = "id_pos_maxent.bin";
	/**
	 * @see Apache Open NLP 
	 */
	POSModel model;
	
	/**
	 * 
	 */
	public PartOfSpeechId() {
		ClassLoader classLoader = getClass().getClassLoader();
		model = loadModel(classLoader.getResourceAsStream(POS_MODEL_URL));
	}
	
	/**
	 * @param modelURL
	 */
	public PartOfSpeechId(String modelURL) {
		model = loadModel(modelURL);
	}
	
	/**
	 * @param inputStream
	 */
	public PartOfSpeechId(InputStream inputStream) {
		model = loadModel(inputStream);
	}
	
	@Override
	public void train(String corpus, String modelout){
		POSModel model = null;
		File dataIn = null;
		try {
		  dataIn = new File(corpus);
		  InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(dataIn);
		  ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, "UTF-8");
		  ObjectStream<POSSample> sampleStream = new WordTagSampleStream(lineStream);
		  model = POSTaggerME.train("id", sampleStream, TrainingParameters.defaultParams(), null);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		OutputStream modelOut = null;
		File fileOut = new File(modelout);
		try {
		  modelOut = new BufferedOutputStream(new FileOutputStream(fileOut));
		  model.serialize(modelOut);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelOut != null) {
			  try {
			     modelOut.close();
			  }
			  catch (IOException e) {
			    e.printStackTrace();
			  }
		  }
		}
	}
	
	@Override
	public String[] test(String[] words){
		POSTaggerME tagger = new POSTaggerME(model);
		return tagger.tag(words);
	}
	
	/**
	 * @param modelUrl
	 * @return
	 */
	public POSModel loadModel(String modelUrl){
		POSModel model = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(modelUrl);
			model = new POSModel(inputStream);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (inputStream != null) {
		    try {
		    	inputStream.close();
		    }
		    catch (IOException e) {
		    	
		    }
		  }
		}
		return model;
	}

	/**
	 * @param inputStream
	 * @return
	 */
	public POSModel loadModel(InputStream inputStream){
		POSModel model = null;
		try {
			model = new POSModel(inputStream);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (inputStream != null) {
		    try {
		    	inputStream.close();
		    }
		    catch (IOException e) {
		    	
		    }
		  }
		}
		return model;
	}
	
	/**
	 * @return
	 */
	public POSModel getModel() {
		return model;
	}
	
	/**
	 * @param model
	 */
	public void setModel(POSModel model) {
		this.model = model;
	}

	/**
	 * @param modelURL
	 */
	public void setModel(String modelURL) {
		this.model = loadModel(modelURL);
	}
	
}
