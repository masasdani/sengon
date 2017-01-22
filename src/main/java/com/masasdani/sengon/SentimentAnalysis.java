package com.masasdani.sengon;

import com.masasdani.sengon.classifier.HolisticLexiconClassifierEn;
import com.masasdani.sengon.classifier.HolisticLexiconClassifierId;
import com.masasdani.sengon.classifier.HolisticLexiconClassifierTh;
import com.masasdani.sengon.classifier.SentimentClassifier;
import com.masasdani.sengon.model.Document;
import com.masasdani.sengon.model.Language;
import com.masasdani.sengon.model.Sentiment;
import com.masasdani.sengon.util.FileUtil;
import opennlp.tools.util.eval.FMeasure;
import org.apache.commons.cli.*;

/**
 * Main Class to do sentiment analysis via console
 * 
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public class SentimentAnalysis {

	private static final String TITLE = "mentalis [[option] [optionvalues] ...]";
	private static final String HEADER = 
			"Measure the opinion orientation (sentiment) of people from a given text. \n"
			+ "\n";
	private static final String LANGUAGE_DESC = "select language processor, \n"
			+ "available is English (en), Indonesian (id) \n"
			+ "default value = id.";
	private static final String EVALUATION_DESC = "Count and measure the Accuracy of testing document.";
	private static final String TEXT_DESC = "Get sentiment from given text / document.";
	private static final String HELP_DESC = "Print this message.";
	
	private static final String LANGUAGE_INVALID = "Your given language is invalid or has not yet implemented. \n";
	
	/**
	 * classifier class
	 */
	private SentimentClassifier classifier;
	
	/**
	 * Language to process class
	 */
	private Language language;
	
	/**
	 * Default Constructor
	 */
	public SentimentAnalysis() {
	
	}
	
	/**
	 * 
	 * @param language
	 */
	public SentimentAnalysis(Language language) {
		this.language = language;
	}
	
	/**
	 * @param classifier
	 */
	public void setClassifier(SentimentClassifier classifier) {
		this.classifier = classifier;
	}

	/**
	 * @param language
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * @param text
	 * @return
	 */
	public Document doSentimentAnalysis(String text){
		switch (language) {
		case INDONESIA:
			classifier = new HolisticLexiconClassifierId();
			break;
		case THAILAND:
			classifier = new HolisticLexiconClassifierTh();
			break;
		default:
			classifier = new HolisticLexiconClassifierEn();
			break;
		}
		return classifier.getDocumentOrientation(text);
	}
	
	/**
	 * @param args
	 */
	public void run(String[] args){
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		Option languageOption = new Option("l", "language", true, LANGUAGE_DESC);
		languageOption.setArgName("lang");
		options.addOption(languageOption);
		Option evaluationOption = new Option("e", "evaluate", true, EVALUATION_DESC);
		evaluationOption.setArgName("file");
		options.addOption(evaluationOption);
		Option textOption = new Option("t", "text", true, TEXT_DESC);
		textOption.setArgName("text");
		options.addOption(textOption);
		options.addOption("h", HELP_DESC);
		
		try {
			CommandLine commandLine = parser.parse(options, args);
			if(args.length == 0 || commandLine.hasOption("h")){
				printHelp(options);
				return;
			}
			if(!commandLine.hasOption("t") && !commandLine.hasOption("e")){
				printHelp(options);
				return;
			}
			if(commandLine.hasOption("l")){
				if(commandLine.getOptionValue("l").equalsIgnoreCase("en")){
					language = Language.ENGLISH;
				}else if(commandLine.getOptionValue("l").equalsIgnoreCase("id")){
					language = Language.INDONESIA;
				}else{
					System.out.println(LANGUAGE_INVALID);
					printHelp(options);
					return;
				}
			}
			if(commandLine.hasOption("t")){
				String text = commandLine.getOptionValue("t");
				Document document = doSentimentAnalysis(text);
				System.out.println(document.toSentimentDocument().toString());
				return;
			}
			if(commandLine.hasOption("e")){
				String fileLocation = commandLine.getOptionValue("e");
				String document = FileUtil.read(fileLocation);
				String[] textLines = document.split("\\r?\\n");
				String[] predictions = new String[textLines.length]; 
				String[] references = new String[textLines.length]; 
				for(int i=0;i<textLines.length;i++){
					String text = textLines[i];
					String[] lines = text.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
					String sentence = lines[0];
					String classification = lines[1];
					Document doc = doSentimentAnalysis(sentence);
					System.out.println(doc.toSentimentDocument().toString());
					references[i] = classification;
					if(doc.getSentiment() == Sentiment.NEGATIVE){
						predictions[i] = "-1";
					}else if(doc.getSentiment() == Sentiment.POSITIVE){
						predictions[i] = "1";
					}else{
						predictions[i] = "0";
					}
				}
				FMeasure fMeasure = new FMeasure();
				fMeasure.updateScores(references, predictions);
				System.out.println(fMeasure);
				return;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param options
	 */
	private void printHelp(Options options){
		HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.printHelp(TITLE, HEADER, options, null, false);
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]){
		new SentimentAnalysis().run(args);
	}
	
}
