package com.example.beautybox.mainScreens.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybox.R
import com.example.beautybox.databinding.ItemCourseCardViewBinding

class CourseAdapter : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    var courseList = listOf<Course>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseAdapter.CourseViewHolder {
        val binding = ItemCourseCardViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseAdapter.CourseViewHolder, position: Int) {
        val courseItem = courseList[position]
        with(holder.binding) {
            sessionNumber.text = courseItem.sessionId.toString()
            dateParam.text = courseItem.date
            if (position == 0)
                imageView.setImageResource(R.drawable.done)
            cardViewItem.setOnClickListener {
                root.findNavController().navigate(R.id.action_five_to_descriptionMassageFromCourseFragment)
            }
        }
    }

    override fun getItemCount(): Int = courseList.size

    class CourseViewHolder(
        val binding : ItemCourseCardViewBinding
    ) : RecyclerView.ViewHolder(binding.root)
}