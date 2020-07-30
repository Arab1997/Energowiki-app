package com.reactive.energowiki.ui.screens.main

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_toolbar.*

class FAQScreen: BaseFragment(R.layout.screen_faq) {
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        title.text = "частые вопросы"
