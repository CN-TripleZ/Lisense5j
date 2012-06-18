package com.license.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat DEFAULT_DATE_FORMATE = new SimpleDateFormat("yyyy-MM-dd");

	public static Date toDate(String dateStr) {
		try {
			return DEFAULT_DATE_FORMATE.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDate(Date date) {
		return DEFAULT_DATE_FORMATE.format(date);
	}

	public static int dayLeft(Date date) {
		long time = date.getTime() - System.currentTimeMillis();
		int dayLeft = 1 + (int) (time / 86400000L);
		return dayLeft;
	}
}
