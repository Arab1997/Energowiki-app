package com.reactive.energowiki.ui.screens.calculator.capacity

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.dialog_crash.*

class Capacity1Screen: BaseFragment(R.layout.screen_capacity_1){

    override fun initialize() {
        initClicks()
    }

    private fun initClicks(){
        close.setOnClickListener { finishFragment() }
    }

}