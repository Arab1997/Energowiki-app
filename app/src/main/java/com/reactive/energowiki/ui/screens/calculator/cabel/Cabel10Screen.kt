package com.reactive.energowiki.ui.screens.calculator.cabel

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_cabel_10.*

class Cabel10Screen: BaseFragment(R.layout.screen_cabel_10){

    var koef1=2.71

    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()

    }

    private fun initViews() {

        header.text = "Длина кабеля"
    }
    private fun initSpinners() {

        val a1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayListOf("м", "фт"))
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = a1
        spinner2.adapter = a1

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                if (pos == 1) koef1 = 8.89
                else koef1 = 2.71
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
            ) {
                // todo
                initCalculation()
            }
        }
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {

            input3.text?.clear()
            input4.text?.clear()
        }
        resultBtn.setOnClickListener {
            initCalculation()
        }
    }

    private fun initEditTexts() {
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
        var inputNum1 = input3.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum2 = input4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }

        var res1=0.0 // todo
        // static
        res1 = 52.7
        showResult((res1*1000).toInt().toDouble()/1000)
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res1: Double){
        result.text = "$res1 мм²"
    }
}
