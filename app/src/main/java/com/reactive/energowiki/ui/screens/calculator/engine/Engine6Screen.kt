
package com.reactive.energowiki.ui.screens.calculator.engine

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_engine_6.*

class Engine6Screen: BaseFragment(R.layout.screen_engine_6){

    private val spinValues = arrayListOf<ArrayList<String>>()
    var koef1=50
    var koef2=3000
    var koef3=2
    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()

    }


    private fun initViews() {

        header.text = "Скольжение"
    }
    private fun initSpinners() {
        spinValues.add(arrayListOf("50 Гц", "60 Гц"))
        spinValues.add(arrayListOf("3000", "1500", "1000", "750", "600", "500", "429", "375", "333", "300", "273", "240", "231", "214", "200"))
        spinValues.add(arrayListOf("2", "4", "6", "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30"))
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
        spinner5.adapter = a3

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) { koef1=1
                koef1=when(pos){
                    0 -> 50
                    1 -> 60
                    else -> 50
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
            ) { koef2=1
                koef2=when(pos){
                    0 -> 3000
                    1 -> 1500
                    2 -> 1000
                    3 -> 750
                    4 -> 600
                    5 -> 500
                    6 -> 429
                    7 -> 375
                    8 -> 333
                    9 -> 300
                    10 -> 273
                    11 -> 240
                    12 -> 231
                    13 -> 214
                    14 -> 200
                    else -> 3000
                }
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
                koef3=when(pos){
                    0 -> 2
                    1 -> 4
                    2 -> 6
                    3 -> 8
                    4 -> 10
                    5 -> 12
                    6 -> 14
                    7 -> 16
                    8 -> 18
                    9 -> 20
                    10 -> 22
                    11 -> 24
                    12 -> 26
                    13 -> 28
                    14 -> 30
                    else ->1

                }
                initCalculation()
            }
        }
    }
    private fun initClicks() {

        checkBox.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked)
            {input5Name.visibility=View.VISIBLE
            spinner5.visibility=View.VISIBLE }
            else {
                input5Name.visibility=View.GONE
                spinner5.visibility=View.GONE }
            initCalculation()
        }

//        if (checkBox.isChecked) {
//            Toast.makeText(requireContext(), "Check", Toast.LENGTH_LONG).show()
//        } else {
//            input5Name.visibility=View.GONE
//            spinner5.visibility=View.GONE
//            Toast.makeText(requireContext(), "no Check", Toast.LENGTH_LONG).show()
//        }
        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input3.text?.clear()
            input5Name.visibility=View.GONE
            spinner5.visibility=View.GONE
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

    }
    private fun initCalculation() {
        var inputNum3 = input3.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var res1:Double= 0.0

//        res1 = //todo

        showResult((res1*1000).toInt().toDouble()/1000)

    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res1: Double){
        result.text = "$res1 %"
    }
}
