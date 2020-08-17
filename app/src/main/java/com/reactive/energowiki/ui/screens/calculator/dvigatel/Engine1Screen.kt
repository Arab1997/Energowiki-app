package com.reactive.energowiki.ui.screens.calculator.dvigatel

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_engine_1.*
import kotlin.math.pow

class Engine1Screen: BaseFragment(R.layout.screen_engine_1) {

    private val spinValues = arrayListOf<ArrayList<String>>()
    var type=0
    var koef1=1.0
    var koef2=1.0

    override fun initialize() {

        initClicks()

        initSpinners()

        initEditTexts()

    }

    private fun initSpinners() {
        spinValues.add(arrayListOf("Трехфазный", "Однофазный"))
        spinValues.add(arrayListOf("кВт", "ЛС"))
        spinValues.add(arrayListOf("отн.ед", "%"))

        val a1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[0])
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = a1
        val a2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[1])
        a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = a2
        val a3: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[2])
        a3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = a3

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) { type*= when (pos) {
                0 -> 0
                1 -> 1
                else -> 1
            }

            }
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                koef1=1000.0
                koef1*=when(pos){
                    0 -> 1000.0
                    1 -> 746.0
                    else -> 1000.0
                }

            }
        }
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                koef2=0.0
                koef2*=when(pos){
                    0 -> 1.0
                    1 -> 0.01
                    else -> 1.0
                }
            }
        }
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input2.text?.clear()
            input3.text?.clear()
            input4.text?.clear()
            input5.text?.clear()
        }
        resultBtn.setOnClickListener {
            initCalculation()
        }
    }

    private fun initEditTexts() {
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
        input5.addTextChangedListener(object : TextWatcher {
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
        var inputNum2:Double = input2.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
        var inputNum3:Double = input3.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
        var inputNum4:Double = input4.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
        var inputNum5:Double = input5.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
        var res=0.0
        when(type){
            0 -> res=inputNum2/(3.0.pow(0.5)*inputNum5*inputNum4*inputNum3*koef1*koef2)
            1 -> res=inputNum2/(inputNum5*inputNum4*inputNum3*koef1*koef2)
        }
        showResult(res)
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double){
        result.text = "$res A"
    }
}