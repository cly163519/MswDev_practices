// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN131 assignment.
// You may not distribute it in any other way without permission.

/* Code for SWEN131 Individual Project - if
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

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
    	int day = UI.askInt("Enter the day: ");
    	int month = UI.askInt("Enter the month: ");
    	int year = UI.askInt("Enter the year: ");
    	if(day >= 1 && day <= 31) {
    		UI.println("Day " + day);
    	}else {
    		UI.println("Invalid day!");
    	}

    	if(month >= 1 && month <= 12) {
    		UI.println("Month " + month);
    	}else {
    		UI.println("Invalid month.");
    	}
    	
    	if(year >= 1) {
    		UI.println("Year " + year);
    	}else {
    		UI.println("Invalid year.");
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
    public void validateDateCompletion(){
        /*# YOUR CODE HERE */
    	int day = UI.askInt("Enter the day: ");
    	int month = UI.askInt("Enter the month: ");
    	int year = UI.askInt("Enter the year: ");
    	
    	/*if(day >= 1 && day <= 31) {
    		UI.println("Day " + day);
    	}else {
    		UI.println("Invalid day!");
    	}

    	if(month >= 1 && month <= 12) {
    		UI.println("Month " + month);
    	}else {
    		UI.println("Invalid month.");
    	}
    	
    	if(year >= 1) {
    		UI.println("Year " + year);
    	}else {
    		UI.println("Invalid year.");
    	}
    	
    	if((month >= 1) && (month <= 12) && (month != 2)||(month == 4) || (month == 6) || (month == 9) || (month == 11)) {
    		UI.println("Day should be 30.");
    	}else if((month >= 1) && (month <= 12) && (month == 1)||(month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
    		UI.println("Day should be 31.");
    	}else if(day == 29){
    		UI.println("A leap year.");	
    	}else {
    		UI.println("Invalid day.");
    	}
    	
    	if (day == 29) {
    		UI.println("A leap year");
    	}else if ((year%4 == 0) && (year%100 != 0) && (year%400 == 0) ){
    		UI.println("A leap year");
    	}
    	else {
    		UI.println("Common year");
    	}

    }
    */
    	if(year < 1) {
    		UI.println("Year is invalid");
    		return;
    	}
    	if(month >= 1 && month <= 12) {
    		int monthDays = calculateMonthDays(year, month);
    		if(day < 1 || day > monthDays) {
    			UI.println("Day is invalid for month"+month+" of "+year);
    		}
    	}else {
    		UI.println("Invalid month " + month);}
    	}
    	
    	public int calculateMonthDays(int year, int month) {
    		if(isLeapYear(year) && month ==2) {
    			return 29;
    		}else if(month == 2) {
    			return 28;
    		}
    		if( month ==1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
    			return 31;
    		}
    		return 30;
    	}
    	
    	public boolean isLeapYear(int y) {
    		if(y % 4 != 0) {
    			return false;
    		}
    		if(y % 100!=0) {
    			return true;
    		}
    		if(y % 400 == 0) {
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
    	int day = UI.askInt("Enter the day: ");
    	int month = UI.askInt("Enter the month: ");
    	int year = UI.askInt("Enter the year: ");
    	
    	if((year > 0 && year <= 1582) && (month > 0 && month <= 11) && (day > 0) && (day <= 31)) {//How to check 11.31?
    		UI.println("Gregorian calendar");
    	}
    	else if((year == 1582) && (month == 12) && (day > 0 && day <= 9)) {//France transition calendar
    		UI.println("Gregorian calendar");
    	}else if((year == 1582) && (month == 12) && (day >= 10) && (day < 20)) {
    		UI.println("Invalid day");
    	}else if((year == 1582) && (month == 12) && (day >= 20) && (day <= 31)){
    		UI.println("Julian calendar");
    	}else if((year > 1582) && (month > 0 && month <= 12) && (day > 0) && (day <= 31)) {
    		UI.println("Julian calendar");
    	}else {
    		UI.println("Invalid day");
    	}
    	
    }

    /** ---------- The code below is already written for you ---------- **/
    /** Constructor: set up user interface */
    public DateValidator(){
        UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Validate Date Core", this::validateDateCore );
        UI.addButton("Validate Date Completion", this::validateDateCompletion );//Add content
        UI.addButton("Validate Date Challenge", this::validateDateChallenge);
        UI.addButton("Quit", UI::quit );
        UI.setDivider(1);       // Expand the text area
        
    }

    public static void main(String[] args) {
    	new DateValidator();
    }


}

