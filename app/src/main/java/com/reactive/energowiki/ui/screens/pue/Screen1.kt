package com.reactive.energowiki.ui.screens.pue

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.utils.extensions.enableDisable
import kotlinx.android.synthetic.main.bottomsheet_detail.close
import kotlinx.android.synthetic.main.bottomsheet_detail.header
import kotlinx.android.synthetic.main.screen_1_pue.*

class Screen1 : BaseFragment(R.layout.screen_1_pue) {

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
        screen_text1.text = ""
    }

    private fun initViews() {
        header.text = "Длительных ток"
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }
        result_btn_screen1.enableDisable(false)

        clear_btn_pue_screen1.setOnClickListener {
            clear()
        }

       /* result_btn_screen1.setOnClickListener {
            val dialog = context?.let { it1 ->
                PueReport1(
                    it1,
                    "%.5f".format(resistance) + " A",
                    spinner_screen1_1.selectedItem.toString(),
                    getMM(
                        spinner_screen1_4,
                        input_screen1_2
                    ).toString() + " м²",
                    getCountСon(
                        spinner_screen1_2,
                        input_screen1_1
                    ).toString() + " м",
                    getTemperature(
                        spinner_screen1_3, input_screen1_1
                    ).toString() + " °C"
                )
            }
            dialog!!.show()
        }*/

        input_screen1_1.setText("3")
        input_screen1_2.setText("1")

        TextChanged(input_screen1_1)
        TextChanged(input_screen1_2)

        spinnerSelectedListener(spinner_screen1_1)
        spinnerSelectedListener(spinner_screen1_2)
        spinnerSelectedListener(spinner_screen1_3)
        spinnerSelectedListener(spinner_screen1_4)

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
        spinner_screen1_1.adapter = adapter1
        //Allowable ampacity   /  Допустимая ампутация    28.06 A

        spinValues2.add(arrayListOf("Медь", "Алюминий"))

        val adapter2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues2[0])
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_screen1_2.adapter = adapter2
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
        spinner_screen1_3.adapter = adapter3
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
        spinner_screen1_4.adapter = adapter4
        //S
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
                if (input_screen1_1.text.toString() == "" || input_screen1_2.text.toString() == ""
                ) {
                    screen_text1.text = ""
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
        if (input_screen1_1.text.toString() != "" && input_screen1_2.text.toString() != "") {
            //   жильных  
            val veinData = getVeinValue(spinner_screen1_1)
            //   material
            val materialData = getMaterialValue(spinner_screen1_2)
            //  Температура
            val tempData = getTemperature(spinner_screen1_3)
            //  Сечение
            val mmData = getMM(spinner_screen1_4)

          // resistance = (veinData * ( materialData.ro20 * tempData) * mmData)

           resistance = (veinData + mmData ) *( materialData.ro20 * tempData)

            screen_text1.text = "%.0f".format(resistance) + "A"
        }
    }

    private fun getMaterialValue(spinner: Spinner): MaterialsData {
        var t: Double = 0.0

        when (spinner.selectedItemPosition) {
            0 -> { //"Медь"  Copper
                t = 1.78
            }
            1 -> { //"Алюминий",ok Alumin
                t = 2.9
            }
        }
        return MaterialsData(t)
    }
//  4   +
    private fun getVeinValue(spinner: Spinner): Double {
        var t: Double = 0.0

        when (spinner.selectedItemPosition) {
            0 -> { // Одножильных провод открыто
                t = 8.0
            }
            1 -> { //Один двух жильных закрыто
                t = 6.0
            }
            2 -> { //Один трехжильных закрыто
                t = 6.0
            }
            3 -> { //Два одножильных закрыто
                t = 7.0
            }
            4 -> { //Три одножильных закрыто
                t = 6.0
            }
            5 -> { //Четыре одножильных закрыто
                t = 5.0
            }
            6 -> { //Двухжильный кабель в воздухе
                t = 6.0
            }
            7 -> { //Трехжильный кабель в воздухе
                t = 6.0
            }
            8 -> { //Двухжильный кабель в земле
                t = 15.0
            }
            9 -> { //Трехжильный кабель в земле
                t = 11.0
            }

        }
        return t
    }

    private fun getTemperature(spinner: Spinner): Double {
        var temp: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> {
                temp = 1.14
            }
            1 -> {
                temp = 1.14
            }
            2 -> {
                temp = 1.11
            }
            3 -> {
                temp = 1.08
            }
            4 -> {
                temp = 1.04
            }
            5 -> {
                temp = 1.00
            }
            6 -> {
                temp = 0.96
            }
            7 -> {
                temp = 0.92
            }
            8 -> {
                temp = 0.88
            }
            9 -> {
                temp = 0.83
            }
            10 -> {
                temp = 0.78
            }
            11 -> {
                temp = 0.73
            }
            12 -> {
                temp = 0.68
            }
        }
        return temp
    }

    private fun getMM(spinner: Spinner): Double {
        var mm: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> {
                mm = 0.5
            }
            1 -> {
                mm =  0.75
            }
            2 -> {
                mm = 1.0
            }
            3 -> {
                mm = 1.2
            }
            4 -> {
                mm = 1.5
            }
            5 -> {
                mm = 2.0
            }
            6 -> {
                mm = 2.5
            }
            7 -> {
                mm = 3.0
            }
            8 -> {
                mm = 4.0
            }
            9 -> {
                mm = 5.0
            }
            10 -> {
                mm = 6  * 1.0e-6
            }
            11 -> {
                mm = 8  * 1.0e-6
            }
            12 -> {
                mm = 10  * 1.0e-6
            }
            13 -> {
                mm = 16  * 1.0e-6
            }
            14 -> {
                mm =  25  * 1.0e-6
            }
            15 -> {
                mm = 35  * 1.0e-6
            }
            16 -> {
                mm =  50  * 1.0e-6
            }
            17 -> {
                mm =  70  * 1.0e-6
            }
            18 -> {
                mm =   95  * 1.0e-6
            }
            19 -> {
                mm =  120  * 1.0e-6
            }
            20 -> {
                mm =  150  * 1.0e-6
            }
            21 -> {
                mm =  185  * 1.0e-6
            }
            22 -> {
                mm = 240  * 1.0e-6
            }
            23 -> {
                mm = 300  * 1.0e-6
            }
            24 -> {
                mm =  400  * 1.0e-6
            }
        }
        return mm
    }
}

data class MaterialsData(var ro20: Double) {
}


