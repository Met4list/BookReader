package com.metalist.bookreader.presentation.list_of_books

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.metalist.bookreader.R
import com.metalist.bookreader.base.BaseBindingFragment
import com.metalist.bookreader.databinding.FragmentListOfBooksBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListOfBooksFragment : BaseBindingFragment<FragmentListOfBooksBinding>() {

    private val viewModel: ListOfBooksViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.fragment_list_of_books

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookAdapter = ListOfBooksAdapter()
        val bookCompactAdapter = ListOfBooksCompactAdapter()

        binding.rvBooks.adapter = bookAdapter
        binding.rvRecommendation.adapter = bookCompactAdapter

        viewModel.listOfBooks.observe(viewLifecycleOwner){
            bookAdapter.submitList(it)
            bookCompactAdapter.submitList(it)
        }

        bookAdapter.onBookClickListener = {
            (parentFragment as NavHostFragment)
                .parentFragment
                ?.findNavController()
                ?.navigate(R.id.action_mainFragment_to_bookDetailsFragment, bundleOf("book_id" to it.id))
        }

        bookCompactAdapter.onBookClickListener = {
            (parentFragment as NavHostFragment)
                .parentFragment
                ?.findNavController()
                ?.navigate(R.id.action_mainFragment_to_bookDetailsFragment, bundleOf("book_id" to it.id))
        }
    }

}