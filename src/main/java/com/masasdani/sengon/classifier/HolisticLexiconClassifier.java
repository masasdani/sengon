package com.masasdani.sengon.classifier;

import com.masasdani.sengon.model.*;
import com.masasdani.sengon.partofspeech.PartOfSpeech;
import com.masasdani.sengon.preprocessing.Preprocessing;
import com.masasdani.sengon.sentencedetector.SentenceDetector;
import com.masasdani.sengon.tokenizer.Tokenizer;
import com.masasdani.sengon.util.FileUtil;
import com.masasdani.sengon.util.RegexUtil;

import java.io.InputStream;
import java.util.*;

/**
 * Abstract implementation of Sentiment Classifier
 * 
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public abstract class HolisticLexiconClassifier implements SentimentClassifier {

	private SentenceDetector sentenceDetector;
	private PartOfSpeech partOfSpeech;
	private Map<String, Integer> opinionWords;
	private Tokenizer tokenizer;
	
	/**
	 * @param sentenceDetector
	 * @param partOfSpeech
	 * @param tokenizer
	 * @param lexiconDataPositive
	 * @param lexiconDataNegative
	 * @param classPathResource
	 */
	public HolisticLexiconClassifier(
			SentenceDetector sentenceDetector, 
			PartOfSpeech partOfSpeech, 
			Tokenizer tokenizer,
			String lexiconDataPositive, 
			String lexiconDataNegative, boolean classPathResource) {
		this(sentenceDetector, partOfSpeech, tokenizer, null);
		String positiveString = "";
		String negativeString = "";
		if(classPathResource){
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream positiveInputStream = classLoader.getResourceAsStream(lexiconDataPositive);
			InputStream negativeInputStream = classLoader.getResourceAsStream(lexiconDataNegative);
			positiveString = FileUtil.read(positiveInputStream);
			negativeString = FileUtil.read(negativeInputStream);
		}else{
			positiveString = FileUtil.read(lexiconDataPositive);
			negativeString = FileUtil.read(lexiconDataNegative);
		}
		String negativeSentiment[] = negativeString.split(RegexUtil.NEWLINE_REGEX);
		String positiveSentiment[] = positiveString.split(RegexUtil.NEWLINE_REGEX);
		opinionWords = new HashMap<String, Integer>();
		for(String string : negativeSentiment){
			string = string.toLowerCase();
			opinionWords.put(string, -1);
		}
		for(String string : positiveSentiment){
			string = string.toLowerCase();
			opinionWords.put(string, 1);
		}
	}

	/**
	 * @param sentenceDetector
	 * @param partOfSpeech
	 * @param tokenizer
	 * @param opinionWords
	 */
	public HolisticLexiconClassifier(
			SentenceDetector sentenceDetector, 
			PartOfSpeech partOfSpeech, 
			Tokenizer tokenizer,
			Map<String, Integer> opinionWords) {
		this.sentenceDetector = sentenceDetector;
		this.partOfSpeech = partOfSpeech;
		this.opinionWords = opinionWords;
		this.tokenizer = tokenizer;
	}
	
	@Override
	public Document getDocumentOrientation(String text){
		float score = 0;
		
		text = applyPrepocessing(text);
		Document document = new Document(text);
		String[] strings = sentenceDetector.parseSentence(document.getText());
		List<Sentence> sentences = new ArrayList<Sentence>();
		List<String> positive = new ArrayList<String>();
		List<String> negative = new ArrayList<String>();
		for(String s : strings){
			Sentence sentence = countSentenceSentiment(s);
			score += sentence.getScore();
			positive.addAll(sentence.getPositives());
			negative.addAll(sentence.getNegatives());
			sentences.add(sentence);
		}
		document.setSentences(sentences);
		document.setScore(score);
		document.setSentiment(sentimentFromScore(score));
		return document;
	}

	@Override
	public List<SentimentFeature> getFeatureOrientation(String text, String[] features) {
		return null;
	}

	@Override
	public SentimentDocument getSentimentDocumentOrientation(String text) {
		return getDocumentOrientation(text).toSentimentDocument();
	}

	/**
	 * @param text
	 * @return
	 */
	private String applyPrepocessing(String text){
		text = Preprocessing.caseFolding(text);
		text = Preprocessing.twitterTextCleaning(text);
		return text;
	}
	
	/**
	 * @param text
	 * @return
	 */
	private Sentence countSentenceSentiment(String text){
		float score = 0;
		boolean markedAsTooEffect = false;
		boolean markedAsNegationEffect = false;
		
		Sentence sentence = new Sentence(text);
		String[] words = tokenizer.tokenize(text, null);
		List<String> positives = new ArrayList<String>();
		List<String> negatives = new ArrayList<String>();
		String[] tags = partOfSpeech.test(words);
		if(isButClauseAvailable(tags)){
			int indexBut = startButClause(words, tags);
			words = Arrays.copyOfRange(words, indexBut, words.length);
			tags = Arrays.copyOfRange(tags, indexBut, tags.length);
		}
		int featureIndex = -1;
		for(int i=featureIndex+1;i<tags.length;i++){
			if(tags[i].equals("NEG")){
				if(i<tags.length-1){
					markedAsNegationEffect = true;
					continue;
				}
			}
			if(tags[i].equals("TOO")){
				markedAsTooEffect = true;
			}
			float orientation = getWordOrientation(words[i], featureIndex, i, markedAsNegationEffect);
			if(orientation > 0){
				positives.add(words[i]);
			}else if(orientation < 0){
				negatives.add(words[i]);
			}
			score += orientation;
			markedAsNegationEffect = false;
		}
		if(markedAsTooEffect){
			sentence.setScore(-1);
		}
		
		sentence.setScore(score);
		sentence.setPositives(positives);
		sentence.setNegatives(negatives);
		sentence.setTerms(Arrays.asList(tokenizer.tokenize(text)));
		return sentence;
	}

	/**
	 * @param word
	 * @param featureIndex
	 * @param wordIndex
	 * @param markedAsNegationEffect
	 * @return
	 */
	private float getWordOrientation(
			String word, 
			int featureIndex, 
			int wordIndex, 
			boolean markedAsNegationEffect) {
		int wordOrientation = 0;
		if(opinionWords.containsKey(word)){
			wordOrientation = opinionWords.get(word);
		}
		if(markedAsNegationEffect){
			if(wordOrientation == 0){
				wordOrientation = -1;
			}else{
				wordOrientation = -wordOrientation;
			}
		}
		return orientation(wordOrientation, featureIndex, wordIndex);
	}

	/**
	 * @param strings
	 * @param feature
	 * @return
	 */
	@SuppressWarnings("unused")
	private int getFeatureIndexInText(String[] strings, String feature){
		for(int i=0;i<strings.length;i++){
			if(strings[i].equals(feature)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @param tags
	 * @return
	 */
	private boolean isButClauseAvailable(String[] tags) {
		for(String tag : tags){
			if(tag.equals("BUT")){
				return true;
			}
		}
		return false;
	}

	/**
	 * @param parsedText
	 * @param tags
	 * @return
	 */
	private int startButClause(String[] parsedText, String[] tags) {
		for(int i=0;i<tags.length;i++){
			if(tags[i].equals("BUT")){
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * @param featureIndex
	 * @param wordIndex
	 * @return
	 */
	private int distance(int featureIndex, int wordIndex){
		return Math.abs(wordIndex-featureIndex);
	}
	
	/**
	 * @param orientation
	 * @param featureIndex
	 * @param wordIndex
	 * @return
	 */
	private float orientation(int orientation, int featureIndex, int wordIndex){
		return (float) orientation / distance(featureIndex, wordIndex);
	}
	
	/**
	 * @param score
	 * @return
	 */
	private Sentiment sentimentFromScore(float score){
		if(score > 0){
			return Sentiment.POSITIVE;
		}else if(score < 0){
			return Sentiment.NEGATIVE;
		}else {
			return Sentiment.NEUTRAL;
		}
	}
}
