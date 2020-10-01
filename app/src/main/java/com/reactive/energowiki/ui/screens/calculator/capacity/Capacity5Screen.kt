package com.reactive.energowiki.ui.screens.calculator.capacity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.dialogs.capacityReport.CapacityReport5Dialog
import com.reactive.energowiki.utils.extensions.addItems
import kotlinx.android.synthetic.main.bottomsheet_detail.*
import kotlinx.android.synthetic.main.screen_capacity_5.*

class Capacity5Screen : BaseFragment(R.layout.screen_capacity_5) {

    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()
    var birlik = 0.0F
    var C = 0.0F
    var U = 0.0F
    var Q = 0f
    var En = 0f

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

        spinner_capacity_screen5_1.addItems(requireContext(), spinValues1[0])
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

        spinner_capacity_screen5_2.addItems(requireContext(), spinValues2[0])
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


        result_bt_capacity_screen5.setOnClickListener {

            val dialog = context?.let { it1 ->
                CapacityReport5Dialog(
                    it1,
                    "%.5f".format(Q) + " Кл",
                    "%.5f".format(En) + " Ж",
                    U.toString() + " В",
                    C.toString() + " Ф"
                )
            }
            dialog!!.show()

        }
    }


    fun getBirlik(spinner: Spinner): Float {
        when (spinner.selectedItemPosition) {
            0 -> birlik = 1.0e-12F
            1 -> birlik = 1.0e-9F
            2 -> birlik = 1.0e-6F
            3 -> birlik = 1.0e-3F
            4 -> birlik = 1.0F
            5 -> birlik = 1.0e3F
            6 -> birlik = 1.0e6F
            7 -> birlik = 1.0e9F
            else -> 1.0F
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
            U = input_capacity_screen5_1.text.toString().toFloat()
            C = input_capacity_screen5_2.text.toString().toFloat()

            Q =
                C * getBirlik(spinner_capacity_screen5_2) * U * getBirlik(spinner_capacity_screen5_1)
            En = (C * getBirlik(spinner_capacity_screen5_2) * U * getBirlik(
                spinner_capacity_screen5_1
            ) * U * getBirlik(spinner_capacity_screen5_1)) / 2

            if (Q >= 0) {
                capacity_screen5_result1.text = "%.5f".format(Q) + " Culon"
            } else {
                Toast.makeText(context, "ошибка, Q<0", Toast.LENGTH_SHORT).show()
            }

            if (En >= 0) {

                capacity_screen5_result2.text = "%.5f".format(En)+ "Joul"
            } else {
                Toast.makeText(context, "ошибка, Q<0", Toast.LENGTH_SHORT).show()
            }
        }
    }

}