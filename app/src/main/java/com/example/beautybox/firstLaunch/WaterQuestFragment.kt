package com.example.beautybox.firstLaunch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentWaterQuestBinding
import com.example.beautybox.mainScreens.profile.User

class WaterQuestFragment : Fragment() {

    private lateinit var binding : FragmentWaterQuestBinding
    private val args by navArgs<WaterQuestFragmentArgs>()
    private lateinit var user: User


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWaterQuestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weightItemList = arrayListOf<Int>(
            50, 51, 52, 53, 54, 55, 56, 57, 58, 59,
            60, 62, 63, 64, 65, 66, 67, 68, 69, 70,
            71, 72, 73, 74, 75, 76, 77, 78, 79, 80)
        //val weightItemList = (1..30).map { Int }
        user = args.user
        val simpleLangSpinner = binding.simpleSpinner
        simpleLangSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, weightItemList)
        simpleLangSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                user.weight = weightItemList[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) { }

        }
        binding.btnNext.setOnClickListener {
            val action = WaterQuestFragmentDirections.actionWaterQuestFragmentToPhysicalActivityFragment(user)
            binding.root.findNavController().navigate(action)
        }

    }
}