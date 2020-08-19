
package com.reactive.energowiki.ui.screens.calculator.engine

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_engine_7.*
import kotlin.math.PI

class Engine7Screen: BaseFragment(R.layout.screen_engine_7) {
    private val spinValues = arrayListOf<ArrayList<String>>()
    var koef1=0
    var koef2 = 1000.0

    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()

    }

    private fun initViews() {

        header.text = "Крутящий  момент"
    }

    private fun initSpinners() {
        spinValues.add(arrayListOf("Nm", "in lbf", "ft lbf"))
        spinValues.add(arrayListOf("кВт", "ЛС"))

        val a1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[0])
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = a1
        val a2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[1])
        a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = a2
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                koef1 = pos

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
                    0 -> 1000.0
                    1 -> 746.0
                    else -> 1000.0
                }
            initCalculation()
        }
    }
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input2.text?.clear()
            input3.text?.clear()
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

    }
    private fun initCalculation() {
        var inputNum2 = input2.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum3 = input3.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var res1:Double= 0.0
        var a:String=""

        if(koef1==0) {res1 =inputNum2*koef2*30/ (PI*inputNum3)
            a="Nm"}
        else if(koef1==2){
            res1 =inputNum2*koef2*1.34048*5252/ (1000*inputNum3)
            a="in lbf"}
        else if(koef1==1){
            res1 =inputNum2*koef2*1.34048*63025/ (1000*inputNum3)
            a="ft lbf"
        }
        else res1=00.0
        showResult((1000*res1).toInt().toDouble()/1000, a)
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res1: Double, a:String){
        result.text = "$res1 $a"
    }
}