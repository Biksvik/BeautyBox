package com.example.beautybox.mainScreens.articles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybox.MainActivity
import com.example.beautybox.databinding.FragmentArticlesListBinding

class ArticlesListFragment : Fragment() {

    private lateinit var binding: FragmentArticlesListBinding
    private lateinit var viewModel: ArticlesListViewModel
    private lateinit var articleRecyclerView : RecyclerView
    lateinit var adapter: ArticleAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesListBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).bottomNavView.isVisible = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleRecyclerView = binding.recyclerViewArticles
        articleRecyclerView.layoutManager = LinearLayoutManager(context)
        articleRecyclerView.setHasFixedSize(true)
        adapter = ArticleAdapter()
        articleRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(ArticlesListViewModel::class.java)
        viewModel.allArticles.observe(viewLifecycleOwner, Observer {
            adapter.updateArticleList(it)
        })

    }

}