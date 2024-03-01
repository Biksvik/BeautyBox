package com.example.beautybox.firstLaunch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.beautybox.MainActivity
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentLoadingBinding
import com.google.common.base.Stopwatch
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.TimeUnit

class LoadingFragment : Fragment() {

    private lateinit var binding : FragmentLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoadingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val auth = (requireActivity() as MainActivity).authUtils.auth
        Timer().schedule(object : TimerTask() {
            override fun run() {
                requireActivity().runOnUiThread {
                    binding.root.findNavController().navigate(R.id.action_loadingFragment_to_completeQuestFragment)
                }
            }

        }, 3000)

    }
}