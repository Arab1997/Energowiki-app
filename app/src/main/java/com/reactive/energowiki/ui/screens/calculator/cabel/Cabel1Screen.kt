package com.reactive.energowiki.ui.screens.calculator.cabel

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.utils.extensions.toast
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_cabel_1.*
import kotlinx.android.synthetic.main.screen_engine_7.*
import kotlinx.android.synthetic.main.screen_engine_7.clearBtn
import kotlinx.android.synthetic.main.screen_engine_7.input2
import kotlinx.android.synthetic.main.screen_engine_7.input3
import kotlinx.android.synthetic.main.screen_engine_7.result
import kotlinx.android.synthetic.main.screen_engine_7.resultBtn
import kotlin.math.PI

class Cabel1Screen: BaseFragment(R.layout.screen_cabel_1){
    override fun initialize() {
            initViews()

            initClicks()

            initEditTexts()

        }

        private fun initViews() {

            header.text = "Кабелей в трубе"
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
            var inputNum1 = input1.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            var inputNum2 = input2.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            var inputNum3 = input3.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            var res1:Double= 0.0

            if(inputNum2>inputNum1) {
            toast(requireContext(), "Наружн.диам.кабеля>Внутр.диам.трубы")
            }
            else {
//                res1 =// todo
                }
            showResult((1000*res1).toInt().toDouble()/1000)
        }
        @SuppressLint("SetTextI18n")
        private fun showResult(res1: Double){
            result.text = "$res1"
        }
    }
