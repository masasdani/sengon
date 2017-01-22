package com.masasdani.sengon.util;

import java.util.regex.Pattern;

/**
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public class RegexUtil {
	
	public static final String NEWLINE_REGEX = "\\r?\\n";
	public static final String QUOTED_TWEET_REGEX = "((\"@|\" @)*(\"$))";
	public static final String RT_TWEET_REGEX = "(RT@|RT @)";
	public static final String MENTION_HASTAG_SHORTEN_URL_REGEX = 
			"(?:^|\\s|[\\p{Punct}&&[^/]])(@[\\p{L}0-9-_]+)"
			+ "|(?:^|\\s|[\\p{Punct}&&[^/]])(#[\\p{L}0-9-_]+)"
			+ "|(?:^|\\s|[\\p{Punct}&&[^/]])(bit.ly[\\p{L}0-9-_]+)"
			+ "|(?:^|\\s|[\\p{Punct}&&[^/]])(t.co[\\p{L}0-9-_]+)"
			+ "|(?:^|\\s|[\\p{Punct}&&[^/]])(haha[\\p{L}0-9-_]+)"
			+ "|((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
	
	public static final String INTERNET_URL_REGEX = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
	
	public static final String TWITTER_MENTION_REGEX = "(?:^|\\s|[\\p{Punct}&&[^/]])(@[\\p{L}0-9-_]+)";
	public static final String TWITTER_HASHTAG_REGEX = "(?:^|\\s|[\\p{Punct}&&[^/]])(#[\\p{L}0-9-_]+)";
	
	public static final String TWITTER_URL_MENTION_REGEX = 
			"(?:^|\\s|[\\p{Punct}&&[^/]])(@[\\p{L}0-9-_]+)"
			+ "|((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
	
	public static final String IMAGE_URL_REGEX = "(http(s?):/)(/[^/]+)+" + "\\.(?i)(jpg|jpeg|png)";
	
	public static final String HAS_LETTER_REGEX = ".*[a-zA-Z]+.*";
	
	public static final String NO_LETTER_REGEX = "(?:^|\\s|[\\p{Punct}&&[^/]])(.[^a-zA-Z].)";
	
	public static final String WORD_REGEX = "^[a-zA-Z]+$";
	
	/**
	 * @param input
	 * @param regex
	 * @return
	 */
	public static boolean patternMatcher(String input, String regex){
		return Pattern.compile(regex).matcher(input).find();
	}
	
	/**
	 * @param input
	 * @param regex
	 * @return
	 */
	public static String removeMatchRegex(String input, String regex){
		return input.replaceAll(regex, "");
	}
	
}
