package com.example.beautybox.mainScreens.firebaseUtils

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.example.beautybox.MainActivity
import com.example.beautybox.mainScreens.articles.Article
import com.example.beautybox.mainScreens.mainScreen.diary.DiaryEntry
import com.example.beautybox.mainScreens.massageScreen.Massage
import com.example.beautybox.mainScreens.profile.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception

class DatabaseUtils () {

    private val dbFirebase = FirebaseDatabase.getInstance()
    private val articlesReferens = dbFirebase.getReference(ARTICLES_CHILD)
    private val massagesReferens = dbFirebase.getReference(MASSAGE_CHILD)
    private val usersReferens = dbFirebase.getReference(USER_CHILD)


    @Volatile private var INSTANCE : DatabaseUtils ?= null
    fun getInstance() : DatabaseUtils {
        return INSTANCE ?: synchronized(this) {
            val instance = DatabaseUtils()
            INSTANCE = instance
            instance
        }
    }

    fun sendUserToFirebase(user: User, mainActivity: MainActivity) {
        val currentUserRef = usersReferens.child(getCurrentUser(mainActivity))
        currentUserRef.setValue(
            User(
                questIsLaunched = user.questIsLaunched,
                gender = user.gender,
                skinType = user.skinType,
                weight = user.weight,
                physicalActivity = user.physicalActivity,
                waterNorm = user.waterNorm,
                currentWater = user.currentWater,
                stepWater = user.stepWater,
                level = user.level,
                points = user.points,
                diaryEntries = user.diaryEntries
            )
        )
    }

    private fun getCurrentUser(mainActivity: MainActivity): String {
        var result = AuthUtils(mainActivity).getInstance().auth.currentUser.toString()
        val index = result.lastIndexOf("@")
        result = result.substring(index + 1)
        return result
    }

    fun loadArticles (articleList : MutableLiveData<List<Article>>) {
        articlesReferens.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _articleList : List<Article> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Article::class.java)!!
                    }
                    articleList.postValue(_articleList)
                }catch (_: Exception) {}
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    fun loadMassages (massagesList : MutableLiveData<List<Massage>>) {
        massagesReferens.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _massagesList : List<Massage> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Massage::class.java)!!
                    }
                    massagesList.postValue(_massagesList)
                }catch (_: Exception) {}
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    fun sendDoubleToFirebase(sendValue: Double, mainActivity: MainActivity, path: String) {
        usersReferens.child(getCurrentUser(mainActivity)).child(path).setValue(sendValue)
    }

    fun getTextFromDatabase(actualUserWater: TextView, mainActivity: MainActivity, path: String) {
        usersReferens.child(getCurrentUser(mainActivity)).child(path).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                actualUserWater.text = snapshot.getValue(Double::class.java).toString()
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    fun getDiaryFromDatabase(textView: TextView, mainActivity: MainActivity, data: String, path: String) {
        usersReferens
            .child(getCurrentUser(mainActivity))
            .child("diaryEntries")
            .child(data)
            .child(path)
            .addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                textView.text = snapshot.getValue(String::class.java)
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    fun sendDiaryEntryToFirebase(diaryEntry: DiaryEntry, mainActivity: MainActivity) {
        val diaryEntryRef = usersReferens.child(getCurrentUser(mainActivity)).child("diaryEntries").child(diaryEntry.date)
        diaryEntryRef.setValue(
            DiaryEntry(
                date = diaryEntry.date,
                skinCondition = diaryEntry.skinCondition,
                careMorning = diaryEntry.careMorning,
                careEvening = diaryEntry.careEvening,
                note = diaryEntry.note
            )
        )
    }

    companion object {
        //поле для констант
        private const val ARTICLES_CHILD = "articles"
        private const val MASSAGE_CHILD = "massage"
        private const val USER_CHILD = "user"
    }


}