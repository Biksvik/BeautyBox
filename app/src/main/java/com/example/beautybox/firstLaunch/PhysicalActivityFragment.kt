package com.example.beautybox.firstLaunch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.beautybox.MainActivity
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentPhysicalActivityBinding
import com.example.beautybox.databinding.FragmentWaterQuestBinding
import com.example.beautybox.mainScreens.firebaseUtils.DatabaseUtils
import com.example.beautybox.mainScreens.profile.User

class PhysicalActivityFragment : Fragment() {

    private lateinit var binding : FragmentPhysicalActivityBinding
    private val args by navArgs<PhysicalActivityFragmentArgs>()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhysicalActivityBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = args.user
        binding.btnLowActivity.setOnClickListener {
            user.physicalActivity = PhysicalActivity.LOW
            getAction()
        }
        binding.btnAverage.setOnClickListener {
            user.physicalActivity = PhysicalActivity.AVERAGE
            getAction()
        }
        binding.btnHighActivity.setOnClickListener {
            user.physicalActivity = PhysicalActivity.HIGH
            getAction()
        }
    }

    fun getAction() {
        user.waterNorm = user.weight * 0.03 + user.physicalActivity.value
        val action = PhysicalActivityFragmentDirections.actionPhysicalActivityFragmentToDailyNormWaterFragment(user)
        binding.root.findNavController().navigate(action)
    }


}