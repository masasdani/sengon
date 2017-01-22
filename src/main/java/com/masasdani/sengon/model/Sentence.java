package com.masasdani.sengon.model;

import java.util.List;

public class Sentence {

	private String text;
	private List<String> positives;
	private List<String> negatives;
	private List<String> terms;
	private float score;
	
	public Sentence() {
	
	}

	public Sentence(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public List<String> getPositives() {
		return positives;
	}

	public void setPositives(List<String> positives) {
		this.positives = positives;
	}

	public List<String> getNegatives() {
		return negatives;
	}

	public void setNegatives(List<String> negatives) {
		this.negatives = negatives;
	}

	public List<String> getTerms() {
		return terms;
	}

	public void setTerms(List<String> terms) {
		this.terms = terms;
	}
	
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public SentimentSentence toSentimentSentence(){
		return new SentimentSentence(text, score);
	}
}
