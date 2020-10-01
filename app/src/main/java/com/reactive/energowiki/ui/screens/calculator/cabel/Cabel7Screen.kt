package com.reactive.energowiki.ui.screens.calculator.cabel

import android.widget.ArrayAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_cabel_7.*

class Cabel7Screen : BaseFragment(R.layout.screen_cabel_7) {

    override fun initialize() {
        initViews()

        initSpinners()
    }

    private fun initViews() {

        header.text = "Цветовая кодировка проводов"

    }

    private fun initSpinners() {
        val spinValues = arrayListOf<String>("МЭК Великобритание и Европа", "NEC (Соединенные Штаты)", "CEC (Канада)")

        val a1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues)
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = a1
    }


}