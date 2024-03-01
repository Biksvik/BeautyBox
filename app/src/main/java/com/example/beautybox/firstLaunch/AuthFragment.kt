package com.example.beautybox.firstLaunch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.beautybox.MainActivity
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentAuthBinding
import com.example.beautybox.mainScreens.profile.User


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener{
            val user = User()
            val action = AuthFragmentDirections.actionAuthFragmentToMainScreenItem(user)
            binding.root.findNavController().navigate(action)
        }

        binding.btnSignIn.setOnClickListener {
            (requireActivity() as MainActivity).authUtils.btnGoogleClickListener()
            binding.root.findNavController().navigate(R.id.action_authFragment_to_loadingFragment)
        }
    }
}