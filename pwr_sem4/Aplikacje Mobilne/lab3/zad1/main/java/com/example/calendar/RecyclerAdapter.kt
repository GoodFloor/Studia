package com.example.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val itemList: List<CalendarData>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var monthView: CalendarView = view.findViewById(R.id.monthView)
        var text1: TextView = view.findViewById(R.id.textView)
        var text2: TextView = view.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_month, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.monthView.minDate = itemList[position].getStartDate()
        holder.monthView.maxDate = itemList[position].getEndDate()
        holder.monthView.firstDayOfWeek = 2
        holder.monthView.date = itemList[position].getStartDate()
        holder.text1.text = (itemList[position].getStartDate() / 86400000).toString()
        holder.text2.text = (itemList[position].getEndDate() / 86400000).toString()
    }
}