package com.reactive.energowiki.ui.screens.main

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_toolbar.*

class NormDocScreen: BaseFragment(R.layout.screen_norm_doc) {
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        title.text = "нормативные документы"


    }
}