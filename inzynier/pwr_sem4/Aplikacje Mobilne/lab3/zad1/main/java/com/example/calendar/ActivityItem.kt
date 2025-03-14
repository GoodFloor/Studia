package com.example.calendar

import java.util.Calendar

class ActivityItem(private val time: Calendar, private val name: String, private val importance: Int) {
    fun isSameDay(time2: Calendar): Boolean {
        if (time.get(Calendar.YEAR) == time2.get(Calendar.YEAR) && time.get(Calendar.DAY_OF_YEAR) == time2.get(Calendar.DAY_OF_YEAR))
            return true
        return false
    }
    fun getName(): String {
        return name
    }
    fun getTime(): Long {
        return time.timeInMillis
    }
    fun getImportance(): Int {
        return importance
    }
}