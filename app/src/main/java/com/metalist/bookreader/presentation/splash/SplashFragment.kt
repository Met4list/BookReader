package com.metalist.bookreader.presentation.splash

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.metalist.bookreader.R
import com.metalist.bookreader.base.BaseBindingFragment
import com.metalist.bookreader.databinding.FragmentSplashBinding

class SplashFragment : BaseBindingFragment<FragmentSplashBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = FirebaseAuth.getInstance().currentUser

        val connMgr = activity
            ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connMgr.activeNetworkInfo

        if (networkInfo?.isConnected == true) {
            Handler(Looper.getMainLooper())
                .postDelayed({
                    if (currentUser != null) {
                        findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                    } else {
                        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                    }
                }, 3000)
        } else {

        }
    }
}