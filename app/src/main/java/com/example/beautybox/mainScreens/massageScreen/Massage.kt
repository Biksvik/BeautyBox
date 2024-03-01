package com.example.beautybox.mainScreens.massageScreen

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Massage(
    var heading : String ?= null,
    var description : String ?= null,
    var time : String ?= null,
    //var exercise : ArrayList<Exercise>
): Parcelable
