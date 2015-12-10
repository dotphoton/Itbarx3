package com.itbarx.utils;

import android.util.Log;
import android.util.Pair;

import com.itbarx.enums.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class DateUtility {

	private static final long HOUR = 1000L * 60L * 60L;
	private static final long DAY = 1000L * 60L * 60L * 24L;
	private static final long MONTH = 1000L * 60L * 60L * 24L * 30L;
	private static final double YEAR = 1000D * 60D * 60D * 24D * 365D;

	public static String ConvertToDateString(Date date) {

		String dateStr = "";
		DateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			dateStr = readFormat.format(date);

		} catch (Exception e) {
			dateStr = null;
		}
		return dateStr;

	}

	public static String ConvertToPrintString(Date date) {

		String dateStr = "";
		DateFormat readFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

		try {
			dateStr = readFormat.format(date);

		} catch (Exception e) {
			dateStr = null;
		}
		return dateStr;

	}

	public static String ConvertToDateStringWS(Date date) {
		//YYYYMMDDHHMMSS
		String dateStr = "";
		DateFormat readFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		try {
			dateStr = readFormat.format(date);

		} catch (Exception e) {
			dateStr = null;
		}
		return dateStr;

	}

	public static Date ConverToStringDate(String dateString) {
		DateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (dateString.indexOf('/') != -1) {
			if (dateString.indexOf('/') <= 3) {
				readFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			} else {
				readFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			}
		}

		Date date = null;
		try {
			date = readFormat.parse(dateString);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;

	}

	public static Date getNowDate() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/**
	 * WS den donen Stringi tarih formatında Stringe  cevirir.
	 *
	 * @param dateString YYYYMMDDHHMMSS
	 * @return String
	 */
	public static String ConvertWSStringtoDate(String dateString) {
		String formattedDateString = "";

		if (dateString.trim().length() >= 14) {
			formattedDateString = dateString.substring(6, 8) + "/" + dateString.substring(4, 6) + "/" + dateString.substring(0, 4) + " " + dateString.substring(8, 10) + ":" + dateString.subSequence(10, 12) + ":" + dateString.substring(12, 14);

			return formattedDateString;
		} else if (dateString.trim().length() >= 8) {
			formattedDateString = dateString.substring(6, 8) + "/" + dateString.substring(4, 6) + "/" + dateString.substring(0, 4) + " 00:00:00";
			return formattedDateString;

		} else {
			return "28-08-1988 22:22:22";

		}
	}

	/**
	 * Tarih icinden saati ayiklar
	 *
	 * @param date
	 * @return HH:MM:SS
	 */
	public static String toShortTimeString(Date date) {
		String timeStr = "";

		Time time = new Time(date.getTime());
		timeStr = time.toString();

		return timeStr;
	}

	public static String toShortDateString(Date date) {
		String dateStr = "";
		DateFormat shortFormatter = new SimpleDateFormat("dd-MM-yyyy");
		dateStr = shortFormatter.format(date).toString();

		return dateStr;
	}

	public static String toShortDateStringForPrinter(Date date) {
		String dateStr = "";
		DateFormat shortFormatter = new SimpleDateFormat("dd.MM.yyyy");
		dateStr = shortFormatter.format(date).toString();

		return dateStr;
	}

	public static Date getSystemDate() {
		return Calendar.getInstance().getTime();
	}

	public static String getDayFromDate(Date date) {
		String day = "28";
		day = (String) android.text.format.DateFormat.format("dd", date); //20

		return day;
	}

	public static String getYearFromDate(Date date) {
		String year = "1988";
		year = (String) android.text.format.DateFormat.format("yyyy", date); //20

		return year;
	}

	public static String getMonthFromDate(Date date) {
		String month = "08";
		month = (String) android.text.format.DateFormat.format("MM", date); //8

		return month;
	}

	public static Date itBarxDateParser(String time) {
		if (time == null || time.equals("")) {
			return null;
		}
		Calendar calendar = null;
		try {
			String year = time.substring(0, 4);
			String month = time.substring(5, 7);
			String day = time.substring(8, 10);
			String hour = time.substring(11, 13);
			String min = time.substring(14, 16);
			String sec = time.substring(17, 19);
			Log.d("time", year + "  " + month + "  " + day + "  " + hour + "  " + min + "  " + sec);
			calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(min), Integer.parseInt(sec));

		} catch (Exception e) {
			Log.e("itBarxDateParser()", "String Parse Error!");
		}
		return new Date(calendar.getTimeInMillis());

	}

	public static Pair<String, Long> getDateDiff(Date date1, Date date2) {

		long diffInMillies = date2.getTime() - date1.getTime();
		if (diffInMillies < 0) {
			Log.e("gateDateDiff()", "Minus time");
			return new Pair(DateUtils.MINUS.name(), 0);
		}
		if (diffInMillies < HOUR) {
			long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if (diff <= 1) {
				return new Pair(DateUtils.MINUTE.toString(), diff);
			} else {
				return new Pair(DateUtils.MINUTES.toString(), diff);
			}

		} else if (diffInMillies < DAY) {
			long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if (diff <= 1) {
				return new Pair(DateUtils.HOUR.toString(), diff);
			} else {
				return new Pair(DateUtils.HOURS.toString(), diff);
			}
		}
		else if (diffInMillies < MONTH)
		{
			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if (diff <= 1) {
				return new Pair(DateUtils.DAY.toString(), diff);
			} else {
				return new Pair(DateUtils.DAYS.toString(), diff);
			}
		}
		else if (diffInMillies < YEAR) {
			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			diff /= 30;
			if (diff <= 1) {
				return new Pair(DateUtils.MONTH.toString(), diff);
			} else {
				return new Pair(DateUtils.MONTHS.toString(), diff);
			}


		} else {
			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			diff /= 365L;

			if (diff <= 1) {
				return new Pair(DateUtils.YEAR.toString(), diff);
			} else {
				return new Pair(DateUtils.YEARS.toString(), diff);
			}

		}

	}

}
