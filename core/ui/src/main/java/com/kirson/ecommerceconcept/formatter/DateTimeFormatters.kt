package com.kirson.ecommerceconcept.formatter

import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.time.Duration

/**
 * Форматирует даты в виде: "06.08.2021"
 */
val UI_DATE_FORMATTER_DAY_MONTH_YEAR: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
/**
 * Форматирует даты в виде: "12 January 2019"
 */
val UI_DATE_TIME_FORMATTER_DAY_MONTH_YEAR: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
/**
 * Форматирует даты в виде: "12 January 2019, 15:00"
 */
val UI_DATE_TIME_FORMATTER_DAY_MONTH_YEAR_TIME: DateTimeFormatter = DateTimeFormatter
  .ofPattern("dd MMMM yyyy, HH:mm")
  .withZone(ZoneId.of("Europe/Moscow"))
/**
 * Форматирует даты в виде: "12 January, 15:00"
 */
val UI_DATE_TIME_FORMATTER_DAY_MONTH_TIME: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM, HH:mm")
/**
 * Форматирует даты в виде: "20 Dec"
 */
val UI_DATE_TIME_FORMATTER_DAY_MONTH: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMM")
/**
 * Форматирует время в формате 12:49
 */
val UI_TIME_FORMATTER_HOURS_MINUTES: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

/**
 * Форматирует Duration в формате 00:04
 */
val UI_DURATION_FORMATTER_MINUTES_SECONDS = object : Formatter<Duration> {
  override fun format(value: Duration): String {
    val secondsTotal = value.inWholeMilliseconds / MILLIS_IN_SECOND
    val minutes = secondsTotal / SECONDS_IN_MINUTE
    val seconds = secondsTotal % SECONDS_IN_MINUTE
    return String.format(format = "%02d:%02d", minutes, seconds)
  }
}

private const val SECONDS_IN_MINUTE = 60
private const val MILLIS_IN_SECOND = 1000
