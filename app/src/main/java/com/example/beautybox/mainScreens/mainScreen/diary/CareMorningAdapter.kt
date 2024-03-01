package com.example.beautybox.mainScreens.mainScreen.diary

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybox.databinding.ItemDiarySelectBinding

class CareMorningAdapter (val diaryEntry: DiaryEntry) : RecyclerView.Adapter<CareMorningAdapter.CareMorningViewHolder>() {

    var careMorningList = listOf<String>(
        "Очищающее средство",
        "Тоник",
        "Сыворотка",
        "Крем",
        "Маска",
        "Тканевая маска",
        "Патчи",
        "Крем для глаз",
        "Скраб",
        "Пилинг",
        "Массаж лица"
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CareMorningAdapter.CareMorningViewHolder {
        val binding = ItemDiarySelectBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CareMorningViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CareMorningAdapter.CareMorningViewHolder, position: Int) {
        val careMorningItem = careMorningList[position]
        holder.binding.tvItem.text = careMorningItem
        holder.binding.cardViewItem.setOnClickListener {
            diaryEntry.careMorning += careMorningItem
            diaryEntry.careMorning += "\n"
            holder.binding.cardViewItem.setBackgroundColor(Color.parseColor("#E8B9F8"))
        }
    }

    override fun getItemCount(): Int = careMorningList.size

    class CareMorningViewHolder(
        val binding : ItemDiarySelectBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

