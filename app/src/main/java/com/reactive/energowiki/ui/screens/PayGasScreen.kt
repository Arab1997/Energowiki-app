package com.reactive.energowiki.ui.screens

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*


class PayGasScreen : BaseFragment(R.layout.screen_pay) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "оплата за газ"





 }
}