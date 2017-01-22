package com.masasdani.sengon.classifier;

import com.masasdani.sengon.partofspeech.PartOfSpeech;
import com.masasdani.sengon.partofspeech.PartOfSpeechId;
import com.masasdani.sengon.sentencedetector.SentenceDetector;
import com.masasdani.sengon.sentencedetector.SentenceDetectorEn;
import com.masasdani.sengon.tokenizer.IndonesianTokenizer;
import com.masasdani.sengon.tokenizer.Tokenizer;

import java.util.Map;

/**
 * Lexicon Classifier for Indonesia Language
 * 
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public class HolisticLexiconClassifierId extends HolisticLexiconClassifier {

	public static final String LEXICON_DATA_POSITIVE = "id_positive.txt";
	public static final String LEXICON_DATA_NEGATIVE = "id_negative.txt";
	
	/**
	 * @param sentenceDetector
	 * @param partOfSpeech
	 * @param tokenizer
	 * @param opinionWords
	 */
	public HolisticLexiconClassifierId(
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
	public HolisticLexiconClassifierId(
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
	public HolisticLexiconClassifierId() {
		super(new SentenceDetectorEn(), new PartOfSpeechId(), new IndonesianTokenizer(), LEXICON_DATA_POSITIVE, LEXICON_DATA_NEGATIVE, true);
	}
	
}
