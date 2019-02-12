package com.galdino.date;

import java.sql.Date;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Example {

	public static void main(String[] args) {
		//Clock
		//Clock provides access to the current date and time.
		Clock clock = Clock.systemDefaultZone();
		long millis = clock.millis();
		System.out.println("clock.millis() --> " + millis);
		
		Instant instant = clock.instant();
		System.out.println("clock.instant() --> " + instant);
		java.util.Date legacyDate = Date.from(instant);
		System.out.println("Date.from() --> " + legacyDate);
		
		//Timezones
		//Timezones are represented by a ZoneId. They can easily be accessed via static factory methods.
		ZoneId zoneId1 = ZoneId.of("Europe/Berlin");
		ZoneId zoneId2 = ZoneId.of("Brazil/East");
		System.out.println(zoneId1.getRules());
		System.out.println(zoneId2.getRules());
		
		//LocalTime
		//LocalTime represents a time without a timezone, e.g. 10pm or 17:30:15.
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);
		System.out.println("LocalTime.now().isBefore() --> " + now1.isBefore(now2));
		
		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
		
		System.out.println("ChronoUnit.HOURS.between() --> " + hoursBetween);
		System.out.println("ChronoUnit.MINUTES.between() --> " + minutesBetween);
		
		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println("LocalTime.of() --> " + late);
		
		//LocalDate
		//LocalDate represents a distinct date, e.g. 2014-03-11.
		LocalDate today = LocalDate.now();
		System.out.println("LocalDate.now() --> " + today);
		
		LocalDate tomorrow = today.plusDays(1);
		System.out.println("LocalDate.now().plusDays() --> " + tomorrow);
		
		LocalDate yesterday = today.minusDays(1);
		System.out.println("LocalDate.now().minusDays() --> " + yesterday);
		
		LocalDate birthDay = LocalDate.of(2019, Month.MARCH, 11);
		DayOfWeek dayOfWeek = birthDay.getDayOfWeek();
		System.out.println("LocalDate.of().getDayOfWeek() --> " + dayOfWeek);
	}

}
