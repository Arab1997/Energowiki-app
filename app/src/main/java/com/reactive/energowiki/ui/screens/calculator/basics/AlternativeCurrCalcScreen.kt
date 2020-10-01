package com.reactive.energowiki.ui.screens.calculator.basics

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.screens.calculator.onItemSelected
import com.reactive.energowiki.utils.extensions.addItems
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.*
import kotlin.math.pow

class AlternativeCurrCalcScreen : BaseFragment(R.layout.screen_calc_alternative_curr) {

    private val spinValues = arrayListOf<ArrayList<String>>()
    private val koef = arrayListOf<Double>()

    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()
    }

    private fun initSpinners() {
        koef.add(10.0.pow(-12.0))
        koef.add(10.0.pow(-12.0))
        koef.add(10.0.pow(-12.0))
        koef.add(10.0.pow(-12.0))

        spinValues.add(arrayListOf("пОм", "нОм", "мкОм", "мОм", "Ом", "кОм", "МОм", "ГОм"))
        spinValues.add(arrayListOf("пВ", "нВ", "мкВ", "мВ", "В", "кВ", "МВ", "ГВ"))
        spinner1.addItems(requireContext(), spinValues[0])
        spinner2.addItems(requireContext(), spinValues[0])
        spinner3.addItems(requireContext(), spinValues[0])
        spinner4.addItems(requireContext(), spinValues[1])

        spinner1.onItemSelected {
            koef[0] = 1.0
            koef[0] *= when (it) {
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
            koef[1] = 1.0
            koef[1] *= when (it) {
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

        spinner3.onItemSelected{
            koef[2] = 1.0
            koef[2] *= when (it) {
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

        spinner4.onItemSelected {
            koef[3] = 1.0
            koef[3] *= when (it) {
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

        header.text = "Закон Ома переменного тока"
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input1.text?.clear()
            input2.text?.clear()
            input3.text?.clear()
            input4.text?.clear()
        }

        resultBtn.setOnClickListener {
            initCalculation()
        }
    }

    private fun initEditTexts() {
        input1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }

        })
        input2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }

        })
        input3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }

        })
        input4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }

        })
    }


    private fun initCalculation() {
        val R = input1.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val w_l = input2.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val w_c = input3.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
        val U = input4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val result = U / (((koef[0] * R).pow(2) + (koef[2] * w_c - 1 / (w_l * koef[1])).pow(2)).pow(0.5))
        showResult((result * koef[3]*1000).toInt().toDouble()/1000)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double) {
        result.text = "$res А"
    }

}