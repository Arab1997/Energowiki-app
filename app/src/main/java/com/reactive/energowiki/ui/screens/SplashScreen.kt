package com.reactive.energowiki.ui.screens

import android.os.CountDownTimer
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment

class SplashScreen : BaseFragment(R.layout.screen_splash) {

    private lateinit var listener: () -> Unit
     fun setListener(listener: () -> Unit) {
        this.listener = listener
    }

    override fun initialize() {
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                listener.invoke()
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }

}