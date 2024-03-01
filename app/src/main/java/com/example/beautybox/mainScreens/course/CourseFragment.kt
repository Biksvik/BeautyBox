package com.example.beautybox.mainScreens.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybox.MainActivity
import com.example.beautybox.databinding.FragmentFiveBinding



class  CourseFragment : Fragment() {
    private lateinit var binding: FragmentFiveBinding
    private lateinit var adapterCourse: CourseAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel: CourseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFiveBinding.inflate(layoutInflater, container,  false)
        binding.progressBarCourse.max = 10
        binding.progressBarCourse.setProgress(1)
        (requireActivity() as MainActivity).bottomNavView.isVisible = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerViewCourse
        adapterCourse = CourseAdapter()
        recyclerView.adapter = adapterCourse
        adapterCourse.courseList = viewModel.getCourseList()
    }
}