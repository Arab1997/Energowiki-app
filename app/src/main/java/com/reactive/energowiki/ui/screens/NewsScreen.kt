package com.reactive.energowiki.ui.screens

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.NewsAdapter
import com.reactive.energowiki.ui.bottomsheets.NewsBottomSheet
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_recycler.*

class NewsScreen : BaseFragment(R.layout.screen_recycler) {

    private lateinit var adapter: NewsAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "НОВОСТИ"

        adapter = NewsAdapter {
            removePreviousCallback({
                val bottomSheet = NewsBottomSheet.newInstance(it)
                bottomSheet.show(childFragmentManager, "")
            })
        }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun observe() {
        viewModel.news.observe(viewLifecycleOwner, Observer {
            adapter.setData(ArrayList(it))
        })
    }
}