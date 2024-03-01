package com.example.beautybox.mainScreens.massageScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautybox.mainScreens.firebaseUtils.DatabaseUtils

class MassageListViewModel() : ViewModel() {
    private val databaseUtils : DatabaseUtils = DatabaseUtils().getInstance()
    private val _allMassages = MutableLiveData<List<Massage>>()
    val allMassages : LiveData<List<Massage>> = _allMassages

    init {
        databaseUtils.loadMassages(_allMassages)
    }
}