package com.masasdani.sengon.evaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author masasdani 
 * Created Date Oct 4, 2015
 */
public class FMeasure {

	/**
	 * 
	 */
	private long selected;
	/**
	 * 
	 */
	private long target;
	/**
	 * 
	 */
	private long truePositive;

	/**
	 * @return
	 */
	public double getPrecisionScore() {
		return selected > 0 ? (double) truePositive / (double) selected : 0;
	}

	/**
	 * @return
	 */
	public double getRecallScore() {
		return target > 0 ? (double) truePositive / (double) target : 0;
	}

	/**
	 * @return
	 */
	public double getFMeasure() {
		if (getPrecisionScore() + getRecallScore() > 0) {
			return 2 * (getPrecisionScore() * getRecallScore())
					/ (getPrecisionScore() + getRecallScore());
		} else {
			return -1;
		}
	}

	/**
	 * @param references
	 * @param predictions
	 */
	public void updateScores(final Object[] references,
			final Object[] predictions) {
		truePositive += countTruePositives(references, predictions);
		selected += predictions.length;
		target += references.length;
	}

	/**
	 * @param measure
	 */
	public void mergeInto(final FMeasure measure) {
		this.selected += measure.selected;
		this.target += measure.target;
		this.truePositive += measure.truePositive;
	}

	@Override
	public String toString() {
		return "Precision: " + Double.toString(getPrecisionScore()) + "\n"
				+ "Recall: " + Double.toString(getRecallScore()) + "\n"
				+ "F-Measure: " + Double.toString(getFMeasure());
	}

	/**
	 * @param references
	 * @param predictions
	 * @return
	 */
	static int countTruePositives(final Object[] references,
			final Object[] predictions) {
		List<Object> predListSpans = new ArrayList<Object>(predictions.length);
		Collections.addAll(predListSpans, predictions);
		int truePositives = 0;
		Object matchedItem = null;

		for (int referenceIndex = 0; referenceIndex < references.length; referenceIndex++) {
			Object referenceName = references[referenceIndex];
			for (int predIndex = 0; predIndex < predListSpans.size(); predIndex++) {
				if (referenceName.equals(predListSpans.get(predIndex))) {
					matchedItem = predListSpans.get(predIndex);
					truePositives++;
				}
			}
			if (matchedItem != null) {
				predListSpans.remove(matchedItem);
			}
		}
		return truePositives;
	}

	/**
	 * @param references
	 * @param predictions
	 * @return
	 */
	public static double precision(final Object[] references,
			final Object[] predictions) {
		if (predictions.length > 0) {
			return countTruePositives(references, predictions)
					/ (double) predictions.length;
		} else {
			return Double.NaN;
		}
	}

	/**
	 * @param references
	 * @param predictions
	 * @return
	 */
	public static double recall(final Object[] references,
			final Object[] predictions) {
		if (references.length > 0) {
			return countTruePositives(references, predictions)
					/ (double) references.length;
		} else {
			return Double.NaN;
		}
	}
}
