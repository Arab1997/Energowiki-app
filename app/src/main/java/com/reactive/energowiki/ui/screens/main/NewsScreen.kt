package com.reactive.energowiki.ui.screens.main

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.NewsAdapter
import kotlinx.android.synthetic.main.content_toolbar.*

class NewsScreen: BaseFragment(R.layout.screen_news) {
    private lateinit var adapter: NewsAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        title.text = "НОВОСТИ"




    }
}