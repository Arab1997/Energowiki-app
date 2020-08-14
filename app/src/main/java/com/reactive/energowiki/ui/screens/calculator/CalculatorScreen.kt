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
import kotlinx.android.synthetic.main.screen_calculation.*
import kotlin.math.pow


class CalculatorScreen : BaseFragment(R.layout.screen_calculation) {

    private var koef1 :Double = 1.0
    private var koef2 :Double = 1.0
    private var koef3 :Double = 1.0

    override fun initialize() {

        initViews()

        initClicks()

        initEditTexts()

        initSpinners()
    }

    private fun initViews() {

        header.text = "Закон Ома постоянного тока"
    }

    private fun initSpinners() {
        val rValues = arrayListOf<String>("пОм", "нОм", "мкОм", "мОм", "Ом", "кОм", "МОм", "ГОм")
        val lValues = arrayListOf<String>("м", "ft", "км", "см", "мм")
        val SValues = arrayListOf<String>("мм^2", "м^2", "kcmil")
        val ra: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, rValues)
        ra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = ra
        val la: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, lValues)
        la.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = la
        val aa: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, SValues)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = aa

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
    }

    private fun initCalculation() {
        val R = input1.text.toString().let { if (it.isEmpty()) 0 else it.toInt() }
        val l = input2.text.toString().let { if (it.isEmpty()) 1 else it.toInt() }
        val s = input3.text.toString().let { if (it.isEmpty()) 0 else it.toInt() }
        val result = R * s / l
        showResult(result * koef1 * koef2 * koef3)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double) {
        result.text = "$res В"
    }
}