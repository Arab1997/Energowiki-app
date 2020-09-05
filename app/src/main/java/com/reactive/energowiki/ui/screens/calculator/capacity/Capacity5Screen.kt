package com.reactive.energowiki.ui.screens.calculator.capacity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.bottomsheet_detail.*
import kotlinx.android.synthetic.main.screen_capacity_4.*
import kotlinx.android.synthetic.main.screen_capacity_5.*

class Capacity5Screen : BaseFragment(R.layout.screen_capacity_5) {

    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()
    var birlik: Double = 0.0
    var C: Double = 0.0
    var U: Double = 0.0

    override fun initialize() {
        initClicks()
        initSpinners()

    }

    private fun initSpinners() {
        spinValues1.add(
            arrayListOf(
                "пВ",
                "нВ",
                "мкВ",
                "мВ",
                "В",
                "кВ",
                "МВ",
                "ГВ"
            )
        )

        val adapter1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues1[0])
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_capacity_screen5_1.adapter = adapter1
        spinner_capacity_screen5_1.setSelection(4)

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
        spinner_capacity_screen5_2.adapter = adapter2
        spinner_capacity_screen5_2.setSelection(4)


    }

    private fun initClicks() {
        close.setOnClickListener { finishFragment() }

        clear_bt_capacity_screen5.setOnClickListener {
            input_capacity_screen5_1.text?.clear()
            input_capacity_screen5_2.text?.clear()
        }

        TextChanged(input_capacity_screen5_1)
        TextChanged(input_capacity_screen5_2)

        spinnerSelectedListener(spinner_capacity_screen5_2)
        spinnerSelectedListener(spinner_capacity_screen5_1)
    }


    fun getBirlik(spinner: Spinner): Double {
        when (spinner.selectedItemPosition) {
            0 -> birlik = 1.0e-12.toDouble()
            1 -> birlik = 1.0e-9.toDouble()
            2 -> birlik = 1.0e-6.toDouble()
            3 -> birlik = 1.0e-3.toDouble()
            4 -> birlik = 1.toDouble()
            5 -> birlik = 1.0e3.toDouble()
            6 -> birlik = 1.0e6.toDouble()
            7 -> birlik = 1.0e9.toDouble()
            else -> 1.toDouble()
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
        capacity_screen5_result1.text = ""
        capacity_screen5_result2.text = ""

        if (input_capacity_screen5_1.text.toString() != "" && input_capacity_screen5_2.text.toString() != "") {
            U = input_capacity_screen5_1.text.toString().toDouble()
            C = input_capacity_screen5_2.text.toString().toDouble()

            var Q =
                C * getBirlik(spinner_capacity_screen5_2) * U * getBirlik(spinner_capacity_screen5_1)
            var E = (C * getBirlik(spinner_capacity_screen5_2) * U * getBirlik(
                spinner_capacity_screen5_1
            ) * U * getBirlik(spinner_capacity_screen5_1)) / 2

            if (Q >= 0) {
                capacity_screen5_result1.text = Q.toString() + " Culon"
            } else {
                Toast.makeText(context, "ошибка, Q<0", Toast.LENGTH_SHORT).show()
            }

            if (E >= 0) {

                capacity_screen5_result2.text = E.toString() + " Joul"
            } else {
                Toast.makeText(context, "ошибка, Q<0", Toast.LENGTH_SHORT).show()
            }
        }
    }

}