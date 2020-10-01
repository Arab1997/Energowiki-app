package com.reactive.energowiki.ui.screens.calculator.basics

import android.annotation.SuppressLint
import android.widget.EditText
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.screens.calculator.onItemSelected
import com.reactive.energowiki.ui.screens.calculator.onTextChanged
import com.reactive.energowiki.utils.extensions.addItems
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_calc_continous_curr.clearBtn
import kotlinx.android.synthetic.main.screen_calc_continous_curr.input1
import kotlinx.android.synthetic.main.screen_calc_continous_curr.input2
import kotlinx.android.synthetic.main.screen_calc_continous_curr.result
import kotlinx.android.synthetic.main.screen_calc_continous_curr.resultBtn
import kotlinx.android.synthetic.main.screen_calc_continous_curr.spinner1
import kotlinx.android.synthetic.main.screen_calc_continous_curr.spinner2
import kotlin.math.pow

class ContinousCurrCalcScreen : BaseFragment(R.layout.screen_calc_continous_curr) {

    private val spinValues = arrayListOf<ArrayList<String>>()

    private var koef1: Double = 10.0.pow(-12.0)
    private var koef2: Double = 10.0.pow(-12.0)

    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()
    }

    private fun initSpinners() {
        spinValues.add(arrayListOf("пОм", "нОм", "мкОм", "мОм", "Ом", "кОм", "МОм", "ГОм"))
        spinValues.add(arrayListOf("пВ", "нВ", "мкВ", "мВ", "В", "кВ", "МВ", "ГВ"))

        spinner1.addItems(requireContext(), spinValues[0])
        spinner2.addItems(requireContext(), spinValues[1])

        spinner1.onItemSelected {
            koef1 = 1.0
            koef1 *= when (it) {
                0 -> 10.0.pow(-12.0)
                1 -> 10.0.pow(-9.0)
                2 -> 10.0.pow(-6.0)
                3 -> 10.0.pow(-3.0)
                4 -> 1.0
                5 -> 10.0.pow(3)
                6 -> 10.0.pow(6)
                7 -> 10.0.pow(9)
                else -> 10.0.pow(-12.0)
            }
            initCalculation()
        }

        spinner2.onItemSelected {
            koef1 *= when (it) {
                0 -> 10.0.pow(-12.0)
                1 -> 10.0.pow(-9.0)
                2 -> 10.0.pow(-6.0)
                3 -> 10.0.pow(-3.0)
                4 -> 1.0
                5 -> 10.0.pow(3)
                6 -> 10.0.pow(6)
                7 -> 10.0.pow(9)
                else -> 10.0.pow(-12.0)
            }
            initCalculation()
        }

    }

    private fun initViews() {

        header.text = "Закон Ома постоянного тока"
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input1.text?.clear()
            input2.text?.clear()
        }

        resultBtn.setOnClickListener {
            initCalculation()
        }
    }

    private fun initEditTexts() {

        setEdtListener(input1)
        setEdtListener(input2)

    }

    private fun setEdtListener(input: EditText) {
        input.onTextChanged {
            initCalculation()
        }
    }

    private fun initCalculation() {
        val R = input1.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val U = input2.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val result = U / R

        showResult((result * koef2 * 1000 / koef1).toInt().toDouble() / 1000)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double) {
        result.text = "$res А"
    }

}