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
import kotlinx.android.synthetic.main.screen_cabel_5.*
import kotlin.math.pow

class Cabel5Screen: BaseFragment(R.layout.screen_cabel_5){
    private val spinValues = arrayListOf<ArrayList<String>>()
    var koef1=2.71
    var koef2=1.0
    var koef3=1.0
    var koef4=1.0
    var koef5=1.0
    var koef6=1.0

    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()

    }

    private fun initViews() {

        header.text = "Веса металла кабеле"
    }
    private fun initSpinners() {
        spinValues.add(arrayListOf("Медь", "Алюминий"))
        spinValues.add(arrayListOf("м", "фт"))
        spinValues.add(arrayListOf("мм²", "мм", "kcmil"))
        spinValues.add(arrayListOf("1 сплошной", "19", "37", "49", "133 и боле") )

        spinner1.addItems(requireContext(), spinValues[0])
        spinner2.addItems(requireContext(), spinValues[1])
        spinner3.addItems(requireContext(), spinValues[2])
        spinner5.addItems(requireContext(), spinValues[2])
        spinner4.addItems(requireContext(), spinValues[3])
        spinner6.addItems(requireContext(), spinValues[3])

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) { if(pos==1) koef1=8.89
                else koef1=2.71
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
                if(pos==1) koef2=0.305
                else koef2=1.0

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
                    1 -> 1.0// todo
                    2 -> 2*10.0.pow(-6)
                    else -> 1.0
                }

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
            ) { koef4=1.0
                koef4=when(pos){
                    1 -> 1.02
                    2 -> 1.026
                    3 -> 1.03
                    4 -> 1.04
                    else -> 1.0
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
            ) { koef5=1.0
                koef5*=when(pos){
                    1 -> 1.0// todo
                    2 -> 2*10.0.pow(-6)
                    else -> 1.0
                }
                initCalculation()
            }
        }
        spinner6.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {koef6=1.0
                koef6=when(pos){
                    1 -> 1.02
                    2 -> 1.026
                    3 -> 1.03
                    4 -> 1.04
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
            input31.text?.clear()
            input32.text?.clear()
            input51.text?.clear()
            input52.text?.clear()
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
        input51.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input52.addTextChangedListener(object : TextWatcher {
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
        var inputNum31 = input31.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum32 = input32.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum51 = input51.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum52 = input52.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }

        var res1:Double= 0.0
        var res2:Double= 0.0


//        res1 = //todo
        res2=res1*2.05
        showResult((res1*1000).toInt().toDouble()/1000, (res2*1000).toInt().toDouble()/1000)
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res1: Double, res2:Double){
        result.text = "$res1 кг | $res2 lbs"
    }
}
