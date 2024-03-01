package com.example.beautybox.mainScreens.mainScreen.diary

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybox.databinding.ItemDiarySelectBinding

class SkinConditionAdapter(val diaryEntry: DiaryEntry) : RecyclerView.Adapter<SkinConditionAdapter.SkinConditionViewHolder>() {

    var skinConditionList = listOf<String>(
        "Жирный блеск",
        "Стянутость",
        "Сухость",
        "Шелушение",
        "Обезвоженность",
        "Чувствительность",
        "Обветренность",
        "Все в порядке"
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SkinConditionAdapter.SkinConditionViewHolder {
        val binding = ItemDiarySelectBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SkinConditionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SkinConditionAdapter.SkinConditionViewHolder, position: Int) {
        val skinConditionItem = skinConditionList[position]
        holder.binding.tvItem.text = skinConditionItem
        holder.binding.cardViewItem.setOnClickListener {
            holder.binding.cardViewItem.setBackgroundColor(Color.parseColor("#E8B9F8"))
            diaryEntry.skinCondition += skinConditionItem
            diaryEntry.skinCondition += "\n"
        }
    }

    override fun getItemCount(): Int = skinConditionList.size

    class SkinConditionViewHolder(
        val binding : ItemDiarySelectBinding
    ) : RecyclerView.ViewHolder(binding.root)
}