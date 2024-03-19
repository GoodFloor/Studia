package com.example.calendar

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter(private val context: Context, private val data: ArrayList<Date>, private val currentDate: Calendar, private val changeMonth: Calendar?): RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var mListener: OnItemClickListener? = null
    private var index = -1
    private var selectCurrentDate = true
    private val currentDay = currentDate[Calendar.DAY_OF_MONTH]
    private val currentMonth = currentDate[Calendar.MONTH]
    private val currentYear = currentDate[Calendar.YEAR]
    private val selectedDay =
        when {
            changeMonth != null -> changeMonth.getActualMinimum(Calendar.DAY_OF_MONTH)
            else -> currentDay
        }
    private val selectedMonth =
        when {
            changeMonth != null -> changeMonth[Calendar.MONTH]
            else -> currentMonth
        }
    private val selectedYear =
        when {
            changeMonth != null -> changeMonth[Calendar.YEAR]
            else -> currentYear
        }

    class ViewHolder(itemView: View, val listener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        var txtDay = itemView.findViewById<TextView>(R.id.txt_monthday)
        var txtWeekDay = itemView.findViewById<TextView>(R.id.txt_weekday)
        var linearLayout = itemView.findViewById<LinearLayout>(R.id.calendar_linear_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(inflater.inflate(R.layout.calendar_day, parent, false), mListener!!)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = position
        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.ENGLISH)
        val cal = Calendar.getInstance()
        cal.time = data[pos]
        val displayDay = cal[Calendar.DAY_OF_MONTH]
        val displayMonth = cal[Calendar.MONTH]
        val displayYear = cal[Calendar.YEAR]

        try {
            val dayInWeek = sdf.parse(cal.time.toString())!!
            sdf.applyPattern("EEE")
            holder.txtWeekDay!!.text = sdf.format(dayInWeek).toString()
        }
        catch (ex: ParseException) {
            Log.v("Exception", ex.localizedMessage!!)
        }
        holder.txtDay!!.text = cal[Calendar.DAY_OF_MONTH].toString()
        if (displayYear >= currentYear) {
            if (displayMonth >= currentMonth || displayYear > currentYear) {
                if (displayDay >= currentDay || displayMonth > currentMonth || displayYear > currentYear) {
                    holder.linearLayout!!.setOnClickListener {
                        index = pos
                        selectCurrentDate = false
                        holder.listener.onItemClick(pos)
                        notifyDataSetChanged()
                    }
                    if (index == pos) {
                        makeItemSelected(holder)
                    }
                    else {
                        if (displayDay == selectedDay && displayMonth == selectedMonth && displayYear == selectedYear && selectCurrentDate) {
                            makeItemSelected(holder)
                        }
                        else {
                            makeItemDefault(holder)
                        }
                    }
                }
                else {
                    makeItemDisabled(holder)
                }
            }
            else {
                makeItemDisabled(holder)
            }
        }
        else {
            makeItemDisabled(holder)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
    private fun makeItemDisabled(holder: ViewHolder) {
        holder.txtDay!!.setTextColor(ContextCompat.getColor(context, R.color.themeColor2))
        holder.txtWeekDay!!.setTextColor(ContextCompat.getColor(context, R.color.themeColor2))
        holder.linearLayout!!.setBackgroundColor(Color.WHITE)
        holder.linearLayout!!.isEnabled = false
    }
    private fun makeItemSelected(holder: ViewHolder) {
        holder.txtDay!!.setTextColor(Color.parseColor("#FFFFFF"))
        holder.txtWeekDay!!.setTextColor(Color.parseColor("#FFFFFF"))
        holder.linearLayout!!.setBackgroundColor(ContextCompat.getColor(context, R.color.themeColor1))
//        holder.linearLayout!!.isEnabled = false
    }

    private fun makeItemDefault(holder: ViewHolder) {
        holder.txtDay!!.setTextColor(Color.BLACK)
        holder.txtWeekDay!!.setTextColor(Color.BLACK)
        holder.linearLayout!!.setBackgroundColor(Color.WHITE)
        holder.linearLayout!!.isEnabled = true
    }
}