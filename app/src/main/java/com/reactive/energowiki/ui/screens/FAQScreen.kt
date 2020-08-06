package com.reactive.energowiki.ui.screens

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.FAQAdapter
import com.reactive.energowiki.ui.bottomsheets.DetailBottomSheet
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_recycler.*

class FAQScreen : BaseFragment(R.layout.screen_recycler) {

    private lateinit var adapter: FAQAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "Частые вопросы"

        adapter = FAQAdapter {
            removePreviousCallback({
                val bottomSheet = DetailBottomSheet.newInstance(it)
                bottomSheet.show(childFragmentManager, "")
            })
        }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun observe() {
        viewModel.faqs.observe(viewLifecycleOwner, Observer {
            adapter.setData(ArrayList(it))
        })
    }
}