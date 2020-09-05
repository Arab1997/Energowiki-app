package com.reactive.energowiki.ui.screens.calculator.capacity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.bottomsheet_detail.*
import kotlinx.android.synthetic.main.screen_cabel_1.*
import kotlinx.android.synthetic.main.screen_cabel_1.input1
import kotlinx.android.synthetic.main.screen_cabel_2.*
import kotlinx.android.synthetic.main.screen_capacity_1.*
import kotlinx.android.synthetic.main.screen_capacity_3.*
import kotlinx.android.synthetic.main.screen_engine_7.*
import kotlinx.android.synthetic.main.screen_engine_7.clearBtn
import kotlinx.android.synthetic.main.screen_engine_7.input2
import kotlinx.android.synthetic.main.screen_engine_7.input3
import kotlinx.android.synthetic.main.screen_engine_7.spinner1
import kotlin.math.PI
import kotlin.math.sqrt

class Capacity3Screen : BaseFragment(R.layout.screen_capacity_3) {
    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()

    var operatingC = 0
    var startingC = 0
    var finishingC = 0
    var minVoltage = 0

    override fun initialize() {
        initClicks()

        initSpinners()

        TextChanged(input_capacity_screen3_1)
        TextChanged(input_capacity_screen3_2)
        TextChanged(input_capacity_screen3_3)
        TextChanged(input_capacity_screen3_4)
        TextChanged(input_capacity_screen3_5)
        TextChanged(input_capacity_screen3_6)
        TextChanged(input_capacity_screen3_7)

    }

    private fun initSpinners() {


        spinValues1.add(
            arrayListOf(
                "Треугольник",
                "Звезда",
                "Неполная звезда (а)",
                "Неполная звезда (б)"
            )
        )
        val adapter1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues1[0])
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_capacity_screen3_1.adapter = adapter1


        spinValues2.add(arrayListOf("отн.ед", "%"))
        val adapter2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues2[0])
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_capacity_screen3_2.adapter = adapter2

        spinner_capacity_screen3_1.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {

                    if (input_capacity_screen3_1.text.toString() != ""){
                        if (input_capacity_screen3_2.text.toString() != ""){
                            if (input_capacity_screen3_6.text.toString() != ""){
                                calculateInputs()
                            }
                        }
                    }


                    text_1_screen_capacity.setText(
                        when (pos) {
                            0 -> "Треугольник"
                            1 -> "Звезда"
                            2 -> "Неполная звезда (а)"
                            3 -> "Неполная звезда (б)"
                            else -> "Треугольник"

                        }
                    )
                }
            }


        spinner_capacity_screen3_2.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    val h = input_capacity_screen3_5.text.toString()
                    if (h != "") {
                        if (pos == 1) {
                            input_capacity_screen3_5.setText((h.toFloat() * 100).toString())
                        }

                    }
                    //  initCalculation()
                }
            }
    }

    private fun initClicks() {
        close.setOnClickListener { finishFragment() }

        clear_bt_capacity_screen3.setOnClickListener {
            input_capacity_screen3_1.text?.clear()
            input_capacity_screen3_3.text?.clear()
            input_capacity_screen3_6.text?.clear()
            input_capacity_screen3_7.text?.clear()
        }

        input_capacity_screen3_2.setText("50")
        input_capacity_screen3_4.setText("0.85")
        input_capacity_screen3_5.setText("0.9")

        //  radiobt_capacity_screen3_1.isChecked == true


        if (radiobt_capacity_screen3_1.isChecked) {


            title_capacity_screen3.visibility = View.GONE
            liner_1_capacity_screen3.visibility = View.GONE
            liner_2_capacity_screen3.visibility = View.GONE
            liner_3_capacity_screen3.visibility = View.GONE
        }

        radiobt_capacity_screen3_2.setOnClickListener {

            //  input_capacity_screen3_6.setEnabled(false)
            // input_capacity_screen3_7.setEnabled(true)

            radiobt_capacity_screen3_1.setChecked(false)
            title_capacity_screen3.visibility = View.VISIBLE
            liner_1_capacity_screen3.visibility = View.VISIBLE
            liner_2_capacity_screen3.visibility = View.VISIBLE
            liner_3_capacity_screen3.visibility = View.VISIBLE
        }

        radiobt_capacity_screen3_1.setOnClickListener {
            //input_capacity_screen3_7.
            // input_capacity_screen3_6.setEnabled(true)

            radiobt_capacity_screen3_2.setChecked(false)
            title_capacity_screen3.visibility = View.GONE
            liner_1_capacity_screen3.visibility = View.GONE
            liner_2_capacity_screen3.visibility = View.GONE
            liner_3_capacity_screen3.visibility = View.GONE
        }
    }


    fun TextChanged(editText: EditText){

        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (input_capacity_screen3_1.text.toString() != ""){
                    if (input_capacity_screen3_2.text.toString() != ""){
                        if (input_capacity_screen3_6.text.toString() != ""){
                            calculateInputs()
                        }
                    }
                }
            }
        })
    }

    fun calculateInputs() {


            var input_text_volt = input_capacity_screen3_1.text.toString().toInt()
            var input_text_hz = input_capacity_screen3_2.text.toString().toInt()
            //  var input_text_volt_three = input_capacity_screen3_3.text.toString().toFloat()
            //  var input_text_power_factor = input_capacity_screen3_4.text.toString().toFloat()
           // var input_text_power_efficeny = input_capacity_screen3_5.text.toString().toFloat()
            var input_text_power_amper = input_capacity_screen3_6.text.toString().toInt()
            // var input_text_motor_power = input_capacity_screen3_7.text.toString().toFloat()


            when (spinner_capacity_screen3_1.selectedItemPosition) {
                0 -> {

                    operatingC =
                        (3/(2 * (input_text_power_amper / (2 * input_text_hz * input_text_volt * PI))) * 100).toInt()
                    startingC = (operatingC * 2.5).toInt()
                    finishingC = operatingC * 3
                    minVoltage = (input_text_volt * 1.15).toInt()
                }
                1 -> {
                    operatingC =
                        (sqrt(3.0) / 2 * (input_text_power_amper / (2 * input_text_hz * input_text_volt * PI)) * 100).toInt()
                    startingC = (operatingC * 2.5).toInt()
                    finishingC = operatingC * 3
                    minVoltage = input_text_volt * 1.15.toInt()

                }

                2 -> {
                    operatingC =
                        (1 / 2 * (input_text_power_amper / (2 * input_text_hz * input_text_volt * PI)) * 100).toInt()
                    startingC = operatingC * 2.5.toInt()
                    finishingC = operatingC * 3
                    minVoltage = input_text_volt * 2.2.toInt()

                }

                3 -> {
                    operatingC =
                        (sqrt(3.0) / 2 * (input_text_power_amper / (2 * input_text_hz * input_text_volt * PI)) * 100).toInt()
                    startingC = (operatingC * 2.5).toInt()
                    finishingC = operatingC * 3
                    minVoltage = (input_text_volt * 2.2).toInt()

                }

            }

            operating_value.setText(operatingC.toString()+" µF")
            starting_value.setText(startingC.toString() + "-" + finishingC + " µF")
            min_voltage_value.setText(minVoltage.toString()+ " V")

        }





}