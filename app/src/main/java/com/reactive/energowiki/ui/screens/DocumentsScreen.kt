package com.reactive.energowiki.ui.screens

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.DocumentsAdapter
import com.reactive.energowiki.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_recycler.*

class DocumentsScreen : BaseFragment(R.layout.screen_recycler) {

    private lateinit var adapter: DocumentsAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "Нормативные документы"

        adapter = DocumentsAdapter {
            inDevelopment(requireContext())
        }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun observe() {
        viewModel.documents.observe(viewLifecycleOwner, Observer {
            adapter.setData(ArrayList(it))
        })
    }
}