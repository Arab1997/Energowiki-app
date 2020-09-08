package com.reactive.energowiki.ui.screens.calculator.capacity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.bottomsheet_detail.*
import kotlinx.android.synthetic.main.screen_capacity_2.*
import kotlinx.android.synthetic.main.screen_capacity_5.*
import kotlinx.android.synthetic.main.screen_capacity_5.spinner_capacity_screen5_1

class Capacity2Screen : BaseFragment(R.layout.screen_capacity_2) {

    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()

    var birlik = 0F
    var Xc =  0F
    var Fi = 0F
    var C=  0F

    override fun initialize() {
        initClicks()
        initSpinners()

    }

    private fun initSpinners() {
        spinValues1.add(
            arrayListOf(
                "пГц",
                "нГц",
                "мкГц",
                "мГц",
                "Гц",
                "кГц",
                "МГц",
                "ГГц"
            )
        )

        val adapter1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues1[0])
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_capacity_screen2_1.adapter = adapter1
        spinner_capacity_screen2_1.setSelection(4)

        //Third spinner set up
        spinValues2.add(
            arrayListOf(
                "пФ",
                "нФ",
                "мкФ",
                "мФ",
                "Ф",
                "кФ",
                "МФ",
                "ГФ"
            )
        )

        val adapter2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues2[0])
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_capacity_screen2_2.adapter = adapter2
        spinner_capacity_screen2_2.setSelection(4)


    }

    private fun initClicks() {
        close.setOnClickListener { finishFragment() }

        clear_bt_capacity_screen2.setOnClickListener {
            input_capacity_screen2_1.text?.clear()
            input_capacity_screen2_2.text?.clear()
        }

        TextChanged(input_capacity_screen2_1)
        TextChanged(input_capacity_screen2_2)


        spinnerSelectedListener(spinner_capacity_screen2_2)
        spinnerSelectedListener(spinner_capacity_screen2_1)
    }


    fun getBirlik(spinner: Spinner): Float {
        when (spinner.selectedItemPosition) {
            0 -> birlik = 1.0e-12.toFloat()
            1 -> birlik = 1.0e-9.toFloat()
            2 -> birlik = 1.0e-6.toFloat()
            3 -> birlik = 1.0e-3.toFloat()
            4 -> birlik = 1.0.toFloat()
            5 -> birlik = 1.0e3.toFloat()
            6 -> birlik = 1.0e6.toFloat()
            7 -> birlik = 1.0e9.toFloat()
            else -> 1.0.toLong()
        }
        return birlik
    }


    fun TextChanged(editText: EditText) {

        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                calculation()
            }
        })
    }

    fun spinnerSelectedListener(spinner: Spinner) {

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

    fun calculation() {
        capacity_screen2_result1.text = ""

        if (input_capacity_screen2_1.text.toString() != "" && input_capacity_screen2_2.text.toString() != "") {
            Fi = input_capacity_screen2_1.text.toString().toFloat()
            C = input_capacity_screen2_2.text.toString().toFloat()

            Xc = (1/(2*Math.PI*C*Fi* getBirlik(spinner_capacity_screen2_1) * getBirlik(spinner_capacity_screen2_2))).toFloat()

            if (Xc >= 0) {
                capacity_screen2_result1.text = Xc.toString() + " Om"
            } else {
                Toast.makeText(context, "ошибка, Xc<0", Toast.LENGTH_SHORT).show()
            }
        }
    }

}