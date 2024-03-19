package com.example.calendar

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val locale = Locale.ENGLISH
    private val lastDayInCalendar = Calendar.getInstance(locale)
    private val sdf = SimpleDateFormat("MMMM yyyy", locale)
    private val cal = Calendar.getInstance()

    private val currentDate = Calendar.getInstance(locale)
    private val currentDay = currentDate[Calendar.DAY_OF_MONTH]
    private val currentMonth = currentDate[Calendar.MONTH]
    private val currentYear = currentDate[Calendar.YEAR]

    private var selectedDay: Int = currentDay
    private var selectedMonth: Int = currentMonth
    private var selectedYear: Int = currentYear

    private val dates = ArrayList<Date>()

    private val listOfActivities = ArrayList<ActivityItem>()

    private val newActivityIntent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result -> val data = result.data
        val eventDate = Calendar.getInstance(locale)
        eventDate.timeInMillis = (data!!.getStringExtra("time")?.toLong() ?: currentDate.timeInMillis)
        Log.v("newActivityDate", SimpleDateFormat("dd MM yyyy", locale).format(eventDate.time))
        val eventName: String = data!!.getStringExtra("name").toString()
        val eventImportance = data.getStringExtra("importance")
        listOfActivities.add(ActivityItem(eventDate, eventName, eventImportance!!.toInt()))
        Log.v("newActivityImportance", eventImportance)
        val selectedDate = Calendar.getInstance()
        selectedDate.set(selectedYear, selectedMonth, selectedDay)
        displayActivities(selectedDate)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(findViewById(R.id.calendar_recycler_view))

        lastDayInCalendar.add(Calendar.MONTH, 6)

        setUpCalendar()

        findViewById<Button>(R.id.calendar_prev_button)!!.setOnClickListener {
            if (cal.after(currentDate)) {
                cal.add(Calendar.MONTH, -1)
                if (cal == currentDate)
                    setUpCalendar()
                else
                    setUpCalendar(changeMonth = cal)
            }
        }

        findViewById<Button>(R.id.calendar_next_button)!!.setOnClickListener {
            if (cal.before(lastDayInCalendar)) {
                cal.add(Calendar.MONTH, 1)
                setUpCalendar(changeMonth = cal)
            }
        }

    }

    private fun setUpCalendar(changeMonth: Calendar? = null) {
        findViewById<TextView>(R.id.txt_current_month).text = sdf.format(cal.time)
        val monthCalendar = cal.clone() as Calendar
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        selectedDay =
            when {
                changeMonth != null -> changeMonth.getActualMinimum(Calendar.DAY_OF_MONTH)
                else -> currentDay
            }
        selectedMonth =
            when {
                changeMonth !=  null -> changeMonth[Calendar.MONTH]
                else -> currentMonth
            }
        selectedYear =
            when {
                changeMonth != null -> changeMonth[Calendar.YEAR]
                else -> currentYear
            }

        var currentPosition = 0
        dates.clear()
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)

        while (dates.size < maxDaysInMonth) {
            if (monthCalendar[Calendar.DAY_OF_MONTH] == selectedDay)
                currentPosition = dates.size
            dates.add(monthCalendar.time)
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }


        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        findViewById<RecyclerView>(R.id.calendar_recycler_view)!!.layoutManager = layoutManager
        val calendarAdapter = CalendarAdapter(this, dates, currentDate, changeMonth)
        findViewById<RecyclerView>(R.id.calendar_recycler_view)!!.adapter = calendarAdapter

        when {
            currentPosition > 2 -> findViewById<RecyclerView>(R.id.calendar_recycler_view)!!.scrollToPosition(currentPosition - 3)
            maxDaysInMonth - currentPosition < 2 -> findViewById<RecyclerView>(R.id.calendar_recycler_view)!!.scrollToPosition(currentPosition)
            else -> findViewById<RecyclerView>(R.id.calendar_recycler_view)!!.scrollToPosition(currentPosition)
        }

        calendarAdapter.setOnItemClickListener(object: CalendarAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val clickCalendar = Calendar.getInstance()
                clickCalendar.time = dates[position]
                selectedDay = clickCalendar[Calendar.DAY_OF_MONTH]

                displayActivities(clickCalendar)
            }
        })
    }

    fun displayActivities(displayedDate: Calendar) {
        Log.v("displayedDate", displayedDate.timeInMillis.toString())
        findViewById<Button>(R.id.btn_add_activity).setOnClickListener {
            val myIntent = Intent(this, CreatingActivity::class.java)
            myIntent.putExtra("date", SimpleDateFormat("dd.MM.yyyy", locale).format(displayedDate.time))
            myIntent.putExtra("time", displayedDate.timeInMillis.toString())
            newActivityIntent.launch(myIntent)
        }
        val activitiesDisplay = findViewById<LinearLayout>(R.id.activities_list)
        activitiesDisplay.removeAllViews()
        for (i in listOfActivities) {
            if (i.isSameDay(displayedDate)) {
                val newTextView = TextView(this)
                newTextView.text = i.getName()
                newTextView.textSize = 24f
                newTextView.setTextColor(Color.rgb((i.getImportance() / 100f * 255f).toInt(), ((100f - i.getImportance()) / 100f * 255f).toInt(), 128))
                newTextView.setOnLongClickListener {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Czy chcesz usunąć element?")
                    builder.setPositiveButton("Tak") { _, _->
                        listOfActivities.remove(i)
                        displayActivities(displayedDate)
                    }
                    builder.setNegativeButton("Nie") {_, _->
                        Toast.makeText(this, "Nie usunięto wpisu", Toast.LENGTH_SHORT).show()
                    }
                    val dialog = builder.create()
                    dialog.show()
                    true
                }
                activitiesDisplay.addView(newTextView)
            }
        }
//        findViewById<TextView>(R.id.textView3).setOnClickListener {
//            findViewById<TextView>(R.id.textView3).text = "Skasowano"
//        }
//        findViewById<TextView>(R.id.textView3).text = displayedDate.get(Calendar.DAY_OF_MONTH).toString() + "-" + displayedDate.get(Calendar.MONTH) + "-" + displayedDate.get(Calendar.YEAR)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("numberOfActivities", listOfActivities.size)
        for (i in listOfActivities.indices) {
            outState.putLong("time$i", listOfActivities[i].getTime())
            outState.putString("name$i", listOfActivities[i].getName())
            outState.putInt("importance$i", listOfActivities[i].getImportance())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val n = savedInstanceState.getInt("numberOfActivities")
        for (i in 0 until n) {
            val restoredTime = savedInstanceState.getLong("time$i")
            val restoredName = savedInstanceState.getString("name$i")
            val restoredImportance = savedInstanceState.getInt("importance$i")
            val restoredCalendar = Calendar.getInstance()
            restoredCalendar.timeInMillis = restoredTime
            listOfActivities.add(ActivityItem(restoredCalendar, restoredName!!, restoredImportance))
        }
        displayActivities(currentDate)
    }
}










//package com.example.calendar
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.CalendarView
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.LinearSnapHelper
//import androidx.recyclerview.widget.RecyclerView
//import java.util.*
//import kotlin.collections.ArrayList
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        val snapHelper = LinearSnapHelper()
//        snapHelper.attachToRecyclerView(recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.adapter = RecyclerAdapter(generateMonths())
//    }
//
//    private fun generateMonths(): List<CalendarData> {
//        val itemList: MutableList<CalendarData> = ArrayList()
//        val helpCalendar = Calendar.getInstance()
//        helpCalendar.set(Calendar.DAY_OF_MONTH, 1)
//        helpCalendar.set(Calendar.MONTH, 1)
//        helpCalendar.set(Calendar.YEAR, 2023)
//
//        for (i in 0..11) {
//            val daysInMonth = helpCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
//            val newMonth = CalendarData(helpCalendar.timeInMillis, helpCalendar.timeInMillis + ((daysInMonth.toLong() - 1) * 86400000))
//            itemList.add(newMonth)
////            System.out.println("Miesiąc: " + helpCalendar.toString())
//            helpCalendar.set(Calendar.MONTH, (helpCalendar.get(Calendar.MONTH) + 1) % 12)
//            if (helpCalendar.get(Calendar.MONTH) == 0)
//                helpCalendar.set(Calendar.YEAR, helpCalendar.get(Calendar.YEAR) + 1)
//
//        }
//        return itemList
//    }
//}