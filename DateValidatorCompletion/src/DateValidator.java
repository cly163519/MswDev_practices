import ecs100.*;

/**
 * Reads a date from the user as three integers, and then checks that the date is valid
 */

public class DateValidator {

    /**
     * Asks user for a date, then determines whether the date
     *    specified by the three integers is a valid date.
     * For the Core, you may assume that
     * - All months have 31 days, numbered 1 to 31
     * - The months run from 1 to 12
     * - Years start from 1
     */
    public void validateDateCore(){
        /*# YOUR CODE HERE */
    		int day = UI.askInt("Day");
    		int month = UI.askInt("Month");
    		int year = UI.askInt("Year");
    		
    		// Approach 1
    		if (year < 1) {
    			UI.println("Year is invalid");
    		} else if (month < 1 || month > 12) {
    			UI.println("Month is invalid");
    		} else if ( !(day >= 1 && day <= 31) ) {
    			UI.println("Day is invalid");
    		} else {
    			UI.println("Date is valid");
    		}
    		
    		// Approach 2
    		if (year >= 1 && (month >= 1 && month <= 12) && (day >= 1 && day <= 31)) {
    			UI.println("Date is valid");
    		} else {
    			UI.println("Date is invalid");
    		}
    		
    		// Approach 3
    		if (year < 1 || month < 1 || month > 12 || day < 1 || day > 31) {
    			UI.println("Date is invalid");
    		}
    		
    		// Approach 4
    		boolean isValid = true;
    		if (year < 1) {
    			UI.println("Year is invalid");
    			isValid = false;
    		}
    		if (month < 1 || month > 12) {
    			UI.println("Month is invalid");
    			isValid = false;
    		}
    		if (day < 1) {
    			UI.println("Day is invalid (<1)");
    			isValid = false;
    		}
    		if (day > 31) {
    			UI.println("Day is invalid (>31)");
    			isValid = false;
    		}
    		if (isValid) {
    			UI.println("Date is valid");
    		}
    }

    /**
     * Asks user for a date, then determines whether the date
     *    specified by the three integers is a valid date.
     * For the Completion, you should also check that
     * - Months have the correct number of days
     * - On leap years February should have 29 days.
     *    A year is a leap year if:
     *       - The year can be evenly divided by 4 but not 100
     *       - The year can be evenly divided by 400
     */
	public void validateDateCompletion() {
		/* # YOUR CODE HERE */
		int year = UI.askInt("Year");
		int month = UI.askInt("Month");
		int day = UI.askInt("Day");
		if (year < 1) {
			UI.println("Year is invalid");
			return;
		}
		if (month >= 1 && month <= 12) {
			int monthDays = calculateMonthDays(year, month);
			if (day < 1 || day > monthDays) {
				UI.println("Day is invalid for month " + month + " of " + year);
			}
		} else {
			UI.println("Invalid month " + month);
		}

		//////// OR (uncomment to run)
//    	if (day < 1) {
//    		UI.println("Day is invalid - too low.");
//    	} else if (month == 4 || month == 6 || month == 9 || month == 11) {
//    		if (day > 30) {
//    			UI.println("Day is invalid - too high.");
//    		}
//    	} else if (month == 2) {
//    		if (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)) {
//    			if (day > 28) {
//    				UI.println("Day invalid - not leap year");
//    			}
//    		}
//    	} else {
//    		// Must be 31-day month
//    		if (day > 31) {
//    			UI.println("Day invalid - too high");
//    		}
//    	}

		///// OR (uncomment to run)
//    	if (day > 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
//    		UI.println("Day invalid");
//    	} else if (month == 2 && day > 28 && (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0))) {
//    		UI.println("Day invalid - no " + day + " in non-leap year");
//    	} else if (month == 2 && day > 29) {
//    		UI.println("Day invalid - no + " + day + " in February");
//    	} else if (day > 31) {
//    		UI.println("Day invalid");
//    	}
	}

	public int calculateMonthDays(int year, int month) {
		if (isLeapYear(year) && month == 2) {
			return 29;
		} else if (month == 2) {
			return 28;
		}
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		}
		return 30;
	}

	public boolean isLeapYear(int y) {
		if (y % 4 != 0) {
			return false;
		}
		if (y % 100 != 0) {
			return true;
		}
		if (y % 400 == 0) {
			return true;
		}
		return false;
	}


    /**
     * For the challenge, your program should be extended to deal with the transition from the Julian to Gregorian calendar.
     * The program should look at the date, determine whether this should be a Julian or Gregorian date, and test it appropriately.
     * You will need to find the rules of the Julian calendar yourselves.
     *
     */
    public void validateDateChallenge(){
        /*# YOUR CODE HERE */

    }

    /** ---------- The code below is already written for you ---------- **/
    /** Constructor: set up user interface */
    public DateValidator(){
        UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Validate Date Core", this::validateDateCore );
        UI.addButton("Validate Date Completion", this::validateDateCompletion );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(1);       // Expand the text area
    }

    public static void main(String[] args) {
    	new DateValidator();
    }


}

