package com.reactive.energowiki.ui.screens

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*

class GlossariesScreen : BaseFragment(R.layout.screen_glossaries) {

    override fun initialize() {

        close.setOnClickListener { finishFragment() }

        header.text = "Глоссарий"

    }
}