package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder> (){

    class ArticleViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    /**setUp DiffUtil**/
    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    /**tool that takes the list and compare them**/
    val differ = AsyncListDiffer(this, differCallback)

    /**Adapter funcs**/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview, parent, false)
        return ArticleViewHolder(view)
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val article = differ.currentList[position]
        holder.itemView.apply{
            Glide.with(this).load(article.urlToImage).placeholder(R.drawable.news).into(ivArticleImage)
            tvSource.text = article.source?.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

            setOnClickListener {
                onItemClickListener?.let{it(article)}
            }
        }
    }

    override fun getItemCount(): Int  = differ.currentList.size


    fun setOnItemClickListener(listener : (Article) -> Unit){
        onItemClickListener= listener
    }
}


/**DifUtils calculate the difference btw two list and enable us to update with the different elements in the list, this happens in the background---
 * better than notifyContentChange(whc update the whole list every time it is called**/
