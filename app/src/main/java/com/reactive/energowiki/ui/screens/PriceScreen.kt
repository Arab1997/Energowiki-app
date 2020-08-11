package com.reactive.energowiki.ui.screens

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*

class PriceScreen : BaseFragment(R.layout.screen_price) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "цена электренергию"
 }
}