package com.reactive.energowiki.ui.screens

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.OrganizationsAdapter
import com.reactive.energowiki.ui.bottomsheets.OrganizationBottomSheet
import com.reactive.energowiki.utils.extensions.loge
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_recycler.*

class OrganizationsScreen : BaseFragment(R.layout.screen_recycler) {

    private lateinit var adapter: OrganizationsAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "Организации"

        adapter = OrganizationsAdapter {
            removePreviousCallback({
                val bottomSheet = OrganizationBottomSheet.newInstance(it)
                bottomSheet.show(childFragmentManager, "")
            })
        }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun observe() {
        viewModel.organizations.observe(viewLifecycleOwner, Observer {
            adapter.setData(ArrayList(it))
        })
    }
}