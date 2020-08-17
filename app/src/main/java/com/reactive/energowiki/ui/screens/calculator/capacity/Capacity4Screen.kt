package com.reactive.energowiki.ui.screens.calculator.capacity

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.bottomsheet_detail.*

class Capacity4Screen: BaseFragment(R.layout.screen_capacity_2){

    override fun initialize() {
        initClicks()

    }

    private fun initClicks(){
        close.setOnClickListener { finishFragment() }
    }

}