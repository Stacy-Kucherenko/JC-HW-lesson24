package lviv.lgs.ua;

import java.io.Serializable;
import java.util.Scanner;

public enum Days implements Serializable{

	
	MONDAY(1), TUESDAY(2), WENSDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);
	
	int serialNumber;
	
	Days (int serailNumber) {
		this.serialNumber = serailNumber;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}
	
	public String toLiteral(boolean fullType) {
		String dayToLiteralFull;
		String dayToLiteralShort;
		
		switch (Days.this) {
		case MONDAY:
			dayToLiteralFull = "Monday";
			dayToLiteralShort = "Mon.";
			break;
		case TUESDAY :
			dayToLiteralFull = "Tuesday";
			dayToLiteralShort = "Tue.";
			break;
		case WENSDAY:
			dayToLiteralFull = "Wensday";
			dayToLiteralShort = "Wed.";
			break;
		case THURSDAY:
			dayToLiteralFull = "Thursday";
			dayToLiteralShort = "Thu.";
			break;
		case FRIDAY:
			dayToLiteralFull = "Friday";
			dayToLiteralShort = "Fri.";
			break;
		case SATURDAY:
			dayToLiteralFull = "Saturday";
			dayToLiteralShort = "Sat.";
			break;
		case SUNDAY:
			dayToLiteralFull = "Sunday";
			dayToLiteralShort = "Sun.";
			break;
			
			default:
				dayToLiteralFull = dayToLiteralShort = "";
				break;
		}
		
		if (fullType)
			return dayToLiteralFull;
		else
			return dayToLiteralShort;
	}
	
	public static Days inputDay() {
		Scanner scanner = new Scanner(System.in);
		Integer returnedNumber = 0;
		Days foundDay = null;
		
		System.out.println("Enter the number of days of the week: ");
		
		if (scanner.hasNextInt()) {
			int nextInt = scanner.nextInt();
			
			if (nextInt > 0 && nextInt <= Days.values().length) {
				returnedNumber = nextInt;
			} else 
				System.err.println("The  number of days must be between 1 and 7!");
			
			for (Days day : Days.values()) {
				
				if (day.getSerialNumber() == returnedNumber) {
					foundDay = day;
				}
				
			}
			
			
		} 
		return foundDay;
	}
}
