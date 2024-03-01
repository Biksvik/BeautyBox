package com.example.beautybox.mainScreens.mainScreen.diary

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.Date

@Parcelize
class DiaryEntry (
    //var photo
    var date: String,
    var skinCondition: String = "",
    var careMorning: String = "",
    var careEvening: String = "",
    var note: String = ""
):Parcelable