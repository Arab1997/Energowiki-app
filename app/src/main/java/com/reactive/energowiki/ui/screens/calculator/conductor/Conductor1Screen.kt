package com.reactive.energowiki.ui.screens.calculator.conductor

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.dialogs.conductorReport.ConductorReport1
import com.reactive.energowiki.utils.extensions.addItems
import com.reactive.energowiki.utils.extensions.enableDisable
import kotlinx.android.synthetic.main.bottomsheet_detail.*
import kotlinx.android.synthetic.main.screen_conductor_1.*

class Conductor1Screen : BaseFragment(R.layout.screen_conductor_1) {

    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()
    private val spinValues3 = arrayListOf<ArrayList<String>>()
    private val spinValues4 = arrayListOf<ArrayList<String>>()

    var resistance: Double = 0.0
    override fun initialize() {


        initClicks()
        initSpinners()

    }

    private fun clear(){
        input_screen1_1.text?.clear()
        input_screen1_2.text?.clear()
        input_screen1_3.text?.clear()
        conductor_text1.setText("")
    }
    private fun initClicks() {

        close.setOnClickListener { finishFragment() }
        result_btn_screen1.enableDisable(false)

        clear_bt_conductor_screen1.setOnClickListener {
        clear()
        }


        result_btn_screen1.setOnClickListener {
            val dialog = context?.let { it1 ->
                ConductorReport1(
                    it1,
                    "%.5f".format(resistance)+ " Ω",
                    spinner_screen1_1.selectedItem.toString(),
                    getMM(spinner_screen1_4,input_screen1_3).toString()+ " м²",
                    getLengthL(
                        spinner_screen1_2,
                        input_screen1_1
                    ).toString()+" м" ,
                    getCelciumTemperature(
                        spinner_screen1_3,
                        input_screen1_2
                    ).toString()+" °C"
                )
            }
            dialog!!.show()
        }


        TextChanged(input_screen1_1)
        TextChanged(input_screen1_2)
        TextChanged(input_screen1_3)

        spinnerSelectedListener(spinner_screen1_1)
        spinnerSelectedListener(spinner_screen1_2)
        spinnerSelectedListener(spinner_screen1_3)
        spinnerSelectedListener(spinner_screen1_4)


    }

    private fun initSpinners() {

        //spinner 1
        spinValues1.add(
            arrayListOf(
                "Медь",
                "Алюминий",
                "Никелин",
                "Вольфрам",
                "Серебро",
                "Железо",
                "Сталь",
                "Константан",
                "Нихром",
                "Латунь",
                "Золото",
                "Платина",
                "Фехраль",
                "Манганин",
                "Цинк",
                "Никель"


            )
        )

        spinner_screen1_1.addItems(requireContext(), spinValues1[0])

        //spinner 2 set

        spinValues2.add(
            arrayListOf(
                "м",
                "ft",
                "км",
                "см",
                "мм"


            )
        )

        spinner_screen1_2.addItems(requireContext(), spinValues2[0])

        //spinner 3 set

        spinValues3.add(
            arrayListOf(
                "°C",
                "F"
            )
        )

        spinner_screen1_3.addItems(requireContext(), spinValues3[0])

        //spinner 3 set

        spinValues4.add(
            arrayListOf(
                "мм²",
                "kcmil"
            )
        )

        spinner_screen1_4.addItems(requireContext(), spinValues4[0])

    }


    private fun calculation() {

        if (input_screen1_1.text.toString() != "" && input_screen1_2.text.toString() != "" && input_screen1_3.text.toString() != "") {
            var materialData = getMaterialValue(spinner_screen1_1)
            resistance = (getLengthL(
                spinner_screen1_2,
                input_screen1_1
            ) * materialData.ro20 * (1 + materialData.alfa * (getCelciumTemperature(
                spinner_screen1_3,
                input_screen1_2
            ) - 20))) / (getMM(spinner_screen1_4,input_screen1_3))


         conductor_text1.setText("%.5f".format(resistance)+ " Ω")
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
                if (input_screen1_1.text.toString() == "" || input_screen1_2.text.toString() == "" || input_screen1_3.text.toString() == "") {
                    conductor_text1.setText("")
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

    private fun getMaterialValue(spinner: Spinner): MaterialData {
        var t: Double = 0.0
        var alfa: Double = 0.0

        when (spinner.selectedItemPosition) {
            0 -> { //"Медь"  Copper
                t = 1.78 * 1.0e-8
                alfa = 0.005700
            }
            1 -> { //"Алюминий",ok Alumin
                t = 2.9 * 1.0e-8
                alfa = 0.00348
            }
            2 -> { //"Никелин",ok Nickeline
                t = 50 * 1.0e-8
                alfa = 0.005866
            }
            3 -> { //"Вольфрам",ok Tungsten
                t = 5.65 * 1.0e-8
                alfa = 0.004403
            }
            4 -> {// "Серебро",ok Silver
                t = 1.65 * 1.0e-8
                alfa = 0.00625
            }
            5 -> { //"Железо",ok Iron
                t = 130 * 1.0e-8
                alfa = 0.00075
            }
            6 -> { //"Сталь",ok Steel
                t = 1.6 * 1.0e-7
                alfa = 0.003
            }
            7 -> {// "Константан",ok Constantan
                t = 50 * 1.0e-7
                alfa = 0.0002
            }
            8 -> {//"Нихром",ok Nichrome
                t = 1.05 * 1.0e-8
                alfa = 0.00017
            }
            9 -> {// "Латунь", Brass
                t = 0.75 * 1.0e-7
                alfa = 0.001335
            }
            10 -> {//"Золото",ok  Gold
                t = 2.44 * 1.0e-8
                alfa = 0.00416
            }
            11 -> {//"Платина",ok  Platinum
                t = 10 * 1.0e-7
                alfa =
                    0.0010
            }
            12 -> { //"Фехраль",ok  Fechral
                t = 1.39 * 1.0e-6
                alfa = 1.0e-4
            }
            13 -> { //"Манганин",ok Manganin
                t = 0.47 * 1.0e-6
                alfa = 0.00005
            }
            14 -> {//"Цинк",ok Zinc
                t = 5.90 * 1.0e-8
                alfa = 0.00169
            }
            15 -> { //"Никель"ok///Nickel
                t = 7 * 1.0e-8
                alfa = 0.00143
            }
        }

        return MaterialData(t, alfa)
    }

    private fun getLengthL(spinner: Spinner, editText: EditText): Double {
        var length: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> {
                length = editText.text.toString().toDouble()
            }
            1 -> {
                length = editText.text.toString().toDouble() * 0.3048
            }
            2 -> {
                length = editText.text.toString().toDouble() * 1000
            }
            3 -> {
                length = editText.text.toString().toDouble() / 100
            }
            4 -> {
                length = editText.text.toString().toDouble() / 1000
            }
        }
        return length
    }

    private fun getCelciumTemperature(spinner: Spinner, editText: EditText): Double {
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
                temp = editText.text.toString().toDouble()* 1.0e-6
            }
            1 -> {
                temp = ((editText.text.toString().toDouble()) * 0.5067* 1.0e-6)
            }
        }
        return temp
    }
}

data class MaterialData(var ro20: Double, var alfa: Double)


