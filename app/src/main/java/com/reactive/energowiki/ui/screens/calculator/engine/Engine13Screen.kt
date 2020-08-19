
package com.reactive.energowiki.ui.screens.calculator.engine

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_engine_13.*
import java.lang.Double.max
import kotlin.math.abs

class Engine13Screen: BaseFragment(R.layout.screen_engine_13){
    override fun initialize() {
        initViews()

        initClicks()

        initEditTexts()
    }
    private fun initViews() {

        header.text = "Ассиметрия токов"
    }
    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input11.text?.clear()
            input21.text?.clear()
            input31.text?.clear()
            input12.text?.clear()
            input22.text?.clear()
            input32.text?.clear()
            input13.text?.clear()
            input23.text?.clear()
            input33.text?.clear()
        }
        resultBtn.setOnClickListener {
            initCalculation()
        }
    }

    private fun initEditTexts() {
        input11.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input12.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input13.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input21.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input22.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input23.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input31.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input32.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input33.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })

    }

    @SuppressLint("NewApi")
    private fun initCalculation() {
        var inputNum11 = input11.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum12 = input12.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum13 = input13.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum21 = input21.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum22 = input22.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum23 = input23.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum31 = input31.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum32 = input32.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum33 = input33.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var res1:Double= 0.0
        var res2:Double= 0.0
        var res3:Double= 0.0

        res1=max(max(abs(inputNum11-2*inputNum12+inputNum13),
            abs(inputNum11+inputNum12-2*inputNum13) ),
            abs(inputNum13+inputNum12-2*inputNum11))*100/(inputNum11+inputNum12+inputNum13)
        res2=max(max(abs(inputNum21-2*inputNum22+inputNum23),
            abs(inputNum21+inputNum22-2*inputNum23) ),
            abs(inputNum23+inputNum22-2*inputNum21))*100/(inputNum21+inputNum22+inputNum23)
        res3=max(max(abs(inputNum31-2*inputNum32+inputNum33),
            abs(inputNum31+inputNum32-2*inputNum33) ),
            abs(inputNum33+inputNum32-2*inputNum31))*100/(inputNum11+inputNum12+inputNum13)

        showResult((1000*res1).toInt().toDouble()/1000, (1000*res2).toInt().toDouble()/1000,
            (1000*res3).toInt().toDouble()/1000)
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res1: Double, res2:Double, res3:Double){
        result1.text = "$res1 %"
        result2.text = "$res2 %"
        result3.text = "$res3 %"
    }


}