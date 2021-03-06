
package com.reactive.energowiki.ui.screens.calculator.engine

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.utils.extensions.addItems
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_engine_10.*
import kotlinx.android.synthetic.main.screen_engine_10.clearBtn
import kotlinx.android.synthetic.main.screen_engine_10.input1
import kotlinx.android.synthetic.main.screen_engine_10.input3
import kotlinx.android.synthetic.main.screen_engine_10.input4
import kotlinx.android.synthetic.main.screen_engine_10.input5
import kotlinx.android.synthetic.main.screen_engine_10.result1
import kotlinx.android.synthetic.main.screen_engine_10.result2
import kotlinx.android.synthetic.main.screen_engine_10.resultBtn
import kotlinx.android.synthetic.main.screen_engine_10.spinner3
import kotlinx.android.synthetic.main.screen_engine_10.spinner4
import kotlinx.android.synthetic.main.screen_engine_11.*

class Engine10Screen: BaseFragment(R.layout.screen_engine_10){
    private val spinValues = arrayListOf<ArrayList<String>>()
    var koef1=2
    var koef2=1.0
    var koef3=1.0

    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()

    }
    private fun initViews() {

        header.text = "Двигатель компрессора"
    }
    private fun initSpinners() {
        spinValues.add(arrayListOf("2", "3", "4", "5", "6", "7", "8", "9", "10"))
        spinValues.add(arrayListOf("отн.ед", "%"))

        spinner2.addItems(requireContext(), spinValues[0])
        spinner3.addItems(requireContext(), spinValues[1])
        spinner4.addItems(requireContext(), spinValues[1])

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) { koef1+=pos

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
            ) {if(pos==1) koef2=0.01
            else koef2=1.0
                initCalculation()
            }
        }
        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {if(pos==1) koef3=0.01
            else koef3=1.0
                initCalculation()
            }
        }
            }
    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input1.text?.clear()
            input3.text?.clear()
            input4.text?.clear()
            input5.text?.clear()
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
        var inputNum1 = input1.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum3 = input3.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum4 = input4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum5 = input5.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var res1:Double= 0.0
        var res2:Double= 0.0
        if(inputNum3*koef2>1 ){
            input3.setText("1")

            Toast.makeText(requireContext(), "Кпд не может быть больше единицы", Toast.LENGTH_LONG).show()
            res1=0.0
        }
        else {
            if( inputNum4*koef3>1){
            input4.setText("1")
            Toast.makeText(requireContext(), "Кпд не может быть больше единицы", Toast.LENGTH_LONG).show()
            res1=0.0
        }
            else res1=inputNum1*koef1*inputNum5/(inputNum3*inputNum4*koef2*koef3*1000*60)

//            res1= //todo
        }

        res2=res1/746
        showResult((1000*res1).toInt().toDouble()/1000, (1000*res2).toInt().toDouble()/1000)
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res1: Double, res2:Double){
        result1.text = "$res1 кВт"
        result2.text = "$res2 HP"
    }
}