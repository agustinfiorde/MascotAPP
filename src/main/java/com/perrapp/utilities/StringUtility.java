package com.perrapp.utilities;

public final class StringUtility {

	public static boolean notNullEmpty(String s) {
		return s == null && s.isEmpty() ? false : true;
	}
	
}
