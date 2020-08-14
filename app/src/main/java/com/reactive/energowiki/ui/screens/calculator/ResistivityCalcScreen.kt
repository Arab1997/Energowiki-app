package com.reactive.energowiki.ui.screens.calculator

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_calc_conductor.*
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
import java.lang.Math.pow
import kotlin.math.pow


class ResistivityCalcScreen : BaseFragment(R.layout.screen_calc_resistivity) {

    private val spinValues = arrayListOf<ArrayList<String>>()
    private var koef1 :Double = 1.0
    private var koef2 :Double = 1.0
    private var koef3 :Double = 1.0
    private var koef4 :Int = 1
    private var koef5:Double=1.0
    private var k:Double=1.0

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
        spinValues.add(arrayListOf("°C", "°F"))
        spinValues.add(arrayListOf("Медь","Алюминий","Никелин","Вольфрам","Середро","Железо","Сталь","Константан","Нихром","Латунь","Золото","Платина","Фехраль","Маганин","Цинк","Никель"))
        val ra: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[0])
        ra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = ra //R
        val la: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[1])
        la.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = la //l
        val aa: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[2])
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = aa  //S
        val ta: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[3])
        ta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner4.adapter = ta //t
        val pa: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[4])
        pa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = pa //material p

        spinner1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                koef1 = 1.0
                koef1 *= when (position) {
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    2 -> 10.0.pow(-6.0)
                    3 -> 10.0.pow(-3.0)
                    4 -> 1.0
                    5 -> 10.0.pow(3)
                    6 -> 10.0.pow(6)
                    7 -> 10.0.pow(9)
                    else -> 1.0
                }
                initCalculation()
            }
        }

        spinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                koef2 = 1.0
                koef2 *= when (position) {
                    0 -> 1.0
                    1 -> 0.3048
                    2 -> 10.0.pow(3.0)
                    3 -> 10.0.pow(-2.0)
                    4 -> 10.0.pow(-3)
                    else -> 1.0
                }
                initCalculation()
            }

        }
        spinner3.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                koef3 = 1.0
                koef3 *= when (position) {
                    0 -> 10.0.pow(-6.0) // mm^2 to m^2
                    1 -> 1.0 // m^2
                    2 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    else -> 1.0
                }
                initCalculation()
            }

        }
        spinner4.onItemSelectedListener= object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                koef4 = 1
                koef4 *= when (position) {
                    0-> 0
                    1 -> 1
                    else -> 0
                }
                initCalculation()
            }

        }

        spinner5.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
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
        val l = input2.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
        val s = input3.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val t=input4.text.toString().let { if(it.isEmpty()) 0.0 else it.toDouble() }
        var result=0.0
        if(koef4==1) result=R*s/(l*(1+(t-32)*5*k*pow(10.0,-3.0)/9))
        else result=R*s/(l*(1+t*k* pow(10.0, -3.0)))

        showResult(result * koef1 * koef3 / koef2)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double) {
        result.text = "$res Om*m"
    }
}