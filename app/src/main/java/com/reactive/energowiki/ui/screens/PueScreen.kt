/*
package com.reactive.energowiki.ui.screens

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.PueAdapter
import com.reactive.energowiki.ui.bottomsheets.OrganizationBottomSheet
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_recycler.*

class PueScreen : BaseFragment(R.layout.screen_recycler) {

    private lateinit var adapter: PueAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "ПУЭ"

        adapter = PueAdapter {
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
*/
