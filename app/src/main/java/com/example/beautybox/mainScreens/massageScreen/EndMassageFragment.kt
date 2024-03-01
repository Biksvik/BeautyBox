package com.example.beautybox.mainScreens.massageScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentEndMassageBinding

class EndMassageFragment : Fragment() {

    lateinit var binding: FragmentEndMassageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEndMassageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.endMassage.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_endMassageFragment_to_massage_item)
        }
        binding.addCourse.setOnClickListener {
            Toast.makeText(requireContext(), "Курс создан!", Toast.LENGTH_SHORT).show()
            binding.root.findNavController().navigate(R.id.action_endMassageFragment_to_five)
        }
    }
}