package com.reactive.energowiki.ui.screens.pue

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.dialogs.pueReports.PueReport1
import com.reactive.energowiki.utils.extensions.enableDisable
import kotlinx.android.synthetic.main.bottomsheet_detail.close
import kotlinx.android.synthetic.main.bottomsheet_detail.header
import kotlinx.android.synthetic.main.screen1.clear_bt_conductor_screen1
import kotlinx.android.synthetic.main.screen1.conductor_screen1_text1
import kotlinx.android.synthetic.main.screen1.input_conductor_screen1_1
import kotlinx.android.synthetic.main.screen1.input_conductor_screen1_2
import kotlinx.android.synthetic.main.screen1.result_bt_conductor_screen1
import kotlinx.android.synthetic.main.screen1.spinner_conductor_screen1_1
import kotlinx.android.synthetic.main.screen1.spinner_conductor_screen1_2
import kotlinx.android.synthetic.main.screen1.spinner_conductor_screen1_3
import kotlinx.android.synthetic.main.screen1.spinner_conductor_screen1_4

class Screen1 : BaseFragment(R.layout.screen1) {

    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()
    private val spinValues3 = arrayListOf<ArrayList<String>>()
    private val spinValues4 = arrayListOf<ArrayList<String>>()

    var resistance: Double = 0.0
    override fun initialize() {

        initViews()
        initClicks()
        initSpinners()

    }

    private fun clear() {
        input_conductor_screen1_1.text?.clear()
        input_conductor_screen1_2.text?.clear()
        conductor_screen1_text1.text = ""

    }

    private fun initViews() {
        header.text = "Длительных ток"
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }
        result_bt_conductor_screen1.enableDisable(false)

        clear_bt_conductor_screen1.setOnClickListener {
            clear()
        }

        result_bt_conductor_screen1.setOnClickListener {
            val dialog = context?.let { it1 ->
                PueReport1(
                    it1,
                    "%.5f".format(resistance) + " A",
                    spinner_conductor_screen1_1.selectedItem.toString(),
                    getMM(
                        spinner_conductor_screen1_4,
                        input_conductor_screen1_2
                    ).toString() + " м²",
                    getCountСon(
                        spinner_conductor_screen1_2,
                        input_conductor_screen1_1
                    ).toString() + " м",
                    getTemperature(
                        spinner_conductor_screen1_3,
                        input_conductor_screen1_1
                    ).toString() + " °C"
                )
            }
            dialog!!.show()
        }

        input_conductor_screen1_1.setText("3")
        input_conductor_screen1_2.setText("1")

        TextChanged(input_conductor_screen1_1)
        TextChanged(input_conductor_screen1_2)

        spinnerSelectedListener(spinner_conductor_screen1_1)
        spinnerSelectedListener(spinner_conductor_screen1_2)
        spinnerSelectedListener(spinner_conductor_screen1_3)
        spinnerSelectedListener(spinner_conductor_screen1_4)

    }

    private fun initSpinners() {

        spinValues1.add(
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

        val adapter1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues1[0])
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_conductor_screen1_1.adapter = adapter1
        //Allowable ampacity   /  Допустимая ампутация    28.06 A

        spinValues2.add(arrayListOf("Медь", "Алюминий"))

        val adapter2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues2[0])
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_conductor_screen1_2.adapter = adapter2
        //material p

        spinValues3.add(
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

        val adapter3: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues3[0])
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_conductor_screen1_3.adapter = adapter3
        // t

        spinValues4.add(
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

        val adapter4: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues4[0])
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_conductor_screen1_4.adapter = adapter4
        //S
    }

    private fun calculation() {
        if (input_conductor_screen1_1.text.toString() != "" && input_conductor_screen1_2.text.toString() != "") {
            //   жильных  
            var veinData = getVeinValue(spinner_conductor_screen1_1)
            //   material  
            var materialData = getMaterialValue(spinner_conductor_screen1_2)

            resistance =
                (getCountСon(spinner_conductor_screen1_2, input_conductor_screen1_1)
                        * materialData.ro20 * (getTemperature(
                    spinner_conductor_screen1_3,
                    input_conductor_screen1_2
                ) - 20)) / (getMM(spinner_conductor_screen1_4, input_conductor_screen1_1))

            conductor_screen1_text1.text = "%.4f".format(resistance) + "A"
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
                calculation()
                if (input_conductor_screen1_1.text.toString() == "" || input_conductor_screen1_2.text.toString() == ""
                ) {
                    conductor_screen1_text1.text = ""
                    result_bt_conductor_screen1.enableDisable(false)
                } else result_bt_conductor_screen1.enableDisable(true)
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

    private fun getMaterialValue(spinner: Spinner): MaterialData {
        var t: Double = 0.0
        var alfa: Double = 0.0

        when (spinner.selectedItemPosition) {
            0 -> { //"Медь"  Copper
                t = 1.78 * 1.0e-8
            }
            1 -> { //"Алюминий",ok Alumin
                t = 2.9 * 1.0e-8
            }
        }

        return MaterialData(t)
    }

    private fun getVeinValue(spinner: Spinner): MaterialData {
        var t: Double = 0.0
        var alfa: Double = 0.0

        when (spinner.selectedItemPosition) {
            0 -> { // Одножильных провод открыто
                t = 1.78 * 1.0e-8
            }
            1 -> { //Один двух жильных закрыто
                t = 2.9 * 1.0e-8
            }
            2 -> { //Один трехжильных закрыто
                t = 2.9 * 1.0e-8
            }
            3 -> { //Два одножильных закрыто
                t = 2.9 * 1.0e-8
            }
            4 -> { //Три одножильных закрыто
                t = 2.9 * 1.0e-8
            }
            5 -> { //Четыре одножильных закрыто
                t = 2.9 * 1.0e-8
            }
            6 -> { //Двухжильный кабель в воздухе
                t = 2.9 * 1.0e-8
            }
            7 -> { //Трехжильный кабель в воздухе
                t = 2.9 * 1.0e-8
            }
            8 -> { //Двухжильный кабель в земле
                t = 2.9 * 1.0e-8
            }
            9 -> { //Трехжильный кабель в земле
                t = 2.9 * 1.0e-8
            }
        }
        return MaterialData(t)
    }

    private fun getCountСon(spinner: Spinner, editText: EditText): Double {
        var conductor: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> {
                conductor = editText.text.toString().toDouble()
            }
            1 -> {
                conductor = editText.text.toString().toDouble() * 0.3048
            }
            2 -> {
                conductor = editText.text.toString().toDouble() * 1000
            }
            3 -> {
                conductor = editText.text.toString().toDouble() / 100
            }
            4 -> {
                conductor = editText.text.toString().toDouble() / 1000
            }
        }
        return conductor
    }

    private fun getTemperature(spinner: Spinner, editText: EditText): Double {
        var temp: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> {
                temp = editText.text.toString().toDouble()
            }
            1 -> {
                temp = ((editText.text.toString().toDouble() - 32) * 5) / 9
            }
        }
        return temp
    }

    private fun getMM(spinner: Spinner, editText: EditText): Double {
        var temp: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> {
                temp = editText.text.toString().toDouble() * 1.0e-6
            }
            /*   1 -> {
                   temp = ((editText.text.toString().toDouble()) * 0.5067 * 1.0e-6)
               }*/
        }
        return temp
    }
}

data class MaterialData(var ro20: Double)


