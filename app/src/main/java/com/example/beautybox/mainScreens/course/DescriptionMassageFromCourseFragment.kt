package com.example.beautybox.mainScreens.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.beautybox.MainActivity
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentDescriptionMassageBinding
import com.example.beautybox.databinding.FragmentDescriptionMassageFromCourseBinding

class DescriptionMassageFromCourseFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionMassageFromCourseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionMassageFromCourseBinding.inflate(layoutInflater, container, false)
        (requireActivity() as MainActivity).bottomNavView.isGone = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_descriptionMassageFromCourseFragment_to_preparationForMassageFragment)
        }

    }
}