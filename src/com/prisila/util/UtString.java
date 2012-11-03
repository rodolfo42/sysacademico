package com.prisila.util;

public class UtString {
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	public static boolean notEmptyOrNull(String str) {
		return !isNullOrEmpty(str);
	}
	
}
