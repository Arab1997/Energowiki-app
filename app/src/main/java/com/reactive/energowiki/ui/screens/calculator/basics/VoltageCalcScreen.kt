package com.reactive.energowiki.ui.screens.calculator.basics

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.utils.extensions.addItems
import com.reactive.energowiki.utils.extensions.disable
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_calc_voltage.*
import kotlinx.android.synthetic.main.screen_calc_voltage.result
import kotlinx.android.synthetic.main.screen_calc_voltage.resultBtn
import kotlinx.android.synthetic.main.screen_calc_voltage.spinner5

class VoltageCalcScreen : BaseFragment(R.layout.screen_calc_voltage) {

    private val spinValues = arrayListOf<ArrayList<String>>()

    private var res: Double = 0.0

    override fun initialize() {

        initViews()

        initClicks()

        initSpinners()

    }

    private fun initViews() {
        header.text = "Потери напяжения"
    }

    private fun initSpinners() {
        spinValues.add(arrayListOf("Медь", "Альюминий"))
        spinValues.add(arrayListOf("Трехфазный", "Двухфазный", "Однофазный", "Постоянный ток"))
        spinValues.add(arrayListOf("0.5 mm²| 0.8 mm", "0.75 mm²| 0.98 mm", "1.0 mm²| 1.13 mm", "1.2 mm²| 1.24 mm"))
        spinValues.add(arrayListOf("мм²","AWG"))
        spinValues.add(arrayListOf("мм²"))
        spinValues.add(arrayListOf("Один набор проводников", "2 проводника на фазу параллельно", "3 проводника на фазу параллельно", "4 проводника на фазу параллельно"))
        spinValues.add(arrayListOf("м", "ft"))
        spinValues.add(arrayListOf("°C", "F"))

        spinner1.addItems(requireContext(), spinValues[0])
        spinner2.addItems(requireContext(), spinValues[1])
        spinner3.addItems(requireContext(), spinValues[2])
        spinner4.addItems(requireContext(), spinValues[3])
        spinner5.addItems(requireContext(), spinValues[4])
        spinner6.addItems(requireContext(), spinValues[5])
        spinner7.addItems(requireContext(), spinValues[6])
        spinner8.addItems(requireContext(), spinValues[7])

        spinner5.disable()

    }

    private fun initClicks() {
        resultBtn.setOnClickListener { initCalculation() }

        close.setOnClickListener { finishFragment() }
    }

    private fun initCalculation() {
        // static
        res = 5.0

        showResult(5.0)
    }

    private fun showResult(res: Double) {
        result.text = res.toString()
    }


}
