package com.reactive.energowiki.ui.screens.calculator

import android.annotation.SuppressLint
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_calc_conductor.*
import kotlinx.android.synthetic.main.screen_calc_conductor.clearBtn
import kotlinx.android.synthetic.main.screen_calc_conductor.input2
import kotlinx.android.synthetic.main.screen_calc_conductor.input3
import kotlinx.android.synthetic.main.screen_calc_conductor.input4
import kotlinx.android.synthetic.main.screen_calc_conductor.result
import kotlinx.android.synthetic.main.screen_calc_conductor.resultBtn
import kotlinx.android.synthetic.main.screen_calc_conductor.spinner1
import kotlinx.android.synthetic.main.screen_calc_conductor.spinner2
import kotlinx.android.synthetic.main.screen_calc_conductor.spinner3
import kotlinx.android.synthetic.main.screen_calc_conductor.spinner4
import kotlinx.android.synthetic.main.screen_calc_resistivity.*
import java.lang.Math.pow
import kotlin.math.pow

class ConductorCalcScreen: BaseFragment(R.layout.screen_calc_conductor){

    private val spinValues = arrayListOf<ArrayList<String>>()
    private var koef1 :Double =  0.015995
    private var koef2 :Double = 1.0
    private var koef3 :Int = 1
    private var koef4 :Double =1.0
    private var k:Double=4.3


    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()
    }

    private fun initSpinners(){
        spinValues.add(arrayListOf("Медь","Алюминий","Никелин","Вольфрам","Середро","Железо","Сталь","Константан","Нихром","Латунь","Золото","Платина","Фехраль","Маганин","Цинк","Никель"))
        spinValues.add(arrayListOf("пОм", "нОм", "мкОм", "мОм", "Ом", "кОм", "МОм", "ГОм"))
        spinValues.add(arrayListOf("°C", "°F"))
        spinValues.add(arrayListOf("мм²", "м²", "kcmil"))
        val aa1: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[0])
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = aa1 //material p
        val aa2: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[1])
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = aa2  // R
        val aa3: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[2])
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = aa3  // t
        val aa4: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[3])
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner4.adapter = aa4  //S

        spinner1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {koef1=0.015995
                        k=4.3 }
                    1 -> {koef1=0.023848
                        k=4.2 }
                    2 -> {koef1=0.418068
                        k=0.1 }
                    3 -> {koef1=0.0495
                        k=5.0 }
                    4 -> {koef1=0.014688
                        k=4.1 }
                    5 -> {koef1=0.08624
                        k=6.0 }
                    6 -> {koef1=0.0133
                        k=2.6 }
                    7 ->{koef1=0.4995
                        k=0.05 }
                    8 -> {koef1=1.0978
                        k=0.1 }
                    9 -> {koef1=0.074625
                        k=0.25 }
                    10 -> {koef1=0.02116
                        k=4.0 }
                    11 -> {koef1=0.098654
                        k=3.9 }
                    12 -> {koef1=1.1525
                        k=0.1 }
                    13 -> {koef1=0.4699
                        k=0.01 }
                    14 -> {koef1=0.054044
                        k=4.2 }
                    15 -> {koef1=0.07569
                        k=6.5 }
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

        spinner3.onItemSelectedListener= object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                koef3 = 1
                koef3 *= when (position) {
                    0 -> 1
                    1 -> 0
                    else -> 0
                }
                initCalculation()
            }
        }

        spinner4.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                koef4 = 1.0
                koef4 *= when (position) {
                    0 -> 10.0.pow(-6.0) // mm^2 to m^2
                    1 -> 1.0 // m^2
                    2 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    else -> 1.0
                }
                initCalculation()
            }

        }
    }

    private fun initViews() {

        header.text = "Длина проводника"
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input2.text?.clear()
            input3.text?.clear()
            input4.text?.clear()
        }

        resultBtn.setOnClickListener {
            initCalculation()
        }
    }

    private fun initCalculation() {
        val R = input2.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val t = input3.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
        val s = input4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var result=0.0
        if(koef3==0) result=R*s/(koef1*(1+(t-32)*5*k*pow(10.0,-3.0)/9))
        else result=R*s/(koef1*(1+t*k*pow(10.0,-3.0)))

        showResult(result  * koef2 * koef4*pow(10.0, 6.0))
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double) {
        result.text = "$res m"
    }

}