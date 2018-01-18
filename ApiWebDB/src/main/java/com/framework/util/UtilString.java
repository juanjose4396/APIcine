package com.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.codec.binary.Base64;

public class UtilString {

	public static String paddindRea(String text, Integer size) {
		String mas = "";
		for (int i = text.length(); i < size; i++) {
			mas += "0";
		}
		return mas + text;
	}
	
	public static String convertBase64(String url) {
		String imageString="";
		
		File f = new File(url);	//change path of image according to you
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			
			byte byteArray[] = new byte[(int)f.length()];
			
			fis.read(byteArray);
			
			imageString = Base64.encodeBase64String(byteArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return imageString;
	}

}
