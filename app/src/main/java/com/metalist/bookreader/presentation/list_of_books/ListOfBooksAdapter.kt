package com.metalist.bookreader.presentation.list_of_books

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metalist.bookreader.data.response.base_response.BookResponse
import com.metalist.bookreader.databinding.ItemBookBinding

class ListOfBooksAdapter :
    ListAdapter<BookResponse, ListOfBooksAdapter.BookViewHolder>(diffCallback) {

    var onBookClickListener: ((BookResponse) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.onBind(getItem(holder.adapterPosition))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<BookResponse>() {
            override fun areItemsTheSame(oldItem: BookResponse, newItem: BookResponse): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: BookResponse, newItem: BookResponse): Boolean =
                oldItem == newItem

        }
    }

    inner class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: BookResponse) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.pathToTitlePicture)
                    .into(posterImageView)
                titleTextView.text = item.name
                tvYear.text = item.releaseYear.toString()
                tvAgeRating.text = item.ageRating
                binding.tvAuthorName.text = item.author
                root.setOnClickListener {
                    onBookClickListener?.invoke(item)
                }

            }
        }
    }
}