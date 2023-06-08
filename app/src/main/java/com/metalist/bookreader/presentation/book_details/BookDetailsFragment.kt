package com.metalist.bookreader.presentation.book_details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.metalist.bookreader.R
import com.metalist.bookreader.base.BaseBindingFragment
import com.metalist.bookreader.data.response.base_response.BookResponse
import com.metalist.bookreader.databinding.FragmentBookDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailsFragment : BaseBindingFragment<FragmentBookDetailsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_book_details

    private val viewModel: BookDetailsViewModel by viewModels()

    private var bookResponse: BookResponse? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchBookDetails(arguments?.getInt("book_id") ?: 0)

        viewModel.bookResponse.observe(viewLifecycleOwner) {

            bookResponse = it

            Glide.with(requireContext())
                .load(it.pathToTitlePicture)
                .into(binding.ivPreview)

            binding.tvTitle.text = it.name
            binding.tvYear.text = it.releaseYear.toString()
            binding.tvDescription.text = it.description

            binding.tvAgeRating.text = "Age rating: ${it.ageRating}"

            binding.cgGenres.addView(
                Chip(requireContext()).apply {
                    it.genres.forEach { genre ->
                        text = genre.name
                    }
                }
            )
        }

        binding.btnRead.setOnClickListener {
            findNavController().navigate(
                R.id.action_bookDetailsFragment_to_readBookFragment, bundleOf("book_details" to bookResponse)
            )
        }
    }
}