package com.reactive.energowiki.ui.screens.calculator.basics

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.screens.calculator.onItemSelected
import com.reactive.energowiki.ui.screens.calculator.onTextChanged
import com.reactive.energowiki.utils.extensions.addItems
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_calc_resistivity.*
import kotlinx.android.synthetic.main.screen_calc_resistivity.clearBtn
import kotlinx.android.synthetic.main.screen_calc_resistivity.input2
import kotlinx.android.synthetic.main.screen_calc_resistivity.input3
import kotlinx.android.synthetic.main.screen_calc_resistivity.input4
import kotlinx.android.synthetic.main.screen_calc_resistivity.result
import kotlinx.android.synthetic.main.screen_calc_resistivity.resultBtn
import kotlinx.android.synthetic.main.screen_calc_resistivity.spinner1
import kotlinx.android.synthetic.main.screen_calc_resistivity.spinner2
import kotlinx.android.synthetic.main.screen_calc_resistivity.spinner3
import kotlinx.android.synthetic.main.screen_calc_resistivity.spinner4
import kotlin.math.pow


class ResistivityCalcScreen : BaseFragment(R.layout.screen_calc_resistivity) {

    private val spinValues = arrayListOf<ArrayList<String>>()
    private var koef1 :Double =10.0.pow(-12.0)
    private var koef2 :Double = 1.0
    private var koef3 :Double = 10.0.pow(-12.0)
    private var koef4 :Int = 0
    private var koef5:Double=0.015995
    private var k:Double=4.3

    override fun initialize() {

        initViews()

        initClicks()

        initEditTexts()

        initSpinners()
    }

    private fun initViews() {

        header.text = "Удельное сопротивление и проводимость"
    }

    private fun initSpinners() {
        spinValues.add(arrayListOf("пОм", "нОм", "мкОм", "мОм", "Ом", "кОм", "МОм", "ГОм"))
        spinValues.add(arrayListOf("м", "ft", "км", "см", "мм"))
        spinValues.add(arrayListOf("мм²", "м²", "kcmil"))
        spinValues.add(arrayListOf("°C", "°Ф"))
        spinValues.add(arrayListOf("Медь","Алюминий","Никелин","Вольфрам","Середро","Железо","Сталь","Константан","Нихром","Латунь","Золото","Платина","Фехраль","Маганин","Цинк","Никель"))

        spinner1.addItems(requireContext(), spinValues[0])
        spinner2.addItems(requireContext(), spinValues[1])
        spinner3.addItems(requireContext(), spinValues[2])
        spinner4.addItems(requireContext(), spinValues[3])
        spinner5.addItems(requireContext(), spinValues[4])

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
            koef2 = 1.0
            koef2 *= when (it) {
                0 -> 1.0
                1 -> 0.3048
                2 -> 10.0.pow(3.0)
                3 -> 10.0.pow(-2.0)
                4 -> 10.0.pow(-3)
                else -> 1.0
            }
            initCalculation()
        }

        spinner3.onItemSelected {
            koef3 = 1.0
            koef3 *= when (it) {
                0 -> 10.0.pow(-6.0) // mm^2 to m^2
                1 -> 1.0 // m^2
                2 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                else -> 1.0
            }
            initCalculation()
        }
        spinner4.onItemSelected {
            koef4 = 1
            koef4 *= when (it) {
                0-> 0
                1 -> 1
                else -> 0
            }
            initCalculation()
        }

        spinner5.onItemSelected {
            when (it) {
                0 -> {koef5=0.015995
                    k=4.3 }
                1 -> {koef5=0.023848
                    k=4.2 }
                2 -> {koef5=0.418068
                    k=0.1 }
                3 -> {koef5=0.0495
                    k=5.0 }
                4 -> {koef5=0.014688
                    k=4.1 }
                5 -> {koef5=0.08624
                    k=6.0 }
                6 -> {koef5=0.0133
                    k=2.6 }
                7 ->{koef5=0.4995
                    k=0.05 }
                8 -> {koef5=1.0978
                    k=0.1 }
                9 -> {koef5=0.074625
                    k=0.25 }
                10 -> {koef5=0.02116
                    k=4.0 }
                11 -> {koef5=0.098654
                    k=3.9 }
                12 -> {koef5=1.1525
                    k=0.1 }
                13 -> {koef5=0.4699
                    k=0.01 }
                14 -> {koef5=0.054044
                    k=4.2 }
                15 -> {koef5=0.07569
                    k=6.5 }
            }
            initCalculation()
        }

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
        setEdtListener(input1)
        setEdtListener(input2)
        setEdtListener(input3)
        setEdtListener(input4)
    }

    private fun setEdtListener(input: EditText) {
        input.onTextChanged {
            initCalculation()
        }
    }

    private fun initCalculation() {
        val R = input1.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val l = input2.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
        val s = input3.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val t=input4.text.toString().let { if(it.isEmpty()) 0.0 else it.toDouble() }
        var result=0.0
        if(koef4==1) result=R*s/(l*(1+(t-32)*5*k*10.0.pow(-3.0)/9))
        else result=R*s/(l*(1+t*k* 10.0.pow(-3.0)))

        showResult((1000*result * koef1 * koef3 / koef2).toInt().toDouble()/1000)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double) {
        result.text = "$res Ом*м"
    }
}