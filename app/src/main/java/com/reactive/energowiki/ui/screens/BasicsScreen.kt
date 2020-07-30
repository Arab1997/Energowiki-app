package com.reactive.energowiki.ui.screens

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.BasicsAdapter
import kotlinx.android.synthetic.main.screen_basics.*

class BasicsScreen : BaseFragment(R.layout.screen_basics) {

    private lateinit var adapter: BasicsAdapter
    override fun initialize() {
        adapter = BasicsAdapter {
        }.apply {
            setData(arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        }
        recycler.adapter = adapter
    }
}