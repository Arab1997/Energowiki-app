package com.reactive.energowiki.ui.screens.pue

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.dialogs.pueReports.PueReport1
import com.reactive.energowiki.utils.extensions.enableDisable
import kotlinx.android.synthetic.main.bottomsheet_detail.close
import kotlinx.android.synthetic.main.bottomsheet_detail.header
import kotlinx.android.synthetic.main.screen_2_pue.clear_btn_screen1
import kotlinx.android.synthetic.main.screen_2_pue.result_text1
import kotlinx.android.synthetic.main.screen_2_pue.input_screen1_1
import kotlinx.android.synthetic.main.screen_2_pue.input_screen1_2
import kotlinx.android.synthetic.main.screen_2_pue.result_btn_screen1
import kotlinx.android.synthetic.main.screen_2_pue.spinner_screen1_1
import kotlinx.android.synthetic.main.screen_2_pue.spinner_screen1_2
import kotlinx.android.synthetic.main.screen_2_pue.spinner_screen1_3
import kotlinx.android.synthetic.main.screen_2_pue.spinner_screen1_4
import kotlinx.android.synthetic.main.screen_2_pue.*
import kotlinx.android.synthetic.main.screen_2_pue.checkBox
import kotlinx.android.synthetic.main.screen_2_pue.input_screen1_3
import kotlinx.android.synthetic.main.screen_2_pue.radio_btn_screen3_1
import kotlin.math.pow

class Screen2 : BaseFragment(R.layout.screen_2_pue) {

    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()
    private val spinValues3 = arrayListOf<ArrayList<String>>()
    private val spinValues4 = arrayListOf<ArrayList<String>>()
    private val spinValues5 = arrayListOf<ArrayList<String>>()
    private val spinValues6 = arrayListOf<ArrayList<String>>()
    private var koef1: Double = 10.0.pow(-12.0)
    private var koef2: Double = 10.0.pow(-12.0)
    private var koef3: Int = 1
    private var koef4: Double = 10.0.pow(-6.0)
    private var k: Double = 4.3
    var inputI = 0F
    var S: Double = 0.0
    var resistance2: Double = 0.0
    override fun initialize() {

        initViews()
        initClicks()
        initSpinners()
    }

    private fun initViews() {

        header.text = "Сечение проводника"
    }

    private fun clear() {
        input_screen1_3.text?.clear()
        input_screen1_4.text?.clear()
        input_screen1_6.text?.clear()
        input_screen1_7.text?.clear()
        input_screen1_9.text?.clear()
        result_text1.text = ""
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }
        result_btn_screen1.enableDisable(false)
        clear_btn_screen1.setOnClickListener {
            clear()
        }

       /* result_btn_screen1.setOnClickListener {
            val dialog = context?.let { it1 ->
                PueReport1(
                    it1,
                    "%.2f".format(S),
                    spinner_screen1_1.selectedItem.toString(),
                    getMM(spinner_screen1_5).toString() + " м²",
                    getVeinValue(spinner_screen1_3).toString() + " м",
                    getTemperature(spinner_screen1_4).toString() + " °C",
                    getTemperature(spinner_screen1_4).toString() + " °C",
                    getTemperature(spinner_screen1_4).toString() + " °C"
                )
            }
            dialog!!.show()
        }*/
        input_screen1_1.setText("3")
        input_screen1_2.setText("0.85")
        input_screen1_3.setText("")
        input_screen1_4.setText("")
        input_screen1_5.setText("4")
        input_screen1_8.setText("1")

        TextChanged(input_screen1_1)
        TextChanged(input_screen1_2)
        TextChanged(input_screen1_3)
        TextChanged(input_screen1_4)
        TextChanged(input_screen1_5)
        TextChanged(input_screen1_6)

        spinnerSelectedListener(spinner_screen1_1)
        spinnerSelectedListener(spinner_screen1_2)
        spinnerSelectedListener(spinner_screen1_3)
        spinnerSelectedListener(spinner_screen1_4)
        spinnerSelectedListener(spinner_screen1_5)
        spinnerSelectedListener(spinner_screen1_6)

        if (radio_btn_screen3_1.isChecked) {
            input_screen1_7.enableDisable(false)
        }
        radiobt_screen3_2.setOnClickListener {
            input_screen1_7.enableDisable(true)
            input_screen1_6.enableDisable(false)
            radio_btn_screen3_1.isChecked = false
        }

        radio_btn_screen3_1.setOnClickListener {

            input_screen1_7.enableDisable(false)
            input_screen1_6.enableDisable(true)

            radiobt_screen3_2.isChecked = false
        }

        checkBox.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked)
            {liner_1_rezerv_screen.visibility=View.VISIBLE
                input_screen1_9.visibility=View.VISIBLE
            }
            else {
                input_screen1_9.visibility=View.GONE
                liner_1_rezerv_screen.visibility=View.GONE
            }
            calculation()
        }

        result_btn_screen1.setOnClickListener {

            if (radio_btn_screen3_1.isChecked) {
                if (input_screen1_1.text.toString() != "" && input_screen1_2.text.toString() != "" && input_screen1_3.text.toString() != "") {
                    var k = false
                    if (radio_btn_screen3_1.isChecked) k = false
                    if (radiobt_screen3_2.isChecked) k = true

                    var s = ""
                    if (input_screen1_2.text.toString().toFloat() > 1) s = " %"

                    var t = input_screen1_4.text.toString() + " Ампер"
                    if (radiobt_screen3_2.isChecked) t = "$inputI Ампер"

                } else Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()

            }
            if (radiobt_screen3_2.isChecked) {

                if (input_screen1_1.text.toString() != "" && input_screen1_2.text.toString() != "" && input_screen1_3.text.toString() != "" && input_screen1_4.text.toString() != "") {
                    var k = false
                    if (radio_btn_screen3_1.isChecked) k = false
                    if (radiobt_screen3_2.isChecked) k = true
                    var s = ""
                    if (input_screen1_2.text.toString().toFloat() > 1) s = " %"

                    var t = input_screen1_1.text.toString() + " Ампер"
                    if (radiobt_screen3_2.isChecked) t = "$inputI Ампер"

                } else Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initSpinners() {
        spinValues1.add(arrayListOf("Медь", "Алюминий"))

        val adapter1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues1[0])
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_screen1_1.adapter = adapter1
        //Allowable ampacity   /  Допустимая ампутация    28.06 A

        spinValues2.add(
            arrayListOf(
                "Трехфазный",
                "Трехфазный + N",
                "Двухфазный",
                "Двухфазный + N",
                "Однофазный"
            )
        )
        val adapter2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues2[0])
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_screen1_2.adapter = adapter2

        //material p
        spinValues3.add(
            arrayListOf(
                "Одножильных провод открыто",
                "Один двух жильных закрыто",
                "Один трехжильных закрыто",
                "Два одножильных закрыто",
                "Три одножильных закрыто",
                "Четыре одножильных закрыто",
                "Одножильный кабель в воздухе",
                "Двухжильный кабель в воздухе",
                "Трехжильный кабель в воздухе",
                "Двухжильный кабель в земле",
                "Трехжильный кабель в земле"
            )
        )
        val adapter3: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues3[0])
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_screen1_3.adapter = adapter3
        //Allowable ampacity   /  Допустимая ампутация    28.06 A

        spinValues4.add(arrayListOf(
            "auto",
            "-5 °C |41 °Ф",
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
        ))

        val adapter4: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues4[0])
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_screen1_4.adapter = adapter4
        //material p

        spinValues5.add(
            arrayListOf(
                  "м" , "ft"
            )
        )
        val adapter5: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues5[0])
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_screen1_5.adapter = adapter5
        // t
        spinValues6.add(
            arrayListOf(
                "%",
                "В"
            )
        )

        val adapter6: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues6[0])
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_screen1_6.adapter = adapter6
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
                calculation()
                if (input_screen1_1.text.toString() == "" || input_screen1_2.text.toString() == "" ||
                    input_screen1_2.text.toString() == "" || input_screen1_3.text.toString() == "" ||
                    input_screen1_4.text.toString() == "" || input_screen1_5.text.toString() == "" ||
                   input_screen1_6.text.toString() == ""  /*|| input_screen1_7.text.toString() == "" ||
                    input_screen1_8.text.toString() == "" || input_screen1_9.text.toString() == ""*/
                ) {
                    result_text1.text = ""
                    result_btn_screen1.enableDisable(false)
                } else result_btn_screen1.enableDisable(true)
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
                calculation()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculation() {
        if (input_screen1_1.text.toString() != "" && input_screen1_2.text.toString() != "" && input_screen1_3.text.toString() != ""
            && input_screen1_4.text.toString() != "" && input_screen1_5.text.toString() != ""  && input_screen1_6.text.toString() != ""
        /*  && input_screen1_7.text.toString() != ""  && input_screen1_8.text.toString() != "" && input_screen1_9.text.toString() != ""*/) {
            //   material
            val materialData = getMaterialValue(spinner_screen1_1)
            //   phase
            val phaseData = getPhase(spinner_screen1_2)
            //   жильных
            val veinData = getVeinValue(spinner_screen1_3)
            //  Температура
            val tempData = getTemperature(spinner_screen1_4)
            //  длина
            val length = getMM(spinner_screen1_5)

            val U = input_screen1_4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            val I = input_screen1_6.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            val p = input_screen1_7.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
            val z = input_screen1_9.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }

            S =(getPhase(spinner_screen1_2).r20 * materialData.r20 * getMM(spinner_screen1_5) * I  * p * z * 100) /
                    (U * getPoteri(spinner_screen1_6, input_screen1_5))
            resistance2 = (getVeinValue(spinner_screen1_3) + getPhase(spinner_screen1_2).r20  + getTemperature(spinner_screen1_4) + materialData.r20)

            result_text1.text = "%.0f".format(S/ 1000) + " мм² |"
            result_text2.text = "%.0f".format(resistance2) + "°C"
        }
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
                mm =  1.0
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
data class MaterialData(var r20: Double)


