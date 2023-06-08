package com.metalist.bookreader.presentation.read_book

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.metalist.bookreader.data.response.base_response.PathToFoldersWithGlava
import com.metalist.bookreader.presentation.page.PageFragment

class ReadBookViewPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private var currentList: MutableList<PathToFoldersWithGlava> = mutableListOf()
    private var title: String? = ""

    override fun getCount(): Int {
        val listOfChapters = currentList.map { it.numberOfGlava }
        return listOfChapters.size
    }

    override fun getItem(position: Int): Fragment {
        return PageFragment.newInstance(title.toString(), currentList[position])
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "Chapter ${position+1}"
    }

    fun submitList(title: String?, pathToFoldersWithGlava: List<PathToFoldersWithGlava>){
        this.title = title
        currentList = pathToFoldersWithGlava as MutableList<PathToFoldersWithGlava>
        notifyDataSetChanged()
    }

}