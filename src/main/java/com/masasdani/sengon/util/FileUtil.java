package com.masasdani.sengon.util;

import java.io.*;

/**
 * @author masasdani
 * Created Date Oct 4, 2015
 */
public class FileUtil {
	
	/**
	 * @param filename
	 * @param content
	 */
	public static void save(String filename, String content){
		File file = new File(filename);
		String url = file.getAbsolutePath();
		try {
			FileWriter writer = new FileWriter(url);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param dir
	 * @param filename
	 * @param content
	 */
	public static void save(String dir, String filename, String content){
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		String url = file.getAbsolutePath()+"/"+filename;
		try {
			FileWriter writer = new FileWriter(url);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param file
	 * @param content
	 */
	public static void append(File file, String content){
		try {
			FileWriter fileWritter = new FileWriter(file, true);
	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	        bufferWritter.write(content);
	        bufferWritter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param url
	 * @param content
	 */
	public static void append(String url, String content){
		try {
			FileWriter fileWritter = new FileWriter(url, true);
	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	        bufferWritter.write(content);
	        bufferWritter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param dir
	 * @param filename
	 * @return
	 */
	public static File createNewFile(String dir, String filename){
		File fileDir = new File(dir);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		String url = fileDir.getAbsolutePath()+"/"+filename;
		File file = new File(url);
		boolean success = false;
		if(!file.exists()){
			try {
				success = file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(success) return file;
		else return null;
	}
	
	/**
	 * @param filename
	 * @return
	 */
	public static String read(String filename) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append('\n');
	            line = br.readLine();
	        }
	        return sb.toString();	        
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * @param inputStream
	 * @return
	 */
	public static String read(InputStream inputStream) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append('\n');
	            line = br.readLine();
	        }
	        return sb.toString();	        
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
}
