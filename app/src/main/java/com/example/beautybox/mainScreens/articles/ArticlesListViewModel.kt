package com.example.beautybox.mainScreens.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautybox.mainScreens.firebaseUtils.DatabaseUtils

class  ArticlesListViewModel(): ViewModel() {

    private val databaseUtils : DatabaseUtils
    private val _allArticles = MutableLiveData<List<Article>>()
    val allArticles : LiveData<List<Article>> = _allArticles

    init {
        databaseUtils = DatabaseUtils().getInstance()
        databaseUtils.loadArticles(_allArticles)
    }
}
