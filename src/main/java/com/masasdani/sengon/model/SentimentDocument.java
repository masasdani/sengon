package com.masasdani.sengon.model;

import java.util.List;

public class SentimentDocument {
	
	private String text;
	private float score;
	private List<SentimentSentence> sentences;
	private Sentiment sentiment;
	
	public SentimentDocument() {
		
	}
	
	public SentimentDocument(String text) {
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

	public List<SentimentSentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<SentimentSentence> sentences) {
		this.sentences = sentences;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(text+"\n");
		builder.append("sentiment : " + sentiment+"\n");
		for(SentimentSentence sentence : sentences){
			builder.append(sentence);
		}
		return builder.toString();
	}
}
