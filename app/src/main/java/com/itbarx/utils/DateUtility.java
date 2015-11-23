package com.itbarx.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.Time;



public class DateUtility {



	public static String ConvertToDateString(Date date)
	{

	String	dateStr = "";
	DateFormat readFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	 try {
	    dateStr = readFormat.format(date);
	    
	 }catch (Exception e ) 
	 {
		 dateStr =null;
	  }
		return dateStr;
		
		
		
	}
	public static String ConvertToPrintString(Date date)
	{

	String	dateStr = "";
	DateFormat readFormat  = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	
	 try {
	    dateStr = readFormat.format(date);
	    
	 }catch (Exception e ) 
	 {
		 dateStr =null;
	  }
		return dateStr;
		
		
		
	}
	
	
	public static String ConvertToDateStringWS(Date date)
	{
//YYYYMMDDHHMMSS 
	String	dateStr = "";
	DateFormat readFormat  = new SimpleDateFormat("yyyyMMddHHmmss");
	
	 try {
	    dateStr = readFormat.format(date);
	    
	 }catch (Exception e ) 
	 {
		 dateStr =null;
	  }
		return dateStr;
		
		
	}
	public static Date ConverToStringDate(String dateString)
	{
		DateFormat readFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(dateString.indexOf('/')!=-1){
			if(dateString.indexOf('/')<= 3 ){
				readFormat  = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			}else
			{
				readFormat  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			}
		}
	

	    Date date = null;
	    try {
	       date = readFormat.parse( dateString );
	       
	    } catch ( ParseException e ) {
	        e.printStackTrace();
	    }
	   
	    return date;

	}
	public static Date getNowDate()
	{
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}
	/**
	 * WS den donen Stringi tarih formatında Stringe  cevirir.
	 * @param dateString YYYYMMDDHHMMSS 
	 * @return String 
	 */
	public static String ConvertWSStringtoDate(String dateString)
	{
		String formattedDateString ="";
	
		if(dateString.trim().length() >= 14)
		{
			formattedDateString = dateString.substring(6, 8)+"/"+dateString.substring(4,6)+"/"+dateString.substring(0, 4) +" " + dateString.substring(8,10) + ":"+ dateString.subSequence(10, 12) +":"+ dateString.substring(12,14); 
			
			return formattedDateString;
		}else if(dateString.trim().length() >= 8)
		{
		 formattedDateString = dateString.substring(6, 8)+"/"+dateString.substring(4,6)+"/"+dateString.substring(0, 4) +" 00:00:00" ; 
		 return formattedDateString;
				 
		}else
		{
			return "28-08-1988 22:22:22";
		
		}
	}
	/**
	 * Tarih icinden saati ayiklar
	 * @param date 
	 * @return HH:MM:SS 
	 */
	public static String toShortTimeString(Date date)
	{
		String timeStr = "" ;
		
		Time time = new Time(date.getTime());
		timeStr = time.toString();
		
		return timeStr ;
	}
	
	public static String toShortDateString(Date date)
	{
		String dateStr = "" ;
		DateFormat shortFormatter = new SimpleDateFormat("dd-MM-yyyy");
		dateStr = shortFormatter.format(date).toString();
		
		return dateStr ;
	}
	
	public static String toShortDateStringForPrinter(Date date)
	{
		String dateStr = "" ;
		DateFormat shortFormatter = new SimpleDateFormat("dd.MM.yyyy");
		dateStr = shortFormatter.format(date).toString();
		
		return dateStr ;
	}
	
	public static Date getSystemDate() {
		return Calendar.getInstance().getTime();
	}
	
	public static String getDayFromDate(Date date)
	{
		String day = "28";
		 day = (String) android.text.format.DateFormat.format("dd", date); //20
		 
		return day; 
	}
	public static String getYearFromDate(Date date)
	{
		String year = "1988";
		year = (String) android.text.format.DateFormat.format("yyyy", date); //20
		 
		return year; 
	}
	public static String getMonthFromDate(Date date)
	{
		String month = "08";
		month = (String) android.text.format.DateFormat.format("MM", date); //8
		 
		return month; 
	}
}
