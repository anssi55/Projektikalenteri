package com.mycompany.projektikalenteri;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;



public class Kalenteri {
	Date date;
	int year, month, day, dayOfWeek, dayOfMonth, weekOfYear, weekOfMonth;
	SimpleDateFormat sdf;
	Calendar calendar;
	ResourceBundle messages;
	
	



	public Kalenteri(ResourceBundle messages) {
		this.messages = messages;
		sdf = new SimpleDateFormat("yyyy M dd HH:mm:ss");	
		calendar = new GregorianCalendar();
		year       = calendar.get(Calendar.YEAR);
		month      = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); 
		dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
		weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);
		
	}
	public int getDayOfWeek() {
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	public int getDayOfMonth() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	public int getFirstDayOfMonth() {
		Calendar calendar2 = new GregorianCalendar();
		calendar2.set(calendar2.DAY_OF_MONTH, 1);
		return calendar2.get(Calendar.DAY_OF_WEEK);
	}
	public int getMaxDaysInMonth() {
		return calendar.getActualMaximum(calendar.DAY_OF_MONTH);
	}

	public Date getDate() {
		System.out.println(sdf.format(calendar.getTime()));
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMonth() {
		System.out.println(calendar.MONTH);
		return calendar.MONTH;
	}
	public String getMonthName() {
		switch(calendar.get(calendar.MONTH)) {
			case 0 :
				return messages.getString("january");
			case 1 :
				return messages.getString("february");
			case 2 :
				return messages.getString("march");
			case 3 :
				return messages.getString("april");
			case 4 :
				return messages.getString("may");
			case 5 :
				return messages.getString("june");
			case 6 :
				return messages.getString("july");
			case 7 :
				return messages.getString("august");
			case 8 :
				return messages.getString("september");
			case 9 :
				return messages.getString("october");
			case 10 :
				return messages.getString("november");
			case 11 :
				return messages.getString("december");
				
		} 
		return "";
	}

	public void setMonthToNext() {
		calendar.add(calendar.MONTH, 1);
	}
	public void setMonthToPrevious() {
		calendar.add(calendar.MONTH, -1);
	}
	public int getYear() {
		return calendar.get(calendar.YEAR);
	}
}

