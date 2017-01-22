package com.masasdani.sengon.tokenizer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public class ThaiTokenizer extends Tokenizer {

	@Override
	public List<String> tokenizeAsList(String text, CharArraySet charArraySet) {
		List<String> list = new ArrayList<String>();
		try {
			ThaiAnalyzer analyzer = new ThaiAnalyzer();
			TokenStream stream  = analyzer.tokenStream(null, new StringReader(text));
			if(charArraySet != null){
				stream = new StopFilter(stream, charArraySet);
			}
			stream.reset();
		    while (stream.incrementToken()) {
		    	list.add(stream.getAttribute(CharTermAttribute.class).toString());
		    }
		    stream.close();
		    analyzer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
}
