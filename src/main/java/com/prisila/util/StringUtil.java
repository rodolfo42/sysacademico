package com.prisila.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	public static boolean notNullOrEmpty(String str) {
		return !isNullOrEmpty(str);
	}
	
	public static String formatDate(Date data, boolean comHorario) {
		String formatString = "dd/MM/yyyy";
		if(comHorario) {
			formatString = formatString.concat(" '&agrave;s' HH:mm");
		}
		SimpleDateFormat dataSimples = new SimpleDateFormat(formatString);
		dataSimples.setLenient(false);
		return dataSimples.format(data);
	}
}
