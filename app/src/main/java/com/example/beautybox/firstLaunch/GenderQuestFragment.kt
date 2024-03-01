package com.example.beautybox.firstLaunch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentGenderQuestBinding
import com.example.beautybox.mainScreens.profile.User

class GenderQuestFragment : Fragment() {

    private lateinit var user: User
    private lateinit var binding : FragmentGenderQuestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenderQuestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = User()
        user.questIsLaunched = true

        binding.btnWoman.setOnClickListener {
            user.gender = Gender.WOMAN
            getAction()
        }
        binding.btnMan.setOnClickListener {
            user.gender = Gender.MAN
            getAction()
        }
        binding.btnNoAnswer.setOnClickListener {
            getAction()
        }
    }

    fun getAction() {
        val action = GenderQuestFragmentDirections.actionGenderQuestFragmentToSkinTypeFragment(user)
        binding.root.findNavController().navigate(action)
    }
}