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
import com.reactive.energowiki.utils.extensions.addItems
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_cabel_4.*

class Cabel4Screen: BaseFragment(R.layout.screen_cabel_4){

    private val spinValues = arrayListOf<ArrayList<String>>()
    var koef1=1
    var koef2=1
    var koef3=1

    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()

    }

    private fun initViews() {

        header.text = "Индуктивное сопротивление кабеля"
    }
    private fun initSpinners() {
        spinValues.add(arrayListOf("мм²", "мм", "kcmil"))
        spinValues.add(arrayListOf("Равносторонний", "Прямоугольный", "Плоский", "Неравный") )
        spinValues.add(arrayListOf("3 - 6", "7 - 18", "19 - 36", "37 - 60", " > 60", "1 (сплошной)"))
        spinner2.addItems(requireContext(), spinValues[0])
        spinner3.addItems(requireContext(), spinValues[1])
        spinner5.addItems(requireContext(), spinValues[2])

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) { koef1=1
                // todo koefitsient?
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
            ) {
                if(pos==3){ koef2=0
                    lay.visibility=View.VISIBLE}
                else {lay.visibility=View.GONE
                koef2=1}
                initCalculation()
            }
        }
        spinner5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) { koef3=1
                koef3=pos+1
                initCalculation()
            }
        }
    }
    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input1.text?.clear()
            input2.text?.clear()
            input41.text?.clear()
            input42.text?.clear()
            input43.text?.clear()
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
        input41.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input42.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input43.addTextChangedListener(object : TextWatcher {
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
        var inputNum41 = input41.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        if(koef2==0){
            var inputNum42 = input42.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            var inputNum43 = input43.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        }
        var res1 = 0.0


        res1 = inputNum1

        showResult((res1*1000).toInt().toDouble()/1000)

    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res1: Double){
        result.text = "$res1 Ом/км"
    }
}
