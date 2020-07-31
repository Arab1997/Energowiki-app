package com.reactive.energowiki.ui.screens

import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.NewsAdapter
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_basics.*

class NewsScreen : BaseFragment(R.layout.screen_basics) {

    private lateinit var adapter: NewsAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "НОВОСТИ"

        adapter = NewsAdapter {
        }.apply {
            setData(arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}