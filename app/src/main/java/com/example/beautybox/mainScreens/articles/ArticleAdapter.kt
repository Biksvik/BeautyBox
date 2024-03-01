package com.example.beautybox.mainScreens.articles

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybox.R
import com.example.beautybox.databinding.ItemArticlesBinding

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private val articleList = ArrayList<Article>()
    private var articleImageList = listOf<Int>(
        R.drawable.icon_woman,
        R.drawable.icon_esthetician,
        R.drawable.icon_facial_mask,
        R.drawable.icon_skin_care
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticlesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = articleList[position]
        val articleIcon = articleImageList[position]
        holder.binding.heading.text = currentItem.heading
        holder.binding.imageArticle.setImageResource(articleIcon)

        holder.binding.articleCard.setOnClickListener {
            val action = ArticlesListFragmentDirections.actionAriclesItemToArticleFragment(currentItem)
            holder.binding.root.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateArticleList(articleList : List<Article>) {
        this.articleList.clear()
        this.articleList.addAll(articleList)
        notifyDataSetChanged()
    }

    class ArticleViewHolder(
        val binding: ItemArticlesBinding
    ): RecyclerView.ViewHolder(binding.root)
}






/*
(
    options: FirebaseRecyclerOptions<ArticleItem>
) : FirebaseRecyclerAdapter<ArticleItem, ArticleAdapter.ArticleViewHolder>(options) {

    inner class ArticleViewHolder(
        val binding: ItemArticlesBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ItemArticlesBinding
            .bind(LayoutInflater.from(parent.context).inflate(R.layout.item_articles, parent, false)))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int, model: ArticleItem) {
        holder.binding.heading.text = model.heading
    }
}
*/




    /*RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private var articlesList = listOf<ArticleItem>()

    fun setArticlesList(newArticlesList: List<ArticleItem>) {
        articlesList = newArticlesList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleAdapter.ArticleViewHolder {
        val binding = ItemArticlesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ArticleViewHolder, position: Int) {
        val itemArticles = articlesList[position]
        holder.binding.heading.text = itemArticles.heading
    }

    override fun getItemCount(): Int = articlesList.size

    inner class ArticleViewHolder(
        val binding: ItemArticlesBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
*/



/*Firebase(
    options: FirebaseRecyclerOptions<ArticleItem>
) : FirebaseRecyclerAdapter<ArticleItem, ArticleAdapterFirebase.ArticleViewHolder>(options) {

    val articlesList = listOf<ArticleItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ItemArticlesBinding
            .bind(LayoutInflater.from(parent.context).inflate(R.layout.item_articles, parent, false)))
    }

    override fun onBindViewHolder(
        holder: ArticleViewHolder, position: Int, model: ArticleItem
    ) {
        holder.bind(position)
    }

    inner class ArticleViewHolder(
        private val binding: ItemArticlesBinding
        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val articleItem = articlesList[position]
            binding.heading.text = articleItem.heading
        }
    }
}*/

