package final_project;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class monthInformation {
	int daysInMonth;
	Calendar cal1;
	int year, month, day, dayofweek;
	
	
	// Constructor
	public monthInformation(int year, int month) {
		this.year = year;
		this.month = month;
		this.day = 1;
		cal1 = new GregorianCalendar(this.year, this.month, this.day);
	}
	
	public int getDays() {

		daysInMonth = cal1.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		return daysInMonth;
	}
	
	public int startDay() {
		
		return cal1.get(Calendar.DAY_OF_WEEK);
	}
	
	
	
}
