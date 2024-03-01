package com.example.beautybox.mainScreens.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import com.example.beautybox.MainActivity
import com.example.beautybox.R

class MotivationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity() as MainActivity).bottomNavView.isGone = true
        return inflater.inflate(R.layout.fragment_motivation, container, false)
    }
}