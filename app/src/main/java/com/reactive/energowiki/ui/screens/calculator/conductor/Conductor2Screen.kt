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
import com.reactive.energowiki.ui.dialogs.conductorReport.ConductorReport2
import com.reactive.energowiki.utils.extensions.addItems
import com.reactive.energowiki.utils.extensions.enableDisable
import kotlinx.android.synthetic.main.bottomsheet_detail.*
import kotlinx.android.synthetic.main.screen_conductor_2.*

class Conductor2Screen: BaseFragment(R.layout.screen_conductor_2){

    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()
    private val spinValues3 = arrayListOf<ArrayList<String>>()
    private val spinValues4 = arrayListOf<ArrayList<String>>()

    var lengthL: Double = 0.0

    override fun initialize() {


        initClicks()
        initSpinners()

    }

    private fun clear(){
        input_conductor_screen2_1.text?.clear()
        input_conductor_screen2_2.text?.clear()
        input_conductor_screen2_3.text?.clear()
        conductor_screen2_text1.setText("")
    }
    private fun initClicks() {

        close.setOnClickListener { finishFragment() }
        result_bt_conductor_screen2.enableDisable(false)

        clear_bt_conductor_screen2.setOnClickListener {
            clear()
        }


        result_bt_conductor_screen2.setOnClickListener {
            val dialog = context?.let { it1 ->
                ConductorReport2(
                    it1,
                    "%.4f".format(lengthL)+ " м | "+ "%.4f".format(lengthL/0.3084) + " ft",
                    spinner_conductor_screen2_1.selectedItem.toString(),
                    getMM(spinner_conductor_screen2_4,input_conductor_screen2_3).toString()+ " м²",
                    getResistanceOm(
                        spinner_conductor_screen2_2,
                        input_conductor_screen2_1
                    ).toString() +" Oм" ,
                    getCelciumTemperature(
                        spinner_conductor_screen2_3,
                        input_conductor_screen2_2
                    ).toString()+" °C"
                )
            }
            dialog!!.show()
        }


        TextChanged(input_conductor_screen2_1)
        TextChanged(input_conductor_screen2_2)
        TextChanged(input_conductor_screen2_3)

        spinnerSelectedListener(spinner_conductor_screen2_1)
        spinnerSelectedListener(spinner_conductor_screen2_2)
        spinnerSelectedListener(spinner_conductor_screen2_3)
        spinnerSelectedListener(spinner_conductor_screen2_4)


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

        spinner_conductor_screen2_1.addItems(requireContext(), spinValues1[0])

        //spinner 2 set

        spinValues2.add(
            arrayListOf(
                "пОм",
                "нОм",
                "мкОм",
                "мОм",
                "Ом",
                "кОм",
                "МОм",
                "ГОм"
            )
        )

        spinner_conductor_screen2_2.addItems(requireContext(), spinValues2[0])
        spinner_conductor_screen2_2.setSelection(4)

        //spinner 3 set

        spinValues3.add(
            arrayListOf(
                "°C",
                "F"
            )
        )

        spinner_conductor_screen2_3.addItems(requireContext(), spinValues3[0])

        //spinner 3 set

        spinValues4.add(
            arrayListOf(
                "мм²",
                "kcmil"
            )
        )

        spinner_conductor_screen2_4.addItems(requireContext(), spinValues4[0])

    }


    private fun calculation() {

        if (input_conductor_screen2_1.text.toString() != "" && input_conductor_screen2_2.text.toString() != "" && input_conductor_screen2_3.text.toString() != "") {
            var materialData = getMaterialValue(spinner_conductor_screen2_1)
            lengthL = (getResistanceOm(
                spinner_conductor_screen2_2,
                input_conductor_screen2_1
            ) * (getMM(spinner_conductor_screen2_4,input_conductor_screen2_3))
                    )/(materialData.ro20 * (1 + materialData.alfa * (getCelciumTemperature(
                spinner_conductor_screen2_3,
                input_conductor_screen2_2
            ) - 20)))

            conductor_screen2_text1.setText("%.4f".format(lengthL)+ " м | "+ "%.4f".format(lengthL/0.3084) + " ft")
        }




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
                if (input_conductor_screen2_1.text.toString() == "" || input_conductor_screen2_2.text.toString() == "" || input_conductor_screen2_3.text.toString() == "") {
                    conductor_screen2_text1.setText("")
                    result_bt_conductor_screen2.enableDisable(false)
                } else result_bt_conductor_screen2.enableDisable(true)
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

    private fun getResistanceOm(spinner: Spinner, editText: EditText): Double {
        var length: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> {
                length = editText.text.toString().toDouble()*1.0e-12
            }
            1 -> {
                length = editText.text.toString().toDouble() * 1.0e-9
            }
            2 -> {
                length = editText.text.toString().toDouble() * 1.0e-6
            }
            3 -> {
                length = editText.text.toString().toDouble()*1.0e-3
            }
            4 -> {
                length = editText.text.toString().toDouble()
            }
            5 -> {
                length = editText.text.toString().toDouble() * 1.0e3
            }
            6 -> {
                length = editText.text.toString().toDouble()*1.0e6
            }
            7 -> {
                length = editText.text.toString().toDouble()*1.0e9
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
}