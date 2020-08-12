package com.reactive.energowiki.ui.screens

import android.annotation.SuppressLint
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_calculation.*

class CalculatorScreen : BaseFragment(R.layout.screen_calculation) {


    override fun initialize() {

        initViews()

        initClicks()
    }

    private fun initViews() {

        header.text = "Закон Ома постоянного тока"
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input1.text?.clear()
            input2.text?.clear()
            input3.text?.clear()
        }

        resultBtn.setOnClickListener {
            initCalculation()
        }
    }

    private fun initCalculation() {
        showResult(0)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Int) {
        result.text = "$res В"
    }
}