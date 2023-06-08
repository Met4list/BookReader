package com.metalist.bookreader

import com.metalist.bookreader.base.BaseBindingActivity
import com.metalist.bookreader.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main
}