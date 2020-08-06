package com.reactive.energowiki.ui.screens

import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.LinksAdapter
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_recycler.*

class PriceScreen : BaseFragment(R.layout.screen_price) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "цена электренергию"
 }
}