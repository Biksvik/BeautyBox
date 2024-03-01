package com.example.beautybox

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.beautybox.databinding.ActivityMainBinding
import com.example.beautybox.mainScreens.firebaseUtils.AuthUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var authUtils: AuthUtils
    lateinit var launcher : ActivityResultLauncher<Intent>
    lateinit var bottomNavView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavView = binding.bottomNavView
        bottomNavView.setupWithNavController(navController)
        authUtils = AuthUtils(this).getInstance()

        val preferences = getPreferences(MODE_PRIVATE)
        firstRun(preferences)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    authUtils.signInWithGoogle(account.idToken!!)
                }
            } catch (e: ApiException) {
                Log.d("My log", "Api exception")
            }
        }


    }

    private fun firstRun(preferences: SharedPreferences?) {
        if (preferences != null) {
            if (preferences.getBoolean("isFirstRun", true)) {
                navController.navigate(R.id.action_main_screen_item_to_descriptionAppFragment)
            }
            preferences.edit().putBoolean("isFirstRun", false).apply()
        }
    }
}

