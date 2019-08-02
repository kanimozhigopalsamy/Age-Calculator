package com.agecalculator.main;

/**
 * This Class will find out the Age for any given date.
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class AgeCalculator {
	public static int daysCounter = 0;
	public static final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your Birthyear");
		int year = scanner.nextInt();
		System.out.println("Enter your BirthMonth");
		int month = scanner.nextInt();
		System.out.println("Enter your BirthDay");
		int day = scanner.nextInt();
		scanner.close();

		testMyAge(new Date(), year, day, month);

	}

	public static void testMyAge(Date date, int year, int day, int month) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String currentYear = dateFormat.format(date);

		// Check Birth Year is leap or not only if dob before 28 feb.
		if (month < 2 || (month == 2 && day <= 28)) {
			if (isLeapYear(year)) {
				daysCounter = daysCounter + 1;
			}
		}

		// Check any leap year available in between dob+1 and current year-1.
		for (int i = year + 1; i < Integer.valueOf(currentYear) - 1; i++) {
			if (isLeapYear(i)) {
				daysCounter = daysCounter + 1;
			}
		}

		// Adding all counted days to daysCounter. (Leap year count + Birth year count +
		// Current Year Count + In between Year counts)
		daysCounter = daysCounter + getMajorCount(year, currentYear) + getMainDays(date, year, month, day);

		System.out.println("Your total lived days: " + daysCounter);
	}

	private static int getMainDays(Date date, int year, int month, int day) {

		int totalDays = 0;
		int currentMonthDays = daysOfMonth[month - 1] - day;
		for (int i = month + 1; i <= 12; i++) {
			totalDays = totalDays + daysOfMonth[i - 1];
		}
		int birthYearDays = totalDays + currentMonthDays;
		Calendar calc = Calendar.getInstance();
		return birthYearDays + calc.get(Calendar.DAY_OF_YEAR);
	}

	private static int getMajorCount(int year, String currentYear) {

		return ((Integer.valueOf(currentYear) - year) - 1) * 365;
	}

	private static boolean isLeapYear(int i) {

		if (i % 4 == 0) {
			if (i % 100 == 0) {
				if (i % 400 == 0) {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}
}
