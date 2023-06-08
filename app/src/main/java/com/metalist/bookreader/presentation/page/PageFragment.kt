package com.metalist.bookreader.presentation.page

import android.os.Build
import android.os.Bundle
import android.view.View
import com.metalist.bookreader.R
import com.metalist.bookreader.base.BaseBindingFragment
import com.metalist.bookreader.data.response.base_response.PathToFoldersWithGlava
import com.metalist.bookreader.databinding.FragmentPageBinding
import com.metalist.bookreader.presentation.read_book.ChapterModel
import com.metalist.bookreader.presentation.read_book.ReadBookAdapter
import java.io.Serializable

class PageFragment : BaseBindingFragment<FragmentPageBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_page

    private val readBookAdapter = ReadBookAdapter()
    private val listOfChapters: MutableList<ChapterModel> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pathToFoldersWithGlava = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable(
                PATH_TO_FOLDERS_WITH_GLAVA,
                PathToFoldersWithGlava::class.java
            )
        } else {
            requireArguments().getSerializable(PATH_TO_FOLDERS_WITH_GLAVA) as? PathToFoldersWithGlava
        }

        val numberPictures = fillPagesList(pathToFoldersWithGlava?.numberOfPictures!!)
        val numberOfGlava = fillPagesList(pathToFoldersWithGlava.numberOfGlava)



        numberPictures.forEach { page ->
            numberOfGlava.forEach { chapter ->
                listOfChapters.add(
                    ChapterModel(
                        title = requireArguments().getString(TITLE)?.replace(" ", "").toString().lowercase(),
                        chapter, page
                    )
                )
            }
        }

        binding.rvPages.adapter = readBookAdapter
        readBookAdapter.submitList(listOfChapters)

    }

    private fun fillPagesList(size: Int): IntArray {
        val array = IntArray(size)
        for (i in array.indices) {
            array[i] = i + 1
        }
        return array
    }

    override fun onPause() {
        super.onPause()
        listOfChapters.clear()
    }

    companion object {

        private const val PATH_TO_FOLDERS_WITH_GLAVA = "pathToFoldersWithGlava"
        private const val TITLE = "title"


        fun newInstance(
            title: String?,
            pathToFoldersWithGlava: PathToFoldersWithGlava
        ): PageFragment {
            return PageFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putSerializable(PATH_TO_FOLDERS_WITH_GLAVA, pathToFoldersWithGlava as Serializable)
                }
            }
        }
    }
}