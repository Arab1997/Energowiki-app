package com.reactive.energowiki.ui.screens.pue

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.utils.extensions.enableDisable
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_2_pue.*
import kotlinx.android.synthetic.main.screen_3_pue.*
import kotlinx.android.synthetic.main.screen_3_pue.input1
import kotlinx.android.synthetic.main.screen_3_pue.input2
import kotlinx.android.synthetic.main.screen_3_pue.input3
import kotlinx.android.synthetic.main.screen_3_pue.input4
import kotlinx.android.synthetic.main.screen_3_pue.resultBtn
import kotlinx.android.synthetic.main.screen_3_pue.spinner1
import kotlinx.android.synthetic.main.screen_3_pue.spinner2
import kotlinx.android.synthetic.main.screen_3_pue.spinner3
import kotlinx.android.synthetic.main.screen_3_pue.spinner4
import kotlin.math.pow

class Screen3 : BaseFragment(R.layout.screen_3_pue) {
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

        clear_Btn.setOnClickListener {
            input1.text?.clear()
            input4.text?.clear()
            input7.text?.clear()
        }
      /*  resultBtn.setOnClickListener {
            val dialog = context?.let { it1 ->
                PueReport1(
                    it1,
                    "%.2f".format(S),
                    spinner_screen1_1.selectedItem.toString(),
                    getMM(spinner_screen1_5).toString() + " м²",
                    getVeinValue(spinner_screen1_3).toString() + " м",
                    getTemperature(spinner_screen1_4).toString() + " °C"
                )
            }
            dialog!!.show()
        }*/
        input2.setText("0.85")
        input3.setText("0.9")
        input5.setText("4")
        input8.setText("1")

        TextChanged(input1)
        TextChanged(input2)
        TextChanged(input3)
        TextChanged(input4)
        TextChanged(input5)
        TextChanged(input6)
        TextChanged(input7)
        TextChanged(input8)

        spinnerSelectedListener(spinner1)
        spinnerSelectedListener(spinner2)
        spinnerSelectedListener(spinner3)
        spinnerSelectedListener(spinner4)
        spinnerSelectedListener(spinner5)
        spinnerSelectedListener(spinner6)
        spinnerSelectedListener(spinner7)
        spinnerSelectedListener(spinner8)
        spinnerSelectedListener(spinner9)
        resultBtn.setOnClickListener {
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()

            initCalculation()
        }
    }
    private fun TextChanged(editText: EditText) {

        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                initCalculation()
                if (input1.text.toString() == "" || input2.text.toString() == "" ||
                    input3.text.toString() == "" || input4.text.toString() == "" ||
                    input5.text.toString() == "" || input6.text.toString() == "" ||
                    input7.text.toString() == "" || input8.text.toString() == ""
                ) {
                    starting_value.text = ""
                    operating_value.text = ""
                    resultBtn.enableDisable(false)
                } else resultBtn.enableDisable(true)
            }
        })
    }

    private fun spinnerSelectedListener(spinner: Spinner) {

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                initCalculation()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
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

        val result = U * Y /  (Е * T *  I *((koef1 * R).pow(2) + (koef3 * w_c - 1 / (w_l * koef2)).pow(2)).pow(0.5))
        showResult((result * koef4 * 1000).toInt().toDouble() / 1000)


    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double) {
        starting_value.text = "$res А"
    }


}