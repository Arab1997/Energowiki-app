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
import com.reactive.energowiki.ui.dialogs.conductorReport.ConductorReport5
import com.reactive.energowiki.utils.extensions.enableDisable
import kotlinx.android.synthetic.main.bottomsheet_detail.*
import kotlinx.android.synthetic.main.screen_conductor_1.*
import kotlinx.android.synthetic.main.screen_conductor_5.*

class Conductor5Screen: BaseFragment(R.layout.screen_conductor_5){

    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()
    private val spinValues4 = arrayListOf<ArrayList<String>>()

    var sigma: Double = 0.0
    var ro: Double = 0.0

    override fun initialize() {


        initClicks()
        initSpinners()

    }

    private fun clear(){
        input_conductor_screen5_1.text?.clear()
        input_conductor_screen5_2.text?.clear()
        input_conductor_screen5_3.text?.clear()
        conductor_screen5_text1.setText("")
        conductor_screen5_text2.setText("")
    }
    private fun initClicks() {

        close.setOnClickListener { finishFragment() }
        result_bt_conductor_screen5.enableDisable(false)

        clear_bt_conductor_screen5.setOnClickListener {
            clear()
        }


        result_bt_conductor_screen5.setOnClickListener {
            val dialog = context?.let { it1 ->
                ConductorReport5(
                    it1,
                    "%.4f".format(ro)+ " Ω*мм²/м",
                    "%.4f".format(sigma)+ " См*м/мм²",
                    input_conductor_screen5_3.text.toString()+ " " +spinner_conductor_screen5_3.selectedItem.toString(),
                    (getResistanceOm(spinner_conductor_screen5_1,input_conductor_screen5_1)).toString()+ " Ω",
                    (getLengthL(
                        spinner_conductor_screen5_2,
                        input_conductor_screen5_2
                    )).toString() +" м"
                )
            }
            dialog!!.show()
        }


        TextChanged(input_conductor_screen5_1)
        TextChanged(input_conductor_screen5_2)
        TextChanged(input_conductor_screen5_3)

        spinnerSelectedListener(spinner_conductor_screen5_1)
        spinnerSelectedListener(spinner_conductor_screen5_2)
        spinnerSelectedListener(spinner_conductor_screen5_3)



    }

    private fun initSpinners() {

        spinValues1.add(
            arrayListOf(
                "м",
                "ft",
                "км",
                "см",
                "мм"


            )
        )

        val adapter1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues1[0])
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_conductor_screen5_2.adapter = adapter1


        //spinner 2 set

        spinValues2.add(
            arrayListOf(
                "пΩ",
                "нΩ",
                "мкΩ",
                "мΩ",
                "Ω",
                "кΩ",
                "МΩ",
                "ГΩ"
            )
        )

        val adapter2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues2[0])
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_conductor_screen5_1.adapter = adapter2
        spinner_conductor_screen5_1.setSelection(4)

        //spinner 3 set



        spinValues4.add(
            arrayListOf(
                "мм²"
            ,"kcmil"
            )
        )

        val adapter4: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues4[0])
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_conductor_screen5_3.adapter = adapter4

    }


    private fun calculation() {

        if (input_conductor_screen5_1.text.toString() != "" && input_conductor_screen5_2.text.toString() != "" && input_conductor_screen5_3.text.toString() != "") {

            ro = (getResistanceOm(
                spinner_conductor_screen5_1,
                input_conductor_screen5_1
            ))*(getMM(spinner_conductor_screen5_3,input_conductor_screen5_3))/getLengthL(spinner_conductor_screen5_2,input_conductor_screen5_2)
          sigma = 1/ro
            conductor_screen5_text1.setText("%.4f".format(ro)+ " Ω*мм²/м")
            conductor_screen5_text2.setText("%.4f".format(sigma)+ " См*м/мм²")
        }




    }

    private fun getMM(spinner: Spinner, editText: EditText): Double {
        var temp: Double = 0.0
        when (spinner.selectedItemPosition) {
            0 -> {
                temp = editText.text.toString().toDouble()
            }
            1 -> {
                temp = ((editText.text.toString().toDouble()) * 0.5067)
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
                if (input_conductor_screen5_1.text.toString() == "" || input_conductor_screen5_2.text.toString() == "" || input_conductor_screen5_3.text.toString() == "") {
                    conductor_screen5_text1.setText("")
                    conductor_screen5_text2.setText("")
                    result_bt_conductor_screen5.enableDisable(false)
                } else result_bt_conductor_screen5.enableDisable(true)
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
}