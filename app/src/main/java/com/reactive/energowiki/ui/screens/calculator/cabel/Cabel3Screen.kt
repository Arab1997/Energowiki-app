package com.reactive.energowiki.ui.screens.calculator.cabel

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.utils.extensions.addItems
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_cabel_3.*


class Cabel3Screen: BaseFragment(R.layout.screen_cabel_3){
    private val spinValues = arrayListOf<ArrayList<String>>()
    var koef1 = 1.0
    var koef2 = 1.0
    var koef3 = 1.0

    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()

    }

    private fun initViews() {

        header.text = "Остаток кабеля"
    }

    private fun initSpinners() {
        spinValues.add(arrayListOf("мм", "дюйм", "см", "м"))

        spinner1.addItems(requireContext(), spinValues[0])
        spinner2.addItems(requireContext(), spinValues[0])
        spinner3.addItems(requireContext(), spinValues[0])

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) { koef1=1.0
                koef1=when(pos){
                    0 -> 1.0
                    1 -> 25.4
                    2 -> 10.0
                    3 -> 100.0
                    else -> 1.0
                }


                initCalculation()
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
            ) {koef2=1.0
                koef2=when(pos){
                    0 -> 1.0
                    1 -> 25.4
                    2 -> 10.0
                    3 -> 100.0
                    else -> 1.0
                }

                initCalculation()
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
            ) {koef3=1.0
                koef3=when(pos){
                    0 -> 1.0
                    1 -> 25.4
                    2 -> 10.0
                    3 -> 100.0
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
        var inputNum1 = input1.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum2 = input2.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum3 = input3.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum4 = input4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var res1:Double= 0.0

        // static
        res1 = 14.0
        showResult((1000*res1).toInt().toDouble()/1000)
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res1: Double){
        result.text = "$res1 м"
    }
}