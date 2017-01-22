package com.masasdani.sengon.util;

import java.util.List;

/**
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public class ArrayUtils {

	/**
	 * @param list
	 * @return
	 */
	public static String[] listToArrayString(List<String> list){
		String[] strings = new String[list.size()];
		strings = list.toArray(strings);
		return strings;
	}
	
	/**
	 * @param list
	 * @return
	 */
	public static Object[] listToArray(List<Object> list){
		Object[] strings = new Object[list.size()];
		strings = list.toArray(strings);
		return strings;
	}
}
