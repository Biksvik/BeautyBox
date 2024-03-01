package com.example.beautybox.mainScreens.firebaseUtils

import com.example.beautybox.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthUtils(
    private val mainActivity: MainActivity
) {
    val auth = Firebase.auth

    @Volatile private var INSTANCE : AuthUtils ?= null
    fun getInstance() : AuthUtils {
        return INSTANCE ?: synchronized(this) {
            val instance = AuthUtils(mainActivity)
            INSTANCE = instance
            instance
        }
    }

    val googleClient : GoogleSignInClient
        get() = GoogleSignIn.getClient(
            mainActivity,
            GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("590507568704-8asnqghuk7m0u3hk3im1nocjelkkh7su.apps.googleusercontent.com")
                .requestEmail()
                .build()
        )

    fun btnGoogleClickListener() {
        mainActivity.launcher.launch(googleClient.signInIntent)
    }
    
    fun signInWithGoogle(idToken : String?) {
        idToken?.let {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                    } else {

                    }
                }
        }
    }
}