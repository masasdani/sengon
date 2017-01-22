package com.masasdani.sengon.model;

public class SentimentSentence {

	private String text;
	private float score;
	private Sentiment sentiment;
		
	public SentimentSentence() {
	
	}
	
	public SentimentSentence(String text, float score) {
		super();
		this.text = text;
		this.score = score;
	}

	public SentimentSentence(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Sentiment getSentiment() {
		return sentiment;
	}

	public void setSentiment(Sentiment sentiment) {
		this.sentiment = sentiment;
	}
	
	@Override
	public String toString() {
		return getText() + " >> " + getScore();
	}
}
