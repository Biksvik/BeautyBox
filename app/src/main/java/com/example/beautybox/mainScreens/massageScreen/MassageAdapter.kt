package com.example.beautybox.mainScreens.massageScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybox.R
import com.example.beautybox.databinding.ItemMassageBinding

class MassageAdapter : RecyclerView.Adapter<MassageAdapter.MassageViewHolder>() {
    private var massageList = ArrayList<Massage>()
    private var massageImageList = listOf<Int>(
        R.drawable.icon_facial_massage_four,
        R.drawable.icon_facial_massage_two,
        R.drawable.icon_cheek_massage_three,
        R.drawable.icon_facial_massage_one
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MassageViewHolder {
        val binding = ItemMassageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MassageViewHolder(binding)
    }

    override fun getItemCount(): Int = massageList.size

    override fun onBindViewHolder(holder: MassageViewHolder, position: Int) {
        val massage = massageList[position] // Получение массажа из списка данных по позиции
        val massageIcon = massageImageList[position]
        holder.binding.timeMassage.text = massage.time
        holder.binding.nameMassage.text = massage.heading
        holder.binding.imageMassage.setImageResource(massageIcon)
        //massage.photo.also { holder.binding.imageMassage = it }

        holder.binding.massageCard.setOnClickListener {
            val action = MassageListFragmentDirections.actionMassageItemToDescriptionMassageFragment(massage)
            holder.binding.root.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMassageList(massageList : List<Massage>) {
        this.massageList.clear()
        this.massageList.addAll(massageList)
        notifyDataSetChanged()
    }

    class MassageViewHolder(
        val binding: ItemMassageBinding
    ) : RecyclerView.ViewHolder(binding.root)
}