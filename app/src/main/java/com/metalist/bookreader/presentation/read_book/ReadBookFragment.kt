package com.metalist.bookreader.presentation.read_book

import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import com.metalist.bookreader.R
import com.metalist.bookreader.base.BaseBindingFragment
import com.metalist.bookreader.data.response.base_response.BookResponse
import com.metalist.bookreader.databinding.FragmentReadBookBinding
import com.metalist.bookreader.utils.FileUtil
import com.metalist.bookreader.utils.updateVisibility

class ReadBookFragment : BaseBindingFragment<FragmentReadBookBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_read_book

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookResponse =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable("book_details", BookResponse::class.java)
            } else {
                arguments?.getSerializable("book_details") as? BookResponse
            }

        if (FileUtil.isPdf(bookResponse?.pathToFoldersWithGlava?.first()?.linkToPictures)){
            binding.apply {
                viewPager.updateVisibility(false)
                tbChapters.updateVisibility(false)
                wvReadPdf.updateVisibility(true)
                wvReadPdf.webViewClient = WebViewClient()
                wvReadPdf.settings.setSupportZoom(true)
                wvReadPdf.settings.apply {
                    displayZoomControls = false
                    supportZoom()
                    builtInZoomControls = true
                }
                wvReadPdf.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                wvReadPdf.settings.javaScriptEnabled = true
                wvReadPdf.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + bookResponse?.pathToFoldersWithGlava?.first()?.linkToPictures.toString())
            }
        } else {
            binding.apply {
                viewPager.updateVisibility(true)
                tbChapters.updateVisibility(true)
                wvReadPdf.updateVisibility(false)
            }
            val pagerAdapter = ReadBookViewPagerAdapter(
                parentFragmentManager
            ).apply {
                binding.viewPager.adapter = this
            }
            bookResponse?.pathToFoldersWithGlava?.let {
                pagerAdapter.submitList(bookResponse.name, it)

            }
        }

        binding.tbChapters.setupWithViewPager(binding.viewPager)
    }
}