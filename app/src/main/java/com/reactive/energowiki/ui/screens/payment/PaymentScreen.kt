package com.reactive.energowiki.ui.screens.payment

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.PaymentAdapter
import com.reactive.energowiki.ui.screens.HomeData
import com.reactive.energowiki.ui.screens.HomeMenus
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_recycler.*

class PaymentScreen : BaseFragment(R.layout.screen_recycler) {

    private lateinit var adapter: PaymentAdapter
    override fun initialize() {

        close.setOnClickListener { finishFragment() }

        header.text = "Оплата"

        adapter = PaymentAdapter {
            addFragment(PayScreen.newInstance(it.type))
        }.apply {
            setData(
                arrayListOf(
                    HomeData(R.drawable.idea, "За электроэнергии", HomeMenus.ELECTRICITY),
                    HomeData(R.drawable.fire, "За газ ", HomeMenus.GAS)
                )
            )
        }
        recycler.adapter = adapter
    }
}
