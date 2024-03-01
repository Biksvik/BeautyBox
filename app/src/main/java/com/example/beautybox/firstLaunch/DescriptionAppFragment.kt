package com.example.beautybox.firstLaunch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentDescriptiionAppBinding

class DescriptionAppFragment : Fragment() {

    private lateinit var binding : FragmentDescriptiionAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptiionAppBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_descriptionAppFragment_to_authFragment)
        }
    }
}