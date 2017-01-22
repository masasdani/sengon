package com.masasdani.sengon.model;

import java.util.ArrayList;
import java.util.List;

public class Document {
	
	private String text;
	private float score;
	private List<Sentence> sentences;
	private Sentiment sentiment;
	
	public Document() {
		
	}
	
	public Document(String text) {
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

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}
	
	public SentimentDocument toSentimentDocument(){
		SentimentDocument sentimentDocument = new SentimentDocument();
		sentimentDocument.setText(text);
		sentimentDocument.setScore(score);
		sentimentDocument.setSentiment(sentiment);
		if(sentences != null){
			List<SentimentSentence> list = new ArrayList<SentimentSentence>();
			for(Sentence sentence : sentences){
				list.add(sentence.toSentimentSentence());
			}
			sentimentDocument.setSentences(list);
		}
		return sentimentDocument;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
