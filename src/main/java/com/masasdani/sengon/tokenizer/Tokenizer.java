package com.masasdani.sengon.tokenizer;

import com.masasdani.sengon.util.ArrayUtils;
import com.masasdani.sengon.util.FileUtil;
import com.masasdani.sengon.util.RegexUtil;
import org.apache.lucene.analysis.util.CharArraySet;

import java.util.List;

/**
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public abstract class Tokenizer {

	/**
	 * 
	 */
	private CharArraySet charArraySet;
	/**
	 * 
	 */
	private static final String COMMENT = "#";
	
	/**
	 * @param text
	 * @param charArraySet
	 * @return
	 */
	public abstract List<String> tokenizeAsList(String text, CharArraySet charArraySet);
	
	/**
	 * @param text
	 * @param charArraySet
	 * @return
	 */
	public String[] tokenize(String text, CharArraySet charArraySet) {
		return ArrayUtils.listToArrayString(tokenizeAsList(text, charArraySet));
	}

	/**
	 * @param text
	 * @return
	 */
	public String[] tokenize(String text) {
		return ArrayUtils.listToArrayString(tokenizeAsList(text));
	}

	/**
	 * @param text
	 * @return
	 */
	public List<String> tokenizeAsList(String text) {
		return tokenizeAsList(text, getCharArraySet());
	}

	/**
	 * @param resourceUrl
	 * @param comment
	 * @return
	 */
	public CharArraySet loadCharArraySet(String resourceUrl, String comment){
		CharArraySet charArraySet = new CharArraySet(16, true);
		String stopwordString = FileUtil.read(resourceUrl);
		String[] stopwordArray = stopwordString.split(RegexUtil.NEWLINE_REGEX);
		if(comment == null) comment = COMMENT;
		for(String s : stopwordArray){
			if(!s.startsWith(comment));
				charArraySet.add(s);
		}
		return charArraySet;
	}
	
	/**
	 * @return
	 */
	public CharArraySet getCharArraySet() {
		return charArraySet;
	}
	
	/**
	 * @param charArraySet
	 */
	public void setCharArraySet(CharArraySet charArraySet) {
		this.charArraySet = charArraySet;
	}
	
}
