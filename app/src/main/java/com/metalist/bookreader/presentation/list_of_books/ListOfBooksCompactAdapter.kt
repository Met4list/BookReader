package com.metalist.bookreader.presentation.list_of_books


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metalist.bookreader.data.response.base_response.BookResponse
import com.metalist.bookreader.databinding.ItemBookCompactBinding

class ListOfBooksCompactAdapter :
    ListAdapter<BookResponse, ListOfBooksCompactAdapter.BookCompactViewHolder>(diffCallback) {

    var onBookClickListener: ((BookResponse) -> Unit)? = null
    var onSaveBookClickListener: ((BookResponse) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCompactViewHolder {
        return BookCompactViewHolder(
            ItemBookCompactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookCompactViewHolder, position: Int) {
        holder.onBind(getItem(holder.adapterPosition))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<BookResponse>() {
            override fun areItemsTheSame(oldItem: BookResponse, newItem: BookResponse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BookResponse, newItem: BookResponse): Boolean =
                oldItem == newItem

        }
    }

    inner class BookCompactViewHolder(private val binding: ItemBookCompactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: BookResponse) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.pathToTitlePicture)
                    .into(posterImageView)
                titleTextView.text = item.name
                root.setOnClickListener {
                    onBookClickListener?.invoke(item)
                }
            }
        }
    }
}