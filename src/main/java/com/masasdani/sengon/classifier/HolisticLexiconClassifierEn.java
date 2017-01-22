package com.masasdani.sengon.classifier;

import com.masasdani.sengon.partofspeech.PartOfSpeech;
import com.masasdani.sengon.partofspeech.PartOfSpeechEn;
import com.masasdani.sengon.sentencedetector.SentenceDetector;
import com.masasdani.sengon.sentencedetector.SentenceDetectorEn;
import com.masasdani.sengon.tokenizer.EnglishTokenizer;
import com.masasdani.sengon.tokenizer.Tokenizer;

import java.util.Map;

/**
 * Lexicon Classfier for English
 * 
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public class HolisticLexiconClassifierEn extends HolisticLexiconClassifier {

	public static final String LEXICON_DATA_POSITIVE = "en_positive.txt";
	public static final String LEXICON_DATA_NEGATIVE = "en_negative.txt";
	
	/**
	 * @param sentenceDetector
	 * @param partOfSpeech
	 * @param tokenizer
	 * @param opinionWords
	 */
	public HolisticLexiconClassifierEn(
			SentenceDetector sentenceDetector,
			PartOfSpeech partOfSpeech,  
			Tokenizer tokenizer,
			Map<String, Integer> opinionWords) {
		super(sentenceDetector, partOfSpeech, tokenizer, opinionWords);
	}

	/**
	 * @param sentenceDetector
	 * @param partOfSpeech
	 * @param tokenizer
	 * @param lexiconDataPositive
	 * @param lexiconDataNegative
	 */
	public HolisticLexiconClassifierEn(
			SentenceDetector sentenceDetector,
			PartOfSpeech partOfSpeech, 
			Tokenizer tokenizer,
			String lexiconDataPositive, 
			String lexiconDataNegative) {
		super(sentenceDetector, partOfSpeech, tokenizer, lexiconDataPositive, lexiconDataNegative, false);
	}

	/**
	 * 
	 */
	public HolisticLexiconClassifierEn() {
		super(new SentenceDetectorEn(), new PartOfSpeechEn(), new EnglishTokenizer(), LEXICON_DATA_POSITIVE, LEXICON_DATA_NEGATIVE, true);
	}
	
}
