package com.reactive.energowiki.ui.screens

import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.ReferenceAdapter
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_basics.*

class ReferenceScreen : BaseFragment(R.layout.screen_reference) {

    private lateinit var adapter: ReferenceAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "Справка"

        adapter = ReferenceAdapter {
        }.apply {
            setData(arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}