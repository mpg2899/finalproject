package final_project;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class monthInformation {
	int daysInMonth;
	Calendar cal1;
	int year, month, day, dayofweek;
	
	
	// Constructor
	public monthInformation() {
		year = 2013;
		month = 11;
		day = 1;
		cal1 = new GregorianCalendar(year, month, day);
		dayofweek = cal1.get(Calendar.DAY_OF_WEEK);
	}
	
	public int getDays() {

		daysInMonth = cal1.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		return daysInMonth;
	}
	
	public int startDays() {
		
		return 0;
	}
	
	public int stopDays() {
		
		return 0;
	}
	
	
}
