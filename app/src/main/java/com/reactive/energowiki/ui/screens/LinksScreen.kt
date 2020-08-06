package com.reactive.energowiki.ui.screens

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.LinksAdapter
import com.reactive.energowiki.ui.adapters.NewsAdapter
import com.reactive.energowiki.ui.bottomsheets.DetailBottomSheet
import com.reactive.energowiki.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_recycler.*

class LinksScreen : BaseFragment(R.layout.screen_recycler) {

    private lateinit var adapter: LinksAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "Полезные ссылки"

        adapter = LinksAdapter {
            removePreviousCallback({
                val bottomSheet = DetailBottomSheet.newInstance(it)
                bottomSheet.show(childFragmentManager, "")
            })
        }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun observe() {
        viewModel.links.observe(viewLifecycleOwner, Observer {
            adapter.setData(ArrayList(it))
        })
    }
}