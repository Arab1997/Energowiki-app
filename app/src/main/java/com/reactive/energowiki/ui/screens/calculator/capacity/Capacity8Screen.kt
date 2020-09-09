package com.reactive.energowiki.ui.screens.calculator.capacity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.dialogs.capacityReport.CapacityReport8Dialog
import kotlinx.android.synthetic.main.bottomsheet_detail.*
import kotlinx.android.synthetic.main.screen_capacity_8.*
import kotlin.math.PI
import kotlin.math.log10
import kotlin.math.sqrt

class Capacity8Screen : BaseFragment(R.layout.screen_capacity_8) {
    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()


    var birlik = 0F
    var inputU = 0F
    var outputU = 0F
    var loadI = 0F
    var Fi = 0F

    var capacitience = 0F
    var resistance = 0F
    var power = 0F

    override fun initialize() {
        initClicks()

        initSpinners()
    }


    private fun initSpinners() {
        spinValues1.add(
            arrayListOf(
                "50 Гц",
                "60 Гц"
            )
        )

        val adapter1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues1[0])
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_capacity_screen8_1.adapter = adapter1

        spinValues2.add(
            arrayListOf(
                " А",
                " мА",
                " кВт",
                " Вт"
            )
        )

        val adapter2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues2[0])
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_capacity_screen8_2.adapter = adapter2


    }

    private fun initClicks() {
        close.setOnClickListener { finishFragment() }
        clear_bt_capacity_screen8.setOnClickListener {
            input_capacity_screen8_1.text?.clear()
            input_capacity_screen8_2.text?.clear()
            input_capacity_screen8_3.text?.clear()
        }
        TextChanged(input_capacity_screen8_1)
        TextChanged(input_capacity_screen8_2)
        TextChanged(input_capacity_screen8_3)

        spinnerSelectedListener(spinner_capacity_screen8_1)
        spinnerSelectedListener(spinner_capacity_screen8_2)

        result_bt_capacity_screen8.setOnClickListener {

            val dialog = context?.let { it1 ->
                CapacityReport8Dialog(
                    it1,
                    capacitience.toString() + " µF",
                    resistance.toString() + " Ω | \n$power W",
                    getGers(spinner_capacity_screen8_1).toString()+" Гц",
                    input_capacity_screen8_1.text.toString() + " Вольт",
                    input_capacity_screen8_2.text.toString() + " Вольт",
                    loadI.toString() + spinner_capacity_screen8_2.selectedItem.toString()

                )
            }
            dialog!!.show()

        }
    }


    fun getBirlik(spinner: Spinner): Float {
        when (spinner.selectedItemPosition) {
            0 -> birlik = 1.toFloat()
            1 -> birlik = 1.0e-3.toFloat()
            2 -> birlik = 1.0e3.toFloat()
            3 -> birlik = 1.0e1.toFloat()
            else -> 1.0.toLong()
        }
        return birlik
    }

    fun getGers(spinner: Spinner): Float =
        when (spinner.selectedItemPosition) {
            0 -> 50F
            1 -> 60F
            else -> 50F


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
        capacity_screen8_result1.text = ""
        capacity_screen8_result2.text = ""
        capacity_screen8_result3.text = ""

        var input1 = input_capacity_screen8_1.text.toString()
        var input2 = input_capacity_screen8_2.text.toString()
        var input3 = input_capacity_screen8_3.text.toString()

        if (input1 != "" && input2 != "" && input3 != "") {

            inputU = input_capacity_screen8_1.text.toString().toFloat()
            outputU = input_capacity_screen8_2.text.toString().toFloat()
            //spinners
            Fi = getGers(spinner_capacity_screen8_1)
            loadI = getBirlik(spinner_capacity_screen8_2) * input_capacity_screen8_3.text.toString()
                .toFloat()

            capacitience =
                (loadI / (2 * PI * Fi * sqrt(inputU * inputU - outputU * outputU))).toFloat()
            resistance = (19.6 / (capacitience * log10(0.34 * inputU * 1.1))).toFloat()
            power = ((3 * inputU * inputU * 1.21) / resistance).toFloat()

            capacitience = (capacitience * 1.0e6).toFloat()

            capacity_screen8_result1.setText(capacitience.toString() + " µF")
            capacity_screen8_result2.setText(resistance.toString() + " Ω")
            capacity_screen8_result3.setText(power.toString() + " W")


        }


    }

}