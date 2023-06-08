package com.metalist.bookreader.presentation.read_book

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metalist.bookreader.databinding.ItemPageBinding

class ReadBookAdapter : ListAdapter<ChapterModel, ReadBookAdapter.ReadBookViewHolder>(
    diffCallback
) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ChapterModel>() {
            override fun areItemsTheSame(
                oldItem: ChapterModel,
                newItem: ChapterModel
            ): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: ChapterModel,
                newItem: ChapterModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadBookViewHolder =
        ReadBookViewHolder(
            ItemPageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ReadBookViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun submitList(list: MutableList<ChapterModel>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    inner class ReadBookViewHolder(private val binding: ItemPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ChapterModel) {
            Glide.with(itemView.context)
                .load(formatPageUrl(item))
                .into(binding.ivPage)
        }
    }

    private fun formatPageUrl(chapterModel: ChapterModel): String {
        return "https://dvty-vinn-dev-app-0.organism.aibody.io/manga/${chapterModel.title.replace(" ", "")}/glava${chapterModel.chapter}/${chapterModel.page}.jpg"
    }
}

data class ChapterModel(
    val title: String,
    val chapter: Int,
    val page: Int
)
