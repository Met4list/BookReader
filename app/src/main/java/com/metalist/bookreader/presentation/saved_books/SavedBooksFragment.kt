package com.metalist.bookreader.presentation.saved_books

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.metalist.bookreader.R
import com.metalist.bookreader.base.BaseBindingFragment
import com.metalist.bookreader.databinding.FragmentSavedBooksBinding
import com.metalist.bookreader.presentation.list_of_books.ListOfBooksAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedBooksFragment : BaseBindingFragment<FragmentSavedBooksBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_saved_books

    private val viewModel: SavedBooksViewModel by viewModels()
    private val bookAdapter = ListOfBooksAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBooks.adapter = bookAdapter

//        viewModel.books.observe(viewLifecycleOwner){
//            it.forEach { bookEntity ->
//                bookAdapter.submitList(listOf(BookEntity.toResponse(bookEntity)))
//            }
//        }
    }
}