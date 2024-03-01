package com.example.beautybox.firstLaunch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentDailyNormWaterBinding
import com.example.beautybox.databinding.FragmentPhysicalActivityBinding
import com.example.beautybox.mainScreens.firebaseUtils.DatabaseUtils
import com.example.beautybox.mainScreens.profile.User

class DailyNormWaterFragment : Fragment() {

    private lateinit var binding : FragmentDailyNormWaterBinding
    private val args by navArgs<DailyNormWaterFragmentArgs>()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyNormWaterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = args.user
        binding.waterNorm.text = "${user.waterNorm} л"
        binding.btnNext.setOnClickListener {
            val action = DailyNormWaterFragmentDirections.actionDailyNormWaterFragmentToLastQuestFragment(user)
            binding.root.findNavController().navigate(action)
        }
    }
}