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
import kotlinx.android.synthetic.main.screen_4_pue.clearBtn
import kotlinx.android.synthetic.main.screen_4_pue.input1
import kotlinx.android.synthetic.main.screen_4_pue.input2
import kotlinx.android.synthetic.main.screen_4_pue.input3
import kotlinx.android.synthetic.main.screen_4_pue.input4
import kotlinx.android.synthetic.main.screen_4_pue.input5
import kotlinx.android.synthetic.main.screen_4_pue.input6
import kotlinx.android.synthetic.main.screen_4_pue.input7
import kotlinx.android.synthetic.main.screen_4_pue.result
import kotlinx.android.synthetic.main.screen_4_pue.resultBtn
import kotlinx.android.synthetic.main.screen_4_pue.spinner1
import kotlinx.android.synthetic.main.screen_4_pue.spinner2
import kotlinx.android.synthetic.main.screen_4_pue.spinner3
import kotlinx.android.synthetic.main.screen_4_pue.spinner4
import kotlinx.android.synthetic.main.screen_4_pue.spinner5
import kotlinx.android.synthetic.main.screen_4_pue.spinner6
import kotlinx.android.synthetic.main.screen_4_pue.*
import kotlin.math.pow

class Screen4 : BaseFragment(R.layout.screen_4_pue) {
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
                "Однофазный",
                "DC"
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
                "%",
                "V"

            )
        )

        spinValues.add(
            arrayListOf(
                "0.5 мм² |0.8 мм",
                "0.75 мм²|0.98 мм",
                "1  мм²  |1.13 мм",
                "1.2 мм² | 1.6 мм",
                "1.5 мм² |1.38 мм",
                "2  мм² | 1.6 мм",
                "2.5 мм²| 1.78 мм",
                " 3 мм² | 1.95 мм",
                " 5 мм² | 2.52 мм",
                " 6 мм² | 2.78 мм",
                " 8 мм² | 3.19 мм",
                "10 мм² | 3.57 мм",
                "16 мм² | 4.51 мм",
                "25 мм²| 5.64 мм",
                "35 мм²| 6.68 мм",
                "70 мм²| 9.44 мм",
                "95 мм²| 11 мм",
                "120 мм²| 12.36 мм",
                "150 мм²| 13.82 мм",
                "185 мм²| 15.35 мм",
                "240 мм²| 17.48 мм",
                "300 мм²| 19.54 мм",
                "400 мм²| 22.57 мм"
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
                    2 -> 10.0.pow(-9.0)
                    3 -> 10.0.pow(-9.0)
                    4 -> 10.0.pow(-9.0)
                    5 -> 10.0.pow(-9.0)
                    6 -> 10.0.pow(-9.0)
                    7 -> 10.0.pow(-9.0)
                    8 -> 10.0.pow(-9.0)
                    9 -> 10.0.pow(-9.0)
                    10 -> 10.0.pow(-9.0)
                    11 -> 10.0.pow(-9.0)
                    12 -> 10.0.pow(-9.0)
                    13 -> 10.0.pow(-9.0)
                    14 -> 10.0.pow(-9.0)
                    15 -> 10.0.pow(-9.0)
                    16 -> 10.0.pow(-9.0)
                    17 -> 10.0.pow(-9.0)
                    18 -> 10.0.pow(-9.0)
                    19 -> 10.0.pow(-9.0)
                    20 -> 10.0.pow(-9.0)
                    21 -> 10.0.pow(-9.0)
                    22 -> 10.0.pow(-9.0)
                    else -> 10.0.pow(-12.0)
                }
                initCalculation()
            }
        }

    }

    private fun initViews() {
        header.text = "Максимальная длина"
    }

    private fun initClicks() {
        input1.setText("0.85")
        input2.setText("3")
        input3.setText("4")
        input4.setText("")
        input5.setText("")
        input6.setText("")
        input7.setText("1")

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {

            input4.text?.clear()
            input5.text?.clear()
            input6.text?.clear()

        }

        resultBtn.setOnClickListener {
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()

            initCalculation()
        }
        if (radioCurrent.isChecked) {
            input6.enableDisable(false)
        }
        radioPower.setOnClickListener {
            input6.enableDisable(true)
            input5.enableDisable(false)
            radioCurrent.isChecked = false
        }

        radioCurrent.setOnClickListener {
            input6.enableDisable(false)
            input5.enableDisable(true)

            radioPower.isChecked = false
        }



        TextChanged(input1)
        TextChanged(input2)
        TextChanged(input3)
        TextChanged(input4)
        TextChanged(input5)
        TextChanged(input6)
        TextChanged(input7)
        /*      TextChanged(input_screen1_7)
              TextChanged(input_screen1_8)
              TextChanged(input_screen1_9)*/

        spinnerSelectedListener(spinner1)
        spinnerSelectedListener(spinner2)
        spinnerSelectedListener(spinner3)
        spinnerSelectedListener(spinner4)
        spinnerSelectedListener(spinner5)
        spinnerSelectedListener(spinner6)

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
                    input7.text.toString() == ""  /*|| input_screen1_7.text.toString() == "" ||
                    input_screen1_8.text.toString() == "" || input_screen1_9.text.toString() == ""*/
                ) {
                    result.text = ""
                    result.enableDisable(false)
                } else result.enableDisable(true)
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
    }

    private fun initCalculation() {

        if (input1.text.toString() != "" && input2.text.toString() != "" && input3.text.toString() != ""
            && input4.text.toString() != "" && input5.text.toString() != "" && input6.text.toString() != ""
        ) {
            val materialData = getMaterialValue(spinner1)
            //   phase
            val phaseData = getPhase(spinner2)
            //   жильных
            val veinData = getVeinValue(spinner3)
            //  Температура
            val tempData = getTemperature(spinner4)
            //  потери
            val poteri = getPoteri(spinner5, input3)
            //  длина
            val length = getMM(spinner6)


            val R = input1.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            val w_l = input2.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            val w_c = input3.text.toString().let { if (it.isEmpty()) 1.0 else it.toDouble() }
            val Е = input4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            val T = input5.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            val Y = input6.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            val U = input7.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            //  val result =  (U * Е) * T / Y / (((koef1 * R).pow(2) + (koef3 * w_c - 1 / (w_l * koef2)).pow(2)).pow(0.5))

            val result =
                (getPhase(spinner2).r20 * materialData.r20 * (w_c) + (w_l) * getMM(
                    spinner6
                ) * R * Е * Y / T * 100) / (U * getPoteri(spinner5, input3))
            showResult((result * koef4 * 1000).toInt().toDouble() / 1000)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Double) {
        result.text = "$res А"
    }

    private fun getMaterialValue(spinner: Spinner): MaterialData {
        var t: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> { //"Медь"  Copper
                t = 1.78
            }
            1 -> { //"Алюминий",ok Alumin
                t = 2.9
            }
        }
        return MaterialData(t)
    }

    private fun getVeinValue(spinner: Spinner): Double {
        var t: Double = 0.0

        when (spinner.selectedItemPosition) {
            0 -> { // Одножильных провод открыто
                t = 16.0
            }
            1 -> { //Один двух жильных закрыто
                t = 17.0
            }
            2 -> { //Один трехжильных закрыто
                t = 18.0
            }
            3 -> { //Два одножильных закрыто
                t = 15.0
            }
            4 -> { //Три одножильных закрыто
                t = 16.0
            }
            5 -> { //Четыре одножильных закрыто
                t = 20.0
            }
            6 -> { //Двухжильный кабель в воздухе
                t = 19.0
            }
            7 -> { //Трехжильный кабель в воздухе
                t = 25.0
            }
            8 -> { //Двухжильный кабель в земле
                t = 19.0
            }
            9 -> { //Трехжильный кабель в земле
                t = 19.0
            }
        }
        return t
    }

    private fun getPhase(spinner: Spinner): MaterialData {
        var t: Double = 0.0

        when (spinner.selectedItemPosition) {
            0 -> { // Трехфазный
                t = 3.0
            }
            1 -> { //Трехфазный + N
                t = 3.0
            }
            2 -> { //Двухфазный
                t = 2.0
            }
            3 -> { //Двухфазный + N
                t = 2.0
            }
            4 -> { //Однофазный
                t = 1.0
            }
        }
        return MaterialData(t)
    }

    private fun getTemperature(spinner: Spinner): Double {
        var temp: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> {
                temp = 5.0
            }
            1 -> {
                temp = 5.0
            }
            2 -> {
                temp = 0.0
            }
            3 -> {
                temp = 5.0
            }
            4 -> {
                temp = 10.0
            }
            5 -> {
                temp = 15.0
            }
            6 -> {
                temp = 20.0
            }
            7 -> {
                temp = 25.0
            }
            8 -> {
                temp = 30.0
            }
            9 -> {
                temp = 35.0
            }
            10 -> {
                temp = 45.0
            }
            11 -> {
                temp = 50.0
            }
        }
        return temp
    }

    private fun getMM(spinner: Spinner): Double {
        var mm: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> {
                mm = 10.0
            }
            1 -> {
                mm = 1.0
            }
        }
        return mm
    }

    private fun getPoteri(spinner: Spinner, editText: EditText): Double {
        var temp: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> {
                temp = editText.text.toString().toDouble() * 10
            }
            1 -> {
                temp = editText.text.toString().toDouble() * 100
            }
        }
        return temp
    }

}