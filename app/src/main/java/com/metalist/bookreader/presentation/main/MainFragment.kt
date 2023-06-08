package com.metalist.bookreader.presentation.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.metalist.bookreader.R
import com.metalist.bookreader.base.BaseBindingFragment
import com.metalist.bookreader.databinding.FragmentMainBinding

class MainFragment : BaseBindingFragment<FragmentMainBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as? NavHostFragment
        val navController = nestedNavHostFragment?.navController
        NavigationUI.setupWithNavController(
            binding.bottomNavView,
            navController!!
        )

    }
}