package com.reactive.energowiki.ui.screens.pue

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.wire_size_screen.*
import kotlin.math.pow

class WireSizeScreen : BaseFragment(R.layout.wire_size_screen) {

    private val spinValues = arrayListOf<ArrayList<String>>()
    private var koef1: Double = 0.015995
    private var koef2: Double = 10.0.pow(-12.0)
    private var koef3: Int = 1
    private var koef4: Double = 10.0.pow(-6.0)
    private var k: Double = 4.3


    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()
    }

    private fun initSpinners() {
        spinValues.add(
            arrayListOf(
                "Одно ядро открыто",
                "Два-основных закрыт",
                "Три-основные закрытые",
                "Два одноядерных замкнутых",
                "Три одноядерных закрытых",
                "Четыре одноядерных закрытых",
                "Одножильный кабель в воздухе",
                "Двухжильный кабель в воздухе",
                "Трехжильный кабель в воздухе",
                "Двухжильный кабель в земле",
                "Трехжильный кабель в земле"
            )
        )
        spinValues.add(arrayListOf("Медь", "Алюминий"))
        spinValues.add(
            arrayListOf(
                "auto",
                "- 5 °C |41 °Ф",
                " 0 °C | 32 °Ф",
                " 5 °C |41 °Ф",
                "10 °C |50 °Ф",
                "15 °C |59 °Ф",
                "20 °C |68 °Ф",
                "25 °C |77 °Ф",
                "30 °C |86 °Ф",
                "35 °C |95 °Ф",
                "40 °C |104 °Ф",
                "45 °C |113 °Ф",
                "50 °C |122 °Ф"
            )
        )
        spinValues.add(
            arrayListOf(
                "1 мм² |1.13 мм",
                "1.2 мм²| 1.4 мм",
                "1.5 мм²| 1.38 мм",
                "2 мм² | 1.6 мм",
                "2.5 мм²|1.78 мм",
                "3 мм²| 1.95 мм",
                "4 мм²| 2.26 мм",
                "5 мм²| 2.52 мм",
                "6 мм²| 2.76 мм",
                "8 мм²| 3.19 мм",
                "10 мм²| 3.57 мм",
                "16 мм²| 4.51 мм",
                "25 мм²| 5.64 мм",
                "35 мм²| 6.68 мм",
                "70 мм²| 9.44 мм",
                "95 мм²| 11 мм",
                "120 мм²| 12.36 мм",
                "150 мм²| 13.82 мм"
            )
        )
        val aa1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[0])
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = aa1 //Allowable ampacity 28.06 A

        val aa2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[1])
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = aa2 //material p

        val aa3: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[2])
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = aa3  // t

        val aa4: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[3])
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner4.adapter = aa4  //S

        //Allowable ampacity 28.06 A
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        koef1 = 0.015995
                        k = 4.3
                    }
                    1 -> {
                        koef1 = 0.023848
                        k = 4.2
                    }
                    2 -> {
                        koef1 = 0.418068
                        k = 0.1
                    }
                    3 -> {
                        koef1 = 0.0495
                        k = 5.0
                    }
                    4 -> {
                        koef1 = 0.014688
                        k = 4.1
                    }
                    5 -> {
                        koef1 = 0.08624
                        k = 6.0
                    }
                    6 -> {
                        koef1 = 0.0133
                        k = 2.6
                    }
                    7 -> {
                        koef1 = 0.4995
                        k = 0.05
                    }
                    8 -> {
                        koef1 = 1.0978
                        k = 0.1
                    }
                    9 -> {
                        koef1 = 0.074625
                        k = 0.25
                    }
                    10 -> {
                        koef1 = 0.02116
                        k = 4.0
                    }
                }
                initCalculation()
            }
        }
        //material p
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        koef1 = 0.015995
                        k = 4.3
                    }
                    1 -> {
                        koef1 = 0.023848
                        k = 4.2
                    }

                }
                initCalculation()
            }
        }
        //t
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                koef2 = 1.0
                koef2 *= when (position) {
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    2 -> 10.0.pow(-6.0)
                    3 -> 10.0.pow(-3.0)
                    4 -> 1.0
                    5 -> 10.0.pow(3)
                    6 -> 10.0.pow(6)
                    7 -> 10.0.pow(9)
                    8 -> 10.0.pow(12)
                    9 -> 10.0.pow(15)
                    10 -> 10.0.pow(18)
                    11 -> 10.0.pow(21)
                    12 -> 10.0.pow(24)
                    else -> 10.0.pow(-12.0)
                }
                initCalculation()
            }
        }
        // t
        /* spinner4.onItemSelectedListener= object: AdapterView.OnItemSelectedListener {
             override fun onNothingSelected(parent: AdapterView<*>?) {
             }

             override fun onItemSelected(
                 parent: AdapterView<*>?,
                 view: View?,
                 position: Int,
                 id: Long
             ) {
                 koef3 = 1
                 koef3 *= when (position) {
                     0 -> 1
                     1 -> 0
                     else -> 1
                 }
                 initCalculation()
             }
         }*/
//section
        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                koef4 = 1.0
                koef4 *= when (position) {
                    0 -> 10.0.pow(-6.0) // mm^2 to m^2
                    1 -> 1.0 // m^2
                    2 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    3 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    4 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    5 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    6 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    7 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    8 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    9 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    10 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    11 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    12 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    13 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    14 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    15 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    16 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    17 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    18 -> 2 * 10.0.pow(-6.0) // kcmil to m^2
                    else -> 10.0.pow(-6.0)
                }
                initCalculation()
            }

        }

    }

    private fun initViews() {

        header.text = "Допустимые электрические токи"
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input2.text?.clear()
            // input3.text?.clear()
            input4.text?.clear()
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

        input4.addTextChangedListener(object : TextWatcher {
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
        //val R = input2.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        /* val c = input2.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
         val f = input4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
         var result=0.0
         if(koef3==0) result=R*f/(koef1*(1+(c-32)*5*k*10.0.pow(-3.0)/9))
         else result=R*f/(koef1*(1+c*k*10.0.pow(-3.0)))

         showResult((result  * koef2 * koef4*10.0.pow(6.0)*1000).toInt().toDouble()/1000)*/
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double) {
        result.text = "$res м"
    }

}