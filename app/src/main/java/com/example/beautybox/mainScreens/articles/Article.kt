package com.example.beautybox.mainScreens.articles

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Article (
    var heading : String ?= null,
    var text : String ?= null,
    var reference : String ?= null
): Parcelable
