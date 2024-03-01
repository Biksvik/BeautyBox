package com.example.beautybox.firstLaunch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentSkinTypeBinding
import com.example.beautybox.mainScreens.profile.User

class SkinTypeFragment : Fragment() {

    private lateinit var binding : FragmentSkinTypeBinding
    private val args by navArgs<SkinTypeFragmentArgs>()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSkinTypeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = args.user
        binding.btnNormalSkin.setOnClickListener {
            user.skinType = SkinType.NORMAL_SKIN
            getAction()
        }
        binding.btnDrySkin.setOnClickListener {
            user.skinType = SkinType.DRY_SKIN
            getAction()
        }
        binding.btnOilySkin.setOnClickListener {
            user.skinType = SkinType.OILY_SKIN
            getAction()
        }
        binding.btnCombinationSkin.setOnClickListener {
            user.skinType = SkinType.COMBINATION_SKIN
            getAction()
        }
        binding.btnNoAnswer.setOnClickListener {
            user.skinType = SkinType.NO_ANSWER
            getAction()
        }
    }

    fun getAction() {
        val action = SkinTypeFragmentDirections.actionSkinTypeFragmentToWaterQuestFragment(user)
        binding.root.findNavController().navigate(action)
    }
}