
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
import kotlinx.android.synthetic.main.screen_engine_5.*

class Engine5Screen: BaseFragment(R.layout.screen_engine_5){
    private val spinValues = arrayListOf<ArrayList<String>>()
    var koef1=1.0

    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()

    }
    private fun initViews() {

        header.text = "Расчет Кпд"
    }
    private fun initSpinners() {
        spinValues.add(arrayListOf("кВт", "ЛС"))

        val a1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[0])
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = a1

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                    1 -> 0.746
                    else -> 1.0
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

        res1 = 100*inputNum2*koef1/inputNum3

        if(res1<100 && res1>=0) {
            img.visibility= View.INVISIBLE
            showResult((1000*res1).toInt().toDouble()/1000)
        }
        else{
            img.visibility= View.VISIBLE
            result.text=""
        }
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res1: Double){
        result.text = "$res1 %"
    }
}