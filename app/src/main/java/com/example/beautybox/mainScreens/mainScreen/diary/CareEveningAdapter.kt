package com.example.beautybox.mainScreens.mainScreen.diary

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybox.databinding.ItemDiarySelectBinding

class CareEveningAdapter(val diaryEntry: DiaryEntry) : RecyclerView.Adapter<CareEveningAdapter.CareMorningViewHolder>() {

    var careEveningList = listOf<String>(
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
    ): CareEveningAdapter.CareMorningViewHolder {
        val binding = ItemDiarySelectBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CareMorningViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CareEveningAdapter.CareMorningViewHolder, position: Int) {
        val careEveningItem = careEveningList[position]
        holder.binding.tvItem.text = careEveningItem
        holder.binding.cardViewItem.setOnClickListener {
            diaryEntry.careEvening += careEveningItem
            diaryEntry.careEvening += "\n"
            holder.binding.cardViewItem.setBackgroundColor(Color.parseColor("#E8B9F8"))
        }
    }

    override fun getItemCount(): Int = careEveningList.size

    class CareMorningViewHolder(
        val binding : ItemDiarySelectBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

