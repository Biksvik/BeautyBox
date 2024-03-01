package com.example.beautybox.mainScreens.course

import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class CourseViewModel : ViewModel() {
    private var numberOfDays = 10
    private var dateList = getDateList()

    private var courseList = (1..numberOfDays).map {Course(
        sessionId = it,
        date = dateList[it - 1]
    )}

    fun getCourseList() = courseList

    private fun getDateList() : List<String> {
        val result = mutableListOf<String>()
        val formatter = DateTimeFormatter.ofPattern("dd MMMM")
        var date = LocalDate.now()
        val skipDays = 3
        val period = Period.ofDays(skipDays)
        for (i in 1..numberOfDays) {
            result.add(date.format(formatter).toString())
            date = date.plus(period)
        }
        return result
    }
}

data class Course(
    val sessionId: Int,
    val date: String = "Дата сеанса"
)