package com.example.calendar

class CalendarData(private val startDate: Long, private val endDate: Long) {
    fun getStartDate(): Long {
        return startDate
    }
    fun getEndDate(): Long {
        return endDate
    }
}
