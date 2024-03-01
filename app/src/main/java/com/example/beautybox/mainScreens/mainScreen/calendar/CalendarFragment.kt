package com.example.beautybox.mainScreens.mainScreen.calendar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.core.view.isGone
import com.example.beautybox.MainActivity
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentCalendarBinding
import com.example.beautybox.mainScreens.firebaseUtils.DatabaseUtils
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CalendarFragment : Fragment(), CalendarAdapter.CalendarInterface {

    private lateinit var viewModel: CalendarViewModel
    private lateinit var binding: FragmentCalendarBinding
    private val sdf = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val calendar = Calendar.getInstance(Locale.ENGLISH)
    private var mStartD: Date? = null
    private lateinit var dbFirebase: DatabaseUtils
    private lateinit var mainActivity: MainActivity


    private val calendarAdapter = CalendarAdapter(this, arrayListOf())
    private val calendarList = ArrayList<CalendarData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(layoutInflater, container, false)
        dbFirebase = DatabaseUtils().getInstance()
        mainActivity = requireActivity() as MainActivity
        mainActivity.bottomNavView.isGone = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initCalendar()
        with(binding) {
            dbFirebase.getDiaryFromDatabase(skinCondition, mainActivity, LocalDate.now().toString(), "skinCondition")
            dbFirebase.getDiaryFromDatabase(careMorning, mainActivity, LocalDate.now().toString(), "careMorning")
            dbFirebase.getDiaryFromDatabase(careEvening, mainActivity, LocalDate.now().toString(), "careEvening")
            dbFirebase.getDiaryFromDatabase(note, mainActivity, LocalDate.now().toString(), "note")
            massage.text= "Расслабляющий"
        }


    }

    private fun init() {
        binding.apply {
            monthYearPiker.text = sdf.format(calendar.time)
            calendarView.setHasFixedSize(true)
            calendarView.adapter = calendarAdapter
            monthYearPiker.setOnClickListener {
                displayDatePicker()
            }
        }
    }

    private fun initCalendar() {
        mStartD = Date()
        calendar.time = Date()
        getDates()
    }

    private fun displayDatePicker() {
        val materialDateBuilder : MaterialDatePicker.Builder<Long> =
            MaterialDatePicker.Builder.datePicker()
        materialDateBuilder.setTitleText("Выберите дату")
        val materialDatePicker = materialDateBuilder.build()
        materialDatePicker.show(requireActivity().supportFragmentManager, "MATERIAL_DATE_PICKER")
        materialDatePicker.addOnPositiveButtonClickListener {
            try {
                mStartD = Date(it)
                binding.monthYearPiker.text = sdf.format(it)
                calendar.time = Date(it)

                getDates()
            }catch (e: ParseException) {
                Log.e("TAG", "displayDatePicker: ${e.message}")
            }
        }
    }

    private fun getDates() {
        val dateList = ArrayList<CalendarData>()
        val dates = ArrayList<Date>()
        val monthCalendar = calendar.clone() as Calendar
        val maxDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)

        while (dates.size < maxDaysInMonth) {
            dates.add(monthCalendar.time)
            dateList.add(CalendarData(monthCalendar.time))
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        calendarList.clear()
        calendarList.addAll(dateList)
        calendarAdapter.updateList(dateList)

        for (item in dateList.indices) {
            if (dateList[item].data == mStartD) {
                calendarAdapter.setPosition(item)
                binding.calendarView.scrollToPosition(item)
            }
        }
    }

    override fun onSelected(calendarData: CalendarData, position: Int, day: TextView) {
        calendarList.forEachIndexed { index, calendarData ->
            calendarData.isSelected = index == position
        }
        calendarAdapter.updateList(calendarList)
    }
}