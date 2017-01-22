package com.masasdani.sengon.model;

public class SentimentFeature {

	private String text;
	private float score;
	private Sentiment sentiment;
	
	public SentimentFeature() {
		
	}
	
	public SentimentFeature(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Sentiment getSentiment() {
		return sentiment;
	}

	public void setSentiment(Sentiment sentiment) {
		this.sentiment = sentiment;
	}
	
}
