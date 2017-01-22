package com.masasdani.sengon.classifier;

import com.masasdani.sengon.partofspeech.PartOfSpeech;
import com.masasdani.sengon.partofspeech.PartOfSpeechId;
import com.masasdani.sengon.sentencedetector.SentenceDetectorEn;
import com.masasdani.sengon.tokenizer.ThaiTokenizer;
import com.masasdani.sengon.tokenizer.Tokenizer;

import java.util.Map;

/**
 * Lexicon Classifier for Thai
 * 
 * @author masasdani
 * Created Date Oct 4, 2015
 * 
 * TODO add lexicon dataset
 */
public class HolisticLexiconClassifierTh extends HolisticLexiconClassifier {

	public static final String LEXICON_DATA_POSITIVE = null;
	public static final String LEXICON_DATA_NEGATIVE = null;
	
	public HolisticLexiconClassifierTh(
			SentenceDetectorEn sentenceDetector,
			PartOfSpeech partOfSpeech, 
			Tokenizer tokenizer,
			Map<String, Integer> opinionWords) {
		super(sentenceDetector, partOfSpeech, tokenizer, opinionWords);
	}

	public HolisticLexiconClassifierTh() {
		super(new SentenceDetectorEn(), new PartOfSpeechId(), new ThaiTokenizer(), null);
	}
	
}
