package com.example.beautybox.mainScreens.profile

import android.os.Parcelable
import com.example.beautybox.firstLaunch.Gender
import com.example.beautybox.firstLaunch.PhysicalActivity
import com.example.beautybox.firstLaunch.SkinType
import com.example.beautybox.mainScreens.mainScreen.diary.DiaryEntry
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
class User (
    //val userID: String = "",
    var questIsLaunched: Boolean = false,
    var gender: Gender = Gender.NO_ANSWER,
    var skinType: SkinType = SkinType.NO_ANSWER,
    var weight: Int = 50,
    var physicalActivity: PhysicalActivity = PhysicalActivity.NO_ANSWER,
    var waterNorm: Double = 1.7,
    var currentWater: Double = 0.0,
    var stepWater: Double = 0.2,
    var level: Int = 1,
    var points: Int = 0,
    var diaryEntries: DiaryEntry = DiaryEntry("bk")
): Parcelable