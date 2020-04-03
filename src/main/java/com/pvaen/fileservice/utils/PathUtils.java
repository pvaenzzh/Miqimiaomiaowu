package com.pvaen.fileservice.utils;

import java.util.UUID;

public class PathUtils {
	
	public static String nextPath() {
		String dateString = TimeUtils.getDateString();
		String string = UUID.randomUUID().toString();
		return dateString + "/" + string;
	}
	

}
