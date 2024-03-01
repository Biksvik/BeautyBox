package com.example.beautybox.mainScreens.mainScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.beautybox.MainActivity
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentMainScreenBinding
import com.example.beautybox.mainScreens.firebaseUtils.DatabaseUtils
import com.example.beautybox.mainScreens.profile.User
import java.math.RoundingMode

class MainScreenFragment : Fragment() {

    private lateinit var dbFirebase: DatabaseUtils
    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentMainScreenBinding

    private lateinit var user: User

    private var dailyNorm = 2.1
    private var stepWater = 0.2
    private var actualUserWater = 0.0
    lateinit var pbHorizontal: ProgressBar
    private var progressStatus = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        mainActivity = requireActivity() as MainActivity
        mainActivity.bottomNavView.isVisible = true
        dbFirebase = DatabaseUtils().getInstance()
        if (mainActivity.authUtils.auth.currentUser != null)
            initWater()
        return binding.root
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnWater.setOnClickListener {
            if (progressStatus == pbHorizontal.max - 1)
                Toast.makeText(requireContext(), "Вы молодец!", Toast.LENGTH_LONG).show()
            progressStatus += 1
            postProgress()
            DatabaseUtils().getInstance().sendDoubleToFirebase(
                binding.actualUserWater.text.toString().toDouble(),
                (requireActivity() as MainActivity),
                "currentWater"
            )
        }
        binding.btnDiary.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_main_screen_item_to_diaryFragment)
        }
        binding.btnCalendar.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_main_screen_item_to_calendarFragment)
        }
    }

    private fun postProgress() {
        pbHorizontal.setProgress(progressStatus)
        val doubleProgress = (stepWater *  progressStatus).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        if (doubleProgress <= dailyNorm)
            binding.actualUserWater.text = doubleProgress.toString()
    }

    private fun initWater() {
        dbFirebase.getTextFromDatabase(binding.actualUserWater, mainActivity, "currentWater")
        dbFirebase.getTextFromDatabase(binding.normalWater, mainActivity, "waterNorm")
        dbFirebase.getTextFromDatabase(binding.btnWater, mainActivity, "stepWater")
        binding.btnWater.text = "+ ${stepWater}"

        pbHorizontal = binding.pbWater
        pbHorizontal.max = (dailyNorm / stepWater).toInt()
        progressStatus = (actualUserWater / stepWater).toInt()
        pbHorizontal.setProgress(progressStatus)
    }

}

/*
private fun getCurrentUser(): String {
    var result = (requireContext() as MainActivity).authUtils.auth.currentUser.toString()
    val index = result.lastIndexOf("@")
    result = result.substring(index + 1)
    return result
}*/
