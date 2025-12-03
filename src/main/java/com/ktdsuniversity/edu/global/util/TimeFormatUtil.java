package com.ktdsuniversity.edu.global.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeFormatUtil {

	public static String format(String isoDateTime) {
		Instant instant = Instant.parse(isoDateTime);
		LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		LocalDateTime now = LocalDateTime.now();

		long seconds = ChronoUnit.SECONDS.between(dateTime, now);
		long minutes = ChronoUnit.MINUTES.between(dateTime, now);
		long hours = ChronoUnit.HOURS.between(dateTime, now);
		long days = ChronoUnit.DAYS.between(dateTime, now);

		if (seconds < 60) {
			return "방금 전";
		}

		if (minutes < 60) {
			return minutes + "분 전";
		}

		if (hours < 24) {
			return hours + "시간 전";
		}

		if (days < 7) {
			return days + "일 전";
		}

		int thisYear = now.getYear();
		int targetYear = dateTime.getYear();

		if (thisYear == targetYear) {
			return dateTime.format(DateTimeFormatter.ofPattern("MM월 dd일"));
		}

		return dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
	}
}