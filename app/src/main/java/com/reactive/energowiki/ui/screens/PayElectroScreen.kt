package com.reactive.energowiki.ui.screens

import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.LinksAdapter
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_basics.*

class PayElectroScreen : BaseFragment(R.layout.screen_pay) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = "оплата за электренергию"
 }
}