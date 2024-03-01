package com.example.beautybox.mainScreens.massageScreen

import androidx.lifecycle.ViewModel
import com.example.beautybox.R

class MassageViewModel : ViewModel() {

    val imageList = mutableListOf<Int>(
        R.drawable.exone,
        R.drawable.extwo,
        R.drawable.exthree,
        R.drawable.exfour
    )

    val nameExercise = mutableListOf<String>(
        "Нажим: Слабый",
        "Нажим: Сильный",
        "Нажим: Слабый",
        "Нажим: Средний"
    )

    val descripExercise = mutableListOf<String>(
        "Фиксируем большими пальцами нижнюю челюсть и ребром ладони поглаживаем кожу от носа к ушам",
        "Указательными пальцами по очереди проводим по линиям: сначала вверх, потом в стороны",
        "Указательными пальцами проводим по линиям и в конце поднимаем уголки глаз",
        "Четырьмя пальцами обеих рук смещаем кожу по линиям вверх",
    )
}