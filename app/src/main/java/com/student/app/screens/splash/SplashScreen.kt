package com.student.app.screens.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.student.app.R
import com.student.app.screens.home.HomeScreen
import com.student.app.screens.intro.IntroScreen
import com.student.app.screens.signIn.SignInScreen
import com.student.app.utils.COMPLETE_INTRO
import com.student.app.utils.USER_DETAILS
import com.student.app.utils.loadScreen
import com.student.app.utils.prefs

class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("Test","User Details - ${prefs.get(USER_DETAILS, "")}")

        Handler(Looper.getMainLooper()).postDelayed({
            if(prefs.get(COMPLETE_INTRO,"") == "completed") {
                if(prefs.get(USER_DETAILS,"")!="") {
                    loadScreen(requireActivity(),HomeScreen())
                } else {
                    loadScreen(requireActivity(), SignInScreen())
                }
            } else {
                loadScreen(requireActivity(), IntroScreen())
            }
        }, 5000)
    }

    override fun onDetach() {
        super.onDetach()
    }

}