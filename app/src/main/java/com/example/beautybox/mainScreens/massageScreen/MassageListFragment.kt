package com.example.beautybox.mainScreens.massageScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybox.MainActivity
import com.example.beautybox.databinding.FragmentMassageListBinding

class MassageListFragment : Fragment() {

    private lateinit var binding: FragmentMassageListBinding
    private lateinit var adapter: MassageAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MassageListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMassageListBinding.inflate(layoutInflater, container, false)
        (requireActivity() as MainActivity).bottomNavView.isVisible = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerViewMassage
        recyclerView.setHasFixedSize(true)
        adapter = MassageAdapter()
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[MassageListViewModel::class.java]
        viewModel.allMassages.observe(viewLifecycleOwner, Observer {
            adapter.updateMassageList(it)
        })
    }
}