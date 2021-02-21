package com.baharudin.mvvmnewsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.mvvmnewsapp.databinding.ItemListArticleBinding
import com.baharudin.mvvmnewsapp.model.Article
import com.bumptech.glide.Glide
import java.util.*

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    lateinit var contextAdapter : Context
    inner class ArticleViewHolder(var binding : ItemListArticleBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = ItemListArticleBinding.inflate(
            LayoutInflater.from(parent.context)
            ,parent,
            false)
        contextAdapter = parent.context
        return ArticleViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {
            Glide.with(contextAdapter).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvDescription.text = article.description
            tvTitle.text = article.title
            tvPublishedAt.text = article.publishedAt

            setOnCLickListener {
                onItemClickListener?.let { it(article) }
            }
         }
    }
    private var onItemClickListener : ((Article) -> Unit)? = null

    fun setOnCLickListener(listener : (Article) -> Unit){
        onItemClickListener = listener
    }

}