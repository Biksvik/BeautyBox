package com.example.beautybox.mainScreens.mainScreen.calendar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybox.R
import com.example.beautybox.databinding.ItemDiarySelectBinding
import com.example.beautybox.databinding.ItemHorisontalCalendarDateBinding
import java.util.Calendar

class CalendarAdapter(
    private val calendarInterface: CalendarInterface,
    private val list: ArrayList<CalendarData>
): RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    var pos: Int = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarAdapter.CalendarViewHolder {
        val binding = ItemHorisontalCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarAdapter.CalendarViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CalendarViewHolder(
        private val binding: ItemHorisontalCalendarDateBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(calendarDataModel: CalendarData, position: Int) {

            val calendarDay = binding.tvCalendarDay
            val calendarDate = binding.tvCalendarDate
            val cardView = binding.root
            if (pos == position) {
                calendarDataModel.isSelected = true
            }
            if (calendarDataModel.isSelected) {
                pos = -1
                calendarDay.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.white)
                )
                calendarDate.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.white)
                )
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.blue)
                )
            } else {
                calendarDay.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.blue)
                )
                calendarDate.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.blue)
                )
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.white)
                )
            }
            calendarDay.text = calendarDataModel.calendarDay
            calendarDate.text = calendarDataModel.calendarData

            cardView.setOnClickListener {
                calendarInterface.onSelected(calendarDataModel, adapterPosition, calendarDate)
            }
        }
    }

    interface CalendarInterface {
        fun onSelected(calendarData: CalendarData, position: Int, day: TextView)
    }

    fun setPosition(pos: Int) {
        this.pos = pos
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(calendarList: ArrayList<CalendarData>) {
        list.clear()
        list.addAll(calendarList)
        notifyDataSetChanged()
    }
}