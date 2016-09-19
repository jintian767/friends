package com.wsx.friends.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd hh:mm:ss";
	
	public static String getNowDate() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS);
		return dateFormat.format(date);
	}
}
