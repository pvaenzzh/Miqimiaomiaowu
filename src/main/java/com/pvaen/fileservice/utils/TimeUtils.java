package com.pvaen.fileservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	private static final String FORMAT = "yyyyMMdd";
	
	public static Date getCurrentDate() {
		Date date = new Date();
		return 	date;
	}
	
	public static String getDateString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
		String format2 = simpleDateFormat.format(new Date());
		return format2;
	}

}
