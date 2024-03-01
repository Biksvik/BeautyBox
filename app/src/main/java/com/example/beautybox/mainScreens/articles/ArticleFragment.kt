package com.example.beautybox.mainScreens.articles

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.navigation.fragment.navArgs
import com.example.beautybox.MainActivity
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private lateinit var viewModel: ArticleViewModel
    private lateinit var binding: FragmentArticleBinding
    private val args by navArgs<ArticleFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        (requireActivity() as MainActivity).bottomNavView.isGone = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        binding.heading.text = args.currentArticle.heading
        val textArticle = args.currentArticle.text.toString().replace("@", "\n\n")
        binding.textArticle.text = textArticle
        if (args.currentArticle.reference.toString().equals("a1")) {
            binding.imageArticle.setImageResource(R.drawable.scin_type)
        }
    }
}