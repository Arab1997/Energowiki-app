package com.reactive.energowiki.ui.screens

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.PaymentAdapter
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_basics.*

class PaymentScreen : BaseFragment(R.layout.screen_basics) {

    private lateinit var adapter: PaymentAdapter
    override fun initialize() {

        close.setOnClickListener { finishFragment() }

        header.text = "Оплата"

        adapter = PaymentAdapter {
        }.apply {
            setData(arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        }
        recycler.adapter = adapter
    }
}