package com.reactive.energowiki.ui.screens

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.*

class HomeScreen : BaseFragment(R.layout.screen_home) {

    private lateinit var mainAdapter: MenuAdapter
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var faqAdapter: FAQAdapter
    private lateinit var linksAdapter: LinksAdapter

    override fun initialize() {

    }
}