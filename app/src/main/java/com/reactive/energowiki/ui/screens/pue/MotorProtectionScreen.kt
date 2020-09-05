package com.reactive.energowiki.ui.screens.pue

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.*
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.clearBtn
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.input1
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.input2
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.input3
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.input4
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.result
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.resultBtn
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.spinner1
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.spinner2
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.spinner3
import kotlinx.android.synthetic.main.screen_calc_alternative_curr.spinner4
import kotlinx.android.synthetic.main.screen_motor_protection.*
import kotlin.math.pow

class MotorProtectionScreen : BaseFragment(R.layout.screen_motor_protection) {
    private val spinValues = arrayListOf<ArrayList<String>>()
    private var koef1: Double = 10.0.pow(-12.0)
    private var koef2: Double = 10.0.pow(-12.0)
    private var koef3: Double = 10.0.pow(-12.0)
    private var koef4: Double = 10.0.pow(-12.0)


    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()
    }

    private fun initSpinners() {
        spinValues.add(arrayListOf("Медь", "Алюминий"))
        spinValues.add(
            arrayListOf(
                "Трехфазный",
                "Трехфазный + N",
                "Двухфазный",
                "Двухфазный + N",
                "Однофазный"
            )
        )
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

        spinValues.add(
            arrayListOf(
                "auto",
                "- 5 °C |23 °Ф",
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
                "100%",
                "15%",
                "25%",
                "40%",
                "60%"
            )
        )
        spinValues.add(
            arrayListOf(
                "p.u",
                "%"
            )
        )

        spinValues.add(
            arrayListOf(
                "m",
                "ft"
            )
        )

        spinValues.add(
            arrayListOf(
                "%",
                "V"
            )
        )

        spinValues.add(
            arrayListOf(
                "кв",
                "А"
            )
        )

        val aa1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[0])
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = aa1
        val aa2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[1])
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = aa2
        val aa3: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[2])
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = aa3
        val aa4: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[3])
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner4.adapter = aa4
        val aa5: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[4])
        aa5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner5.adapter = aa5

        val aa6: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[5])
        aa6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner6.adapter = aa6

        val aa7: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[6])
        aa7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner7.adapter = aa7

        val aa8: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[7])
        aa8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner8.adapter = aa8

        val aa9: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[8])
        aa9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner9.adapter = aa9


        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                koef1 = 1.0
                koef1 *= when (position) {
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    else -> 10.0.pow(-6.0)
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

                    else -> 10.0.pow(-12.0)
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
                position: Int,
                id: Long
            ) {
                koef3 = 1.0
                koef3 *= when (position) {
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    2 -> 10.0.pow(-6.0)
                    3 -> 10.0.pow(-3.0)
                    4 -> 1.0
                    5 -> 10.0.pow(3)
                    6 -> 10.0.pow(6)
                    7 -> 10.0.pow(9)
                    8 -> 10.0.pow(9)
                    9 -> 10.0.pow(9)
                    10 -> 10.0.pow(9)
                    else -> 10.0.pow(-12.0)
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
                position: Int,
                id: Long
            ) {
                koef4 = 1.0
                koef4 *= when (position) {
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    2 -> 10.0.pow(-6.0)
                    3 -> 10.0.pow(-3.0)
                    4 -> 1.0
                    5 -> 10.0.pow(3)
                    6 -> 10.0.pow(6)
                    7 -> 10.0.pow(9)
                    8 -> 10.0.pow(9)
                    9 -> 10.0.pow(9)
                    10 -> 10.0.pow(9)
                    11 -> 10.0.pow(9)
                    12 -> 10.0.pow(9)
                    else -> 10.0.pow(-12.0)
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
                position: Int,
                id: Long
            ) {
                koef4 = 1.0
                koef4 *= when (position) {
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    2 -> 10.0.pow(-6.0)
                    3 -> 10.0.pow(-3.0)
                    4 -> 1.0
                    else -> 10.0.pow(-12.0)
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
                position: Int,
                id: Long
            ) {
                koef4 = 1.0
                koef4 *= when (position) {
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    else -> 10.0.pow(-12.0)
                }
                initCalculation()
            }
        }

        spinner7.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    else -> 10.0.pow(-12.0)
                }
                initCalculation()
            }
        }

        spinner8.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    else -> 10.0.pow(-12.0)
                }
                initCalculation()
            }
        }

        spinner9.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                    0 -> 10.0.pow(-12.0)
                    1 -> 10.0.pow(-9.0)
                    else -> 10.0.pow(-12.0)
                }
                initCalculation()
            }
        }

    }

    private fun initViews() {
        header.text = "Цель электродвигателя"
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input1.text?.clear()
            input2.text?.clear()
            input3.text?.clear()
            input4.text?.clear()
            input5.text?.clear()
            input6.text?.clear()
            input7.text?.clear()
            input8.text?.clear()
        }

        resultBtn.setOnClickListener {
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()

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
        val R = input1.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val w_l = input2.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val w_c = input3.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
        val Е = input4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val T = input5.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val Y = input6.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val U = input7.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val I = input8.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        val result = U / (((koef1 * R).pow(2) + (koef3 * w_c - 1 / (w_l * koef2)).pow(2)).pow(0.5))
        showResult((result * koef4 * 1000).toInt().toDouble() / 1000)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double) {
        result.text = "$res А"
    }


}