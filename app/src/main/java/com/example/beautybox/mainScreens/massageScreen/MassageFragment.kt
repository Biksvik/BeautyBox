package com.example.beautybox.mainScreens.massageScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.navigation.findNavController
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentMassageBinding

class MassageFragment : Fragment() {

    private lateinit var viewModel: MassageViewModel
    private lateinit var binding: FragmentMassageBinding
    private var count = 0
    private var isStop = true
    private var offset : Long = 0
    lateinit var stopwathc: Chronometer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMassageBinding.inflate(layoutInflater, container, false)
        viewModel = MassageViewModel()
        stopwathc = binding.stopwatch
        workBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backspase.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_massageFragment_to_massage_item)
        }
        binding.btnNext.setOnClickListener{
            if (count < 3) {
                count++
                workBinding()
                stopwathc.base = SystemClock.elapsedRealtime()
            } else {
                binding.root.findNavController().navigate(R.id.action_massageFragment_to_endMassageFragment)
            }
        }
        binding.btnBack.setOnClickListener{
            if (count > 0) {
                count--
                workBinding()
            }
        }
        binding.btnPlay.setOnClickListener{
            if (isStop) {
                isStop = false
                binding.btnPlay.setImageResource(R.drawable.baseline_pause_circle_outline_24)
                setBaseTime()
                stopwathc.start()
            } else {
                isStop = true
                binding.btnPlay.setImageResource(R.drawable.play_circle_outline)
                saveOffset()
                stopwathc.stop()
            }
        }
    }

    fun setBaseTime() {
        stopwathc.base = SystemClock.elapsedRealtime() - offset
    }

    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopwathc.base
    }

    private fun workBinding() {
        with(binding) {
            imageExercise.setImageResource(viewModel.imageList[count])
            nameExercise.text = viewModel.nameExercise[count]
            descripExercise.text = viewModel.descripExercise[count]
        }
    }

}