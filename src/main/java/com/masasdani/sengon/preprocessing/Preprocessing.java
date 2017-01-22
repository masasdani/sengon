package com.masasdani.sengon.preprocessing;

import com.masasdani.sengon.util.RegexUtil;

/**
 * Class to do text preprocessing
 * 
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public class Preprocessing {

	/**
	 * convert text to lower case
	 * 
	 * @param text
	 * @return
	 */
	public static String caseFolding(String text){
		return text.toLowerCase();
	}
	
	/**
	 * remove retweet symbol, hashtag, and url
	 * 
	 * @param text
	 * @return
	 */
	public static String twitterTextCleaning(String text){
		text = RegexUtil.removeMatchRegex(text, RegexUtil.RT_TWEET_REGEX);
		text = RegexUtil.removeMatchRegex(text, RegexUtil.QUOTED_TWEET_REGEX);
		text = RegexUtil.removeMatchRegex(text, RegexUtil.MENTION_HASTAG_SHORTEN_URL_REGEX);
		return text;
	}
	
}
