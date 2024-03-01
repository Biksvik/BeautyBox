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
import com.example.beautybox.databinding.FragmentLastQuestBinding
import com.example.beautybox.mainScreens.firebaseUtils.DatabaseUtils
import com.example.beautybox.mainScreens.mainScreen.MainScreenFragmentArgs
import com.example.beautybox.mainScreens.profile.User

class LastQuestFragment : Fragment() {

    private lateinit var binding : FragmentLastQuestBinding
    private val args by navArgs<LastQuestFragmentArgs>()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLastQuestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = args.user
        DatabaseUtils().getInstance().sendUserToFirebase(user, requireActivity() as MainActivity)
        binding.btnNext.setOnClickListener {
            val action = LastQuestFragmentDirections.actionLastQuestFragmentToMainScreenItem(user)
            binding.root.findNavController().navigate(action)
        }
    }
}