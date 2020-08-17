package com.reactive.energowiki.ui.screens

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.ReferenceAdapter
import com.reactive.energowiki.ui.bottomsheets.DetailBottomSheet
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_recycler.*

class ReferenceScreen : BaseFragment(R.layout.screen_reference) {

    private lateinit var adapter: ReferenceAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "Справка"

        adapter = ReferenceAdapter {
            removePreviousCallback({
                val bottomSheet = DetailBottomSheet.newInstance(it)
                bottomSheet.show(childFragmentManager, "")
            })
        }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun observe() {
        viewModel.spravkas.observe(viewLifecycleOwner, Observer {
            adapter.setData(ArrayList(it))
        })
    }
}