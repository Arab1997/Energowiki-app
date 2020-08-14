package com.reactive.energowiki.ui.screens.calculator

import android.annotation.SuppressLint
import android.widget.ArrayAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_calc_conductor.*
class ConductorCalcScreen: BaseFragment(R.layout.screen_calc_conductor){

    private val spinValues = arrayListOf<ArrayList<String>>()
    private var koef1 :Double = 1.0
    private var koef2 :Double = 1.0
    private var koef3 :Double = 1.0

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
        spinner1.adapter = aa1
        val aa2: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[1])
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = aa2
        val aa3: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[2])
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = aa3
        val aa4: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[3])
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner4.adapter = aa4

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
        val l = input3.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
        val s = input4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val result= R * s / l
        showResult(result * koef1 * koef2 * koef3)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double) {
        result.text = "$res В"
    }

}