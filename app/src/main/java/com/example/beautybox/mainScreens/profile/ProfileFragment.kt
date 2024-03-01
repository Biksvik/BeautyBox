package com.example.beautybox.mainScreens.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.example.beautybox.MainActivity
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentProfileBinding
import com.example.beautybox.mainScreens.firebaseUtils.AuthUtils
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var authUtils : AuthUtils


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        (requireActivity() as MainActivity).bottomNavView.isVisible = true
        binding.level.text = "1"
        val progressBar = binding.pbLevel
        progressBar.max = 100
        progressBar.setProgress(70)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authUtils = AuthUtils(requireActivity() as MainActivity).getInstance()
        checkAuthState()

        binding.help.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_profile_item_to_motivationFragment)
        }

        binding.googleSignIn.setOnClickListener{
            if (checkCurrentAuth()) {
                authUtils.btnGoogleClickListener()
                initUserData()
            } else {
                authUtils.btnGoogleClickListener()
                //binding.root.findNavController().navigate(R.id.action_profile_item_to_questionnaireFragment)
            }
        }
        binding.btnSignOut.setOnClickListener{
            authUtils.auth.signOut()
            checkAuthState()
        }
    }

    private fun initUserData() {
        Thread {
            val imageGoogleAcc = Picasso.get().load(authUtils.auth.currentUser?.photoUrl).get()
            requireActivity().runOnUiThread {
                binding.iconProfileImage.setImageBitmap(imageGoogleAcc)
            }

        }.start()

        binding.nameInProfile.text = authUtils.auth.currentUser?.displayName
        binding.emailText.text = authUtils.auth.currentUser?.email
    }

    private fun checkCurrentAuth() : Boolean {
        return authUtils.auth.currentUser != null

    }

    private fun checkAuthState() {
        if (checkCurrentAuth()) {
            binding.googleSignIn.isInvisible = true
            initUserData()
        } else {
            binding.googleSignIn.isInvisible = false
            binding.iconProfileImage.setImageResource(R.drawable.user)
            binding.nameInProfile.isInvisible = true
            binding.emailText.isInvisible = true
            binding.btnSignOut.isInvisible = true
        }
    }
}