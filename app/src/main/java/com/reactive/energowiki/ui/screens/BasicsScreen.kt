package com.reactive.energowiki.ui.screens

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.BasicsAdapter
import com.reactive.energowiki.ui.screens.calculator.CalculatorScreen
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_recycler.*

class BasicsScreen : BaseFragment(R.layout.screen_recycler) {

    private lateinit var adapter: BasicsAdapter
    private var data = arrayListOf<BasicsData>()
    override fun initialize() {

        data = arrayListOf()
//        data.add(BasicsData("LC Резонанс",)) // todo
        close.setOnClickListener { finishFragment() }

        header.text = "Основы"

        adapter = BasicsAdapter {
            addFragment(CalculatorScreen())
        }.apply { setData(data) }
        recycler.adapter = adapter
    }
}

data class BasicsData(
    val name: String,
    val toFind: String,
    val var1: String,
    val var2: String,
    val var3: String,
    val type: Formulas
)

enum class Formulas {
    LCRESONANS,
}
