package com.metalist.bookreader.presentation.search

import com.metalist.bookreader.R
import com.metalist.bookreader.base.BaseBindingFragment
import com.metalist.bookreader.databinding.FragmentSearchBooksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchBooksFragment : BaseBindingFragment<FragmentSearchBooksBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_search_books
}