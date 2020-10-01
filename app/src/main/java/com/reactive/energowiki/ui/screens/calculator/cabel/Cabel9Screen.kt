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
import kotlinx.android.synthetic.main.screen_cabel_9.*
import kotlinx.android.synthetic.main.screen_cabel_9.checkBox
import kotlinx.android.synthetic.main.screen_cabel_9.clearBtn
import kotlinx.android.synthetic.main.screen_cabel_9.input3
import kotlinx.android.synthetic.main.screen_cabel_9.input5Name
import kotlinx.android.synthetic.main.screen_cabel_9.result
import kotlinx.android.synthetic.main.screen_cabel_9.resultBtn
import kotlinx.android.synthetic.main.screen_cabel_9.spinner2
import kotlinx.android.synthetic.main.screen_engine_6.*

class Cabel9Screen: BaseFragment(R.layout.screen_cabel_9){
    private val spinValues = arrayListOf<ArrayList<String>>()
    var koef1=2.71
    var koef2=0
    var koef3=1.0
    var koef4=0
    var koef5=1
    var koef6=0.5
    var koef7=0.8
    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()

    }

    private fun initViews() {

        header.text = "Потери мощности"
    }
    private fun initSpinners() {
        spinValues.add(arrayListOf("Медь", "Алюминий"))
        spinValues.add(arrayListOf("Трехфазный", "Однофазный", "Постоянный ток"))
        spinValues.add(arrayListOf("м", "фт", "км", "см", "мм"))
        spinValues.add(arrayListOf("°C", "°F"))
        spinValues.add(arrayListOf("В", "кВ"))
        spinValues.add(arrayListOf("0.5 мм² | 0.8 мм","0.75 мм² | 0.98 мм","1.0 мм² | 1.13 мм", "1.2 мм² | 1.24 мм",
            "1.5 мм² | 1.38 мм", "2.0 мм² | 1.6 мм", "2.5 мм² | 1.78 мм", "3.0 мм² | 1.95 мм", "4.0 мм² | 2.26 мм", "5.0 мм² | 2.52 мм",
            "6.0 мм² | 2.76 мм", "8.0 мм² | 3.19 мм","10.0 мм² | 3.57 мм", "16.0 мм² | 4.51 мм", "25.0 мм² | 5.64 мм", "35.0 мм² | 6.68 мм",
            "50.0 мм² | 7.98 мм", "70.0 мм² | 9.44 мм", "95.0 мм² | 11 мм", "120.0 мм² | 12.36 мм", "0.5 мм² | 0.8 мм","150.0 мм² | 13.82 мм",
            "185.0 мм² | 15.35 мм", "240.0 мм² | 17.48 мм", "300.0 мм² | 19.54 мм", "400.0 мм² | 22.57 мм"))
        spinValues.add(arrayListOf("мм²", "AVG"))

        spinner11.addItems(requireContext(), spinValues[0])
        spinner12.addItems(requireContext(), spinValues[1])
        spinner2.addItems(requireContext(), spinValues[2])
        spinner3.addItems(requireContext(), spinValues[3])
        spinner4.addItems(requireContext(), spinValues[4])
        spinner81.addItems(requireContext(), spinValues[5])
        spinner82.addItems(requireContext(), spinValues[6])

        spinner11.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        spinner12.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) { koef2=pos
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
                koef3 = 1.0
                koef3*=when(pos){
                    1-> 0.305
                    2 -> 1000.0
                    3 -> 0.01
                    4 -> 0.001
                    else -> 1.0
                }
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
               koef4=pos
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
            ) {
                koef5 = 1
                koef5*= when (pos) {
                    1 -> 1000
                    else -> 1
                }
                initCalculation()
            }
        }
        spinner81.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                koef6 = 1.0
                koef7 =1.0
                when(pos){
                    0 ->{ koef6=0.5
                    koef7=0.8}
                    1 ->{ koef6=0.75
                        koef7=0.98}
                    2 ->{ koef6=1.0
                        koef7=1.13}
                    3 ->{ koef6=1.2
                        koef7=1.24}
                    4 ->{ koef6=1.5
                        koef7=1.38}
                    5 ->{ koef6=2.0
                        koef7=1.6}
                    6 ->{ koef6=2.5
                        koef7=1.78}
                    7 ->{ koef6=3.0
                        koef7=1.95}
                    8 ->{ koef6=4.0
                        koef7=2.26}
                    9 ->{ koef6=5.0
                        koef7=2.52}
                    10 ->{ koef6=6.0
                        koef7=2.76}
                    11 ->{ koef6=8.0
                        koef7=3.19}
                    12 ->{ koef6=10.0
                        koef7=3.57}
                    13 ->{ koef6=16.0
                        koef7=4.51}
                    14 ->{ koef6=25.0
                        koef7=5.64}
                    15 ->{ koef6=35.0
                        koef7=6.68}
                    16 ->{ koef6=50.0
                        koef7=7.98}
                    17 ->{ koef6=70.0
                        koef7=9.44}
                    18 ->{ koef6=95.0
                        koef7=11.0}
                    19 ->{ koef6=120.0
                        koef7=12.36}
                    20 ->{ koef6=150.0
                        koef7=13.82}
                    21 ->{ koef6=185.0
                        koef7=15.35}
                    22 ->{ koef6=240.0
                        koef7=17.48}
                    23 ->{ koef6=300.0
                        koef7=19.54}
                    24 ->{ koef6=400.0
                        koef7=22.57 }
                    else ->{koef6=0.5
                    koef7=0.8}
                }

                koef6 += pos //todo
                initCalculation()
            }
        }
    }

    private fun initClicks() {

        radioB1.setOnClickListener {
           if(radioB1.isChecked){
            input7.isClickable=false
           input6.isClickable=true
           radioB2.isChecked=false}
            else {
               input7.isClickable=true
               input6.isClickable=false
               radioB2.isChecked=true}
            initCalculation()
        }
        radioB2.setOnClickListener {
            if(radioB2.isChecked){
                input6.isClickable=false
                input7.isClickable=true
                radioB1.isChecked=false}
            else {
                input6.isClickable=true
                input7.isClickable=false
                radioB2.isChecked=true
            }
            initCalculation()
        }
        checkBox.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked)
            {spinner81.visibility=View.VISIBLE
                input8.visibility=View.GONE }
            else {
                input8.visibility=View.VISIBLE
                spinner81.visibility=View.GONE
                }
            initCalculation()
        }

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {

            input2.text?.clear()
            input3.text?.clear()
            input4.text?.clear()
            input5.text?.clear()
            input6.text?.clear()
            input7.text?.clear()
            input8.text?.clear()

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
        input6.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input7.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input8.addTextChangedListener(object : TextWatcher {
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
        var inputNum4 = input4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum5 = input5.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum6 = input6.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum7 = input7.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum8 = input8.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var res1:Double= 0.0
        var res2:Double= 0.0


//        res1 = //todo
//        res2=
        // static
        res1 = 5.0
        res2 = 10.5
        showResult((res1*1000).toInt().toDouble()/1000, (res2*1000).toInt().toDouble()/1000)
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res1: Double, res2:Double){
        result.text = "$res1 мВт | $res2 %"
    }
}
