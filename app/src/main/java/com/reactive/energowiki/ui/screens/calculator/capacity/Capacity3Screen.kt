package com.reactive.energowiki.ui.screens.calculator.capacity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.dialogs.capacityReport.CapacityReport3Dialog
import com.reactive.energowiki.utils.extensions.enableDisable
import kotlinx.android.synthetic.main.bottomsheet_detail.*
import kotlinx.android.synthetic.main.screen2.*
import kotlinx.android.synthetic.main.screen_capacity_3.*
import kotlinx.android.synthetic.main.screen_capacity_3.liner_2_capacity_screen3
import kotlinx.android.synthetic.main.screen_capacity_3.radiobt_capacity_screen3_1
import kotlin.math.sqrt

class Capacity3Screen : BaseFragment(R.layout.screen_capacity_3) {
    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()

    var inputU = 0F
    var inputI = 0F
    var frequency = 0F

    var inputPhaseU = 0F
    var powerfactor = 0F
    var efficency = 0F
    var motorPowwer = 0F


    var operatingC = 0
    var startingC = 0
    var finishingC = 0
    var minVoltage = 0

    override fun initialize() {
        initClicks()

        initSpinners()


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
                    var t = input_capacity_screen3_5.text.toString().toFloat()
                    when (pos) {
                        0 -> {
                            if (t > 1) {
                                input_capacity_screen3_5.setText("" + t / 100F)
                            }
                        }
                        1 -> {
                            if (t < 1) {
                                input_capacity_screen3_5.setText("" + t * 100F)
                            }
                        }
                    }
                }
            }
    }

    private fun initClicks() {
        close.setOnClickListener { finishFragment() }



        TextChanged(input_capacity_screen3_1)
        TextChanged(input_capacity_screen3_2)
        TextChanged(input_capacity_screen3_3)
        TextChanged(input_capacity_screen3_4)
        TextChanged(input_capacity_screen3_5)
        TextChanged(input_capacity_screen3_6)
        TextChanged(input_capacity_screen3_7)



        spinnerSelectedListener(spinner_capacity_screen3_1)
        spinnerSelectedListener(spinner_capacity_screen3_2)


        clear_bt_capacity_screen3.setOnClickListener {
            input_capacity_screen3_1.text?.clear()
            input_capacity_screen3_3.text?.clear()
            input_capacity_screen3_6.text?.clear()
            input_capacity_screen3_7.text?.clear()
        }

        input_capacity_screen3_2.setText("50")
        input_capacity_screen3_4.setText("0.85")
        input_capacity_screen3_5.setText("0.9")




        if (radiobt_capacity_screen3_1.isChecked) {

            input_capacity_screen3_7.enableDisable(false)
            title_capacity_screen3.visibility = View.GONE
            liner_1_capacity_screen3.visibility = View.GONE
            liner_2_capacity_screen3.visibility = View.GONE
            liner_3_capacity_screen3.visibility = View.GONE
        }

        radiobt_screen3_2.setOnClickListener {

            input_capacity_screen3_7.enableDisable(true)
            input_capacity_screen3_6.enableDisable(false)

            radiobt_capacity_screen3_1.setChecked(false)
            title_capacity_screen3.visibility = View.VISIBLE
            liner_1_capacity_screen3.visibility = View.VISIBLE
            liner_2_capacity_screen3.visibility = View.VISIBLE
            liner_3_capacity_screen3.visibility = View.VISIBLE
        }

        radiobt_capacity_screen3_1.setOnClickListener {

            input_capacity_screen3_7.enableDisable(false)
            input_capacity_screen3_6.enableDisable(true)

            radiobt_screen3_2.setChecked(false)
            title_capacity_screen3.visibility = View.GONE
            liner_1_capacity_screen3.visibility = View.GONE
            liner_2_capacity_screen3.visibility = View.GONE
            liner_3_capacity_screen3.visibility = View.GONE
        }




        result_bt_capacity_screen3.setOnClickListener {

            if (radiobt_capacity_screen3_1.isChecked) {
                if (input_capacity_screen3_1.text.toString() != "" && input_capacity_screen3_2.text.toString() != "" && input_capacity_screen3_6.text.toString() != "") {
                    var k = false
                    if (radiobt_capacity_screen3_1.isChecked) k = false
                    if (radiobt_screen3_2.isChecked) k = true

                    var s = ""
                    if (input_capacity_screen3_5.text.toString().toFloat() > 1) s = " %"

                    var t = input_capacity_screen3_6.text.toString() + " Ампер"
                    if (radiobt_screen3_2.isChecked) t = inputI.toString() + " Ампер"

                    val dialog = context?.let { it1 ->
                        CapacityReport3Dialog(
                            it1,
                            spinner_capacity_screen3_1.selectedItem.toString(),
                            operating_value.text.toString(),
                            starting_value.text.toString(),
                            min_voltage_value.text.toString(),
                            input_capacity_screen3_1.text.toString() + " Вольт",
                            input_capacity_screen3_2.text.toString() + " Гц",
                            t,
                            input_capacity_screen3_3.text.toString() + " Вольт",
                            input_capacity_screen3_4.text.toString(),
                            input_capacity_screen3_5.text.toString() + s,
                            input_capacity_screen3_7.text.toString() + " кВт",
                            k
                        )
                    }
                    dialog!!.show()
                } else Toast.makeText(context, "Salom", Toast.LENGTH_SHORT).show()

            }
            if (radiobt_screen3_2.isChecked) {

                if (input_capacity_screen3_1.text.toString() != "" && input_capacity_screen3_2.text.toString() != "" && input_capacity_screen3_4.text.toString() != "" && input_capacity_screen3_7.text.toString() != "") {
                    var k = false
                    if (radiobt_capacity_screen3_1.isChecked) k = false
                    if (radiobt_screen3_2.isChecked) k = true

                    var s = ""
                    if (input_capacity_screen3_5.text.toString().toFloat() > 1) s = " %"

                    var t = input_capacity_screen3_6.text.toString() + " Ампер"
                    if (radiobt_screen3_2.isChecked) t = inputI.toString() + " Ампер"

                    val dialog = context?.let { it1 ->
                        CapacityReport3Dialog(
                            it1,
                            spinner_capacity_screen3_1.selectedItem.toString(),
                            operating_value.text.toString(),
                            starting_value.text.toString(),
                            min_voltage_value.text.toString(),
                            input_capacity_screen3_1.text.toString() + " Вольт",
                            input_capacity_screen3_2.text.toString() + " Гц",
                            t,
                            input_capacity_screen3_3.text.toString() + " Вольт",
                            input_capacity_screen3_4.text.toString(),
                            input_capacity_screen3_5.text.toString() + s,
                            input_capacity_screen3_7.text.toString() + " кВт",
                            k
                        )
                    }
                    dialog!!.show()

                } else Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
            }


        }
    }

    fun spinnerSelectedListener(spinner: Spinner) {

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                calculateInputs()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
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
                calculateInputs()

            }
        })
    }

    fun fillResults() {
        operating_value.setText("%.5f".format(operatingC)+ " µF" )
        starting_value.setText("%.5f".format(startingC)+ " µF" + "-" + "%.5f".format(finishingC)+ " µF" )
        min_voltage_value.setText("%.5f".format(minVoltage) + " V")
    }

    fun calculateInputs() {

        operating_value.setText("")
        starting_value.setText("")
        min_voltage_value.setText("")


        var input_text_volt = input_capacity_screen3_1.text.toString()
        var input_text_hz = input_capacity_screen3_2.text.toString()
        var input_text_power_amper = input_capacity_screen3_6.text.toString()

        //With Power

        var input_text_volt_thre_phase = input_capacity_screen3_3.text.toString()
        var input_power_factor = input_capacity_screen3_4.text.toString()
        var input_efficency = input_capacity_screen3_5.text.toString()
        var input_power = input_capacity_screen3_7.text.toString()







        if (radiobt_capacity_screen3_1.isChecked) {


            when (spinner_capacity_screen3_1.selectedItemPosition) {
                0 -> {
                    if (input_text_volt != "" && input_text_hz != "" && input_text_power_amper != "") {
                        inputU = input_text_volt.toFloat()
                        inputI = input_text_power_amper.toFloat()
                        frequency = input_text_hz.toFloat()

                        operatingC =
                            Math.ceil(1.5 * ((inputI / (2 * frequency * inputU * Math.PI))) * 1000000)
                                .toInt()
                        startingC = Math.ceil(operatingC * 2.5).toInt()
                        finishingC = operatingC * 3
                        minVoltage = Math.ceil(inputU * 1.15).toInt()

                        fillResults()

                    }
                }
                1 -> {
                    if (input_text_volt != "" && input_text_hz != "" && input_text_power_amper != "") {
                        inputU = input_text_volt.toFloat()
                        inputI = input_text_power_amper.toFloat()
                        frequency = input_text_hz.toFloat()

                        operatingC =
                            Math.ceil((sqrt(3.0) / 2) * ((inputI / (2 * frequency * inputU * Math.PI))) * 1000000)
                                .toInt()
                        startingC = Math.ceil(operatingC * 2.5).toInt()
                        finishingC = operatingC * 3
                        minVoltage = Math.ceil(inputU * 1.15).toInt()

                        fillResults()

                    }

                }

                2 -> {
                    if (input_text_volt != "" && input_text_hz != "" && input_text_power_amper != "") {
                        inputU = input_text_volt.toFloat()
                        inputI = input_text_power_amper.toFloat()
                        frequency = input_text_hz.toFloat()

                        operatingC =
                            Math.ceil(0.5 * ((inputI / (2 * frequency * inputU * Math.PI))) * 1000000)
                                .toInt()
                        startingC = Math.ceil(operatingC * 2.5).toInt()
                        finishingC = operatingC * 3
                        minVoltage = Math.ceil(inputU * 2.2).toInt()

                        fillResults()

                    }
                }

                3 -> {

                    if (input_text_volt != "" && input_text_hz != "" && input_text_power_amper != "") {
                        inputU = input_text_volt.toFloat()
                        inputI = input_text_power_amper.toFloat()
                        frequency = input_text_hz.toFloat()

                        operatingC =
                            Math.ceil((sqrt(3.0) / 2) * ((inputI / (2 * frequency * inputU * Math.PI))) * 1000000)
                                .toInt()

                        startingC = Math.ceil(operatingC * 2.5).toInt()
                        finishingC = operatingC * 3
                        minVoltage = Math.ceil(inputU * 2.2).toInt()

                        fillResults()
                    }

                }

            }
        }



        if (radiobt_screen3_2.isChecked) {


            when (spinner_capacity_screen3_1.selectedItemPosition) {
                0 -> {
                    if (input_text_volt != "" && input_text_hz != "" && input_power != "" && input_efficency != "" && input_power_factor != "" && input_text_volt_thre_phase != "") {


                        inputPhaseU = input_text_volt_thre_phase.toFloat()
                        powerfactor = input_power_factor.toFloat()

                        if (input_efficency.toFloat() > 1) efficency =
                            (input_efficency.toFloat()) / 100
                        else efficency = input_efficency.toFloat()
                        motorPowwer = 1000 * input_power.toFloat()

                        inputI =
                            (motorPowwer / (sqrt(3.0) * inputPhaseU * powerfactor * efficency)).toFloat()


                        inputU = input_text_volt.toFloat()
                        frequency = input_text_hz.toFloat()
                        operatingC =
                            Math.ceil(1.5 * ((inputI / (2 * frequency * inputU * Math.PI))) * 1000000)
                                .toInt()
                        startingC = Math.ceil(operatingC * 2.5).toInt()
                        finishingC = operatingC * 3
                        minVoltage = Math.ceil(inputU * 1.15).toInt()

                        fillResults()

                    }
                }
                1 -> {
                    if (input_text_volt != "" && input_text_hz != "" && input_power != "" && input_efficency != "" && input_power_factor != "" && input_text_volt_thre_phase != "") {


                        inputPhaseU = input_text_volt_thre_phase.toFloat()
                        powerfactor = input_power_factor.toFloat()

                        if (input_efficency.toFloat() > 1) efficency =
                            (input_efficency.toFloat()) / 100
                        else efficency = input_efficency.toFloat()
                        motorPowwer = 1000 * input_power.toFloat()

                        inputI =
                            (motorPowwer / (sqrt(3.0) * inputPhaseU * powerfactor * efficency)).toFloat()



                        inputU = input_text_volt.toFloat()
                        frequency = input_text_hz.toFloat()

                        operatingC =
                            Math.ceil((sqrt(3.0) / 2) * ((inputI / (2 * frequency * inputU * Math.PI))) * 1000000)
                                .toInt()
                        startingC = Math.ceil(operatingC * 2.5).toInt()
                        finishingC = operatingC * 3
                        minVoltage = Math.ceil(inputU * 1.15).toInt()

                        fillResults()

                    }

                }

                2 -> {
                    if (input_text_volt != "" && input_text_hz != "" && input_power != "" && input_efficency != "" && input_power_factor != "" && input_text_volt_thre_phase != "") {


                        inputPhaseU = input_text_volt_thre_phase.toFloat()
                        powerfactor = input_power_factor.toFloat()

                        if (input_efficency.toFloat() > 1) efficency =
                            (input_efficency.toFloat()) / 100
                        else efficency = input_efficency.toFloat()
                        motorPowwer = 1000 * input_power.toFloat()

                        inputI =
                            (motorPowwer / (sqrt(3.0) * inputPhaseU * powerfactor * efficency)).toFloat()



                        frequency = input_text_hz.toFloat()
                        inputU = input_text_volt.toFloat()
                        operatingC =
                            Math.ceil(0.5 * ((inputI / (2 * frequency * inputU * Math.PI))) * 1000000)
                                .toInt()
                        startingC = Math.ceil(operatingC * 2.5).toInt()
                        finishingC = operatingC * 3
                        minVoltage = Math.ceil(inputU * 2.2).toInt()

                        fillResults()
                    }
                }

                3 -> {


                    if (input_text_volt != "" && input_text_hz != "" && input_power != "" && input_efficency != "" && input_power_factor != "" && input_text_volt_thre_phase != "") {


                        inputPhaseU = input_text_volt_thre_phase.toFloat()
                        powerfactor = input_power_factor.toFloat()

                        if (input_efficency.toFloat() > 1) efficency =
                            (input_efficency.toFloat()) / 100
                        else efficency = input_efficency.toFloat()
                        motorPowwer = 1000 * input_power.toFloat()

                        inputI =
                            (motorPowwer / (sqrt(3.0) * inputPhaseU * powerfactor * efficency)).toFloat()




                        inputU = input_text_volt.toFloat()
                        frequency = input_text_hz.toFloat()

                        operatingC =
                            Math.ceil((sqrt(3.0) / 2) * ((inputI / (2 * frequency * inputU * Math.PI))) * 1000000)
                                .toInt()

                        startingC = Math.ceil(operatingC * 2.5).toInt()
                        finishingC = operatingC * 3
                        minVoltage = Math.ceil(inputU * 2.2).toInt()

                        fillResults()
                    }

                }

            }


        }


    }


}