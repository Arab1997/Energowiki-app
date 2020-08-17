package com.reactive.energowiki.ui.screens.calculator.dvigatel

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.Dvigatel1_1Adapter
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_dvigatel_1.*
import kotlinx.android.synthetic.main.screen_dvigatel_1.recycler
import kotlin.math.pow

class Dvigatel1Screen : BaseFragment(R.layout.screen_dvigatel_1) {

    private val spinValues = arrayListOf<ArrayList<String>>()
    private var koef1: Double = 10.0.pow(-12.0)
    private var koef2: Double = 10.0.pow(-12.0)
    var a: Char? =null
    var position:Int=0
    override fun initialize() {

        initClicks()

        initSpinners()

        initEditTexts()
    }

    private fun initSpinners() {
       spinValues.add(arrayListOf("пОм", "нОм", "мкОм", "мОм", "Ом", "кОм", "МОм", "ГОм"))
        spinValues.add(arrayListOf("пВ", "нВ", "мкВ", "мВ", "В", "кВ", "МВ", "ГВ"))
        spinValues.add(arrayListOf("пА", "нА", "мА", "мА", "А", "кА", "МА", "ГА"))
        spinValues.add(arrayListOf("пW", "нW", "мкW", "мW", "W", "кW", "МW", "ГW"))
        val ra: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[0])
        ra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val ua: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[1])
        ua.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val ia: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[2])
        ia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val pa: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[3])
        pa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        recycler.adapter = Dvigatel1_1Adapter {
            when (it) {
                1 -> {header.text = "Ток ( I )"
                    spinner1.adapter=ra
                    spinner2.adapter=ua
                    input1Name.text="R="
                    input2Name.text="U="
                    position=1
                    a='A'
               }
                2 -> { header.text =  "Ток ( I )"
                    spinner1.adapter=pa
                    spinner2.adapter=ua
                    input1Name.text="P="
                    input2Name.text="U="
                    position=2
                    a='A'
                   }
                3 -> {header.text =  "Ток ( I )"
                    spinner1.adapter=pa
                    spinner2.adapter=ra
                    input1Name.text="P="
                    input2Name.text="R="
                    position=3
                    a='A'
                    }
                4 ->{header.text =  "Напряжение ( U )"
                    spinner1.adapter=ia
                    spinner2.adapter=ra
                    input1Name.text="I="
                input2Name.text="R="
                    position=4
                     a='В'
                   }
                5 -> {header.text =  "Напряжение ( U )"
                    spinner1.adapter=pa
                    spinner2.adapter=ia
                    input1Name.text="P="
                    input2Name.text="I="
                    position=5
                    a='В'
                    initCalculation()}
                6 -> {header.text =  "Напряжение ( U )"
                    spinner1.adapter=pa
                    spinner2.adapter=ra
                    input1Name.text="P="
                    input2Name.text="R="
                    position=6
                    a='В'
                   }
                7 ->  {header.text =  "Мощность ( P )"
                    spinner1.adapter=ia
                    spinner2.adapter=ua
                    input1Name.text="I="
                    input2Name.text="U="
                    position=7
                    a='W'
                }
                8 -> {header.text =  "Мощность ( P )"
                    spinner1.adapter=ia
                    spinner2.adapter=ua
                    input1Name.text="I="
                    input2Name.text="U="
                    position=8
                    a='W'
                }

                9 -> {header.text =  "Мощность ( P )"
                    spinner1.adapter=ua
                    spinner2.adapter=ra
                    input1Name.text="U="
                    input2Name.text="R="
                    position=9
                    a='W'
                }
                10 -> {header.text =  "Сопротивление ( R )"
                    spinner1.adapter=ua
                    spinner2.adapter=ia
                    input1Name.text="U="
                    input2Name.text="I="
                    position=10
                    a='Ω'
                }
                11 -> {header.text =  "Сопротивление ( R )"
                    spinner1.adapter=pa
                    spinner2.adapter=ia
                    input1Name.text="P="
                    input2Name.text="I="
                    position=10
                    a='Ω'
                }
                12 -> {header.text =  "Сопротивление ( R )"
                    spinner1.adapter=ua
                    spinner2.adapter=pa
                    input1Name.text="U="
                    input2Name.text="P="
                    position=12
                    a='Ω'
                }
                else -> Toast.makeText(requireContext(), "inDevelopment", Toast.LENGTH_SHORT).show()
            }
//            initCalculation()
        }.apply {
            setData(arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12) )
        }

        spinner1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                koef1 = 10.0.pow(-12.0)
                koef1 *= when (pos) {
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    2 -> 10.0.pow(-6.0)
                    3 -> 10.0.pow(-3.0)
                    4 -> 1.0
                    5 -> 10.0.pow(3)
                    6 -> 10.0.pow(6)
                    7 -> 10.0.pow(9)
                    else -> 1.0
                }
                initCalculation()
            }
        }

        spinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                koef1 =10.0.pow(-12.0)
                koef2 *= when (pos) {
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    2 -> 10.0.pow(-6.0)
                    3 -> 10.0.pow(-3.0)
                    4 -> 1.0
                    5 -> 10.0.pow(3)
                    6 -> 10.0.pow(6)
                    7 -> 10.0.pow(9)
                    else -> 1.0
                }
                initCalculation()
            }
        }
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input1.text?.clear()
            input2.text?.clear()
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
    }

    private fun initCalculation() {
       var inputNum1 = input1.text.toString().let { if(it.isEmpty()) 1.0 else it.toDouble() }
        var inputNum2 = input2.text.toString().let { if(it.isEmpty()) 1.0 else it.toDouble() }
      var resultt=0.0
        when(position){
            1 -> resultt=koef2*inputNum2/(koef1*inputNum1)
            2 -> resultt=koef1*inputNum1/(koef2*inputNum2)
            3 -> resultt=(koef1*inputNum1/(koef2*inputNum2)).pow(0.5)
            4 -> resultt=koef1*inputNum1 *koef2*inputNum2
            5 -> resultt=koef1*inputNum1/(koef2*inputNum2)
            6 -> resultt=(koef1*inputNum1*koef2*inputNum2).pow(0.5)
            7 -> resultt=koef1*inputNum1*koef2*inputNum2
            8 -> resultt=(koef1*inputNum1).pow(2)*koef2*inputNum2
            9 -> resultt=(koef1*inputNum1).pow(2)/koef2*inputNum2
            10-> resultt=(koef1*inputNum1)/(koef2*inputNum2)
            11 -> resultt=(koef1*inputNum1)/(koef2*inputNum2).pow(2)
            12 -> resultt=(koef1*inputNum1).pow(2)/(koef2*inputNum2)
            else -> resultt=000.0 }
        showResult(resultt, a!!)
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double, a:Char) {
        result.text = "$res $a"
    }
}