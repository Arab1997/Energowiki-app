package com.reactive.energowiki.ui.screens.main

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.NewsAdapter
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.content_toolbar.*

class LinksScreen: BaseFragment(R.layout.screen_links) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "полезные ссылка"




    }
}