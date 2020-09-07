package com.reactive.energowiki.ui.screens.calculator.capacity

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.dialog_crash.*
import kotlinx.android.synthetic.main.screen_capacity_1.*

class Capacity1Screen : BaseFragment(R.layout.screen_capacity_1) {

    private val magnitudes = charArrayOf('k', 'M', 'G', 'T', 'P', 'E') // enough for Long

    var total = 0.0F
    var C1 = 0.0F
    var C2 = 0.0F
    var C3 = 0.0F
    override fun initialize() {
        initClicks()
    }

    private fun initClicks() {
        close.setOnClickListener { finishFragment() }

        clear_bt_capacity_screen1.setOnClickListener {
            input_capacity_screen1_1.text?.clear()
            input_capacity_screen1_2.text?.clear()
            input_capacity_screen1_3.text?.clear()
        }

        TextChanged(input_capacity_screen1_1)
        TextChanged(input_capacity_screen1_2)
        TextChanged(input_capacity_screen1_3)

        radiobt_capacity_screen1_1.setOnClickListener {
            radiobt_capacity_screen1_2.setChecked(false)
            calculate()
        }
        radiobt_capacity_screen1_2.setOnClickListener {
            radiobt_capacity_screen1_1.setChecked(false)
            calculate()
        }


    }

    fun TextChanged(editText: EditText) {

        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                calculate()
            }
        })
    }

    fun calculate() {
        capacity_screen1_result1.text = ""

        var inputC1 = input_capacity_screen1_1.text.toString()
        var inputC2 = input_capacity_screen1_2.text.toString()
        var inputC3 = input_capacity_screen1_3.text.toString()

        if (inputC1 != "") C1 =1.0e-6F* inputC1.toFloat() else C1 = 0.0F
        if (inputC2 != "") C2 = 1.0e-9F* inputC2.toFloat()else C2 = 0.0F
        if (inputC3 != "") C3 = 1.0e-12F* inputC3.toFloat() else C3 = 0.0F

        if (radiobt_capacity_screen1_1.isChecked) {
            total = C1 + C2 + C3
            capacity_screen1_result1.text = total.toString() + " Farad"
        }

        if (radiobt_capacity_screen1_2.isChecked) {


            if (C1 == 0.0F) total = C2 * C3 / (C2 + C3)
            if (C2 == 0.0F) total = C1 * C3 / (C1 + C3)
            if (C3 == 0.0F) total = C2 * C1 / (C2 + C1)

            if (C2 == 0.0F && C1 == 0.0F) total = C3
            if (C2 == 0.0F && C3 == 0.0F) total = C1
            if (C1 == 0.0F && C3 == 0.0F) total = C2

            if (C1 == 0.0F && C2 == 0.0F && C3 == 0.0F) total = 0.0F

            if (C1 != 0.0F && C2 != 0.0F && C3 != 0.0F)
            total = C1 * C2 * C3 / (C2 * C3 + C1 * C3 + C1 * C2)

            capacity_screen1_result1.text = total.toString() + " Farad"
        }


    }





}