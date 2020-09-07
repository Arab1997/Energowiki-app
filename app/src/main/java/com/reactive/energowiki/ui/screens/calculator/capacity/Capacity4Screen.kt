package com.reactive.energowiki.ui.screens.calculator.capacity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.utils.extensions.enable
import com.reactive.energowiki.utils.extensions.enableDisable
import com.reactive.energowiki.utils.extensions.toastLong
import kotlinx.android.synthetic.main.bottomsheet_detail.*
import kotlinx.android.synthetic.main.screen_capacity_3.*
import kotlinx.android.synthetic.main.screen_capacity_3.spinner_capacity_screen3_1
import kotlinx.android.synthetic.main.screen_capacity_4.*

class Capacity4Screen : BaseFragment(R.layout.screen_capacity_4) {
    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()
    private val spinValues0 = arrayListOf<ArrayList<String>>()
    var birlik: Float = 0.0F
    var inputU: Float = 0.0F
    var outputU: Float = 0.0F
    var C1: Float = 0.0F
    var C2: Float = 0.0F


    override fun initialize() {
        initClicks()

        initSpinners()
    }

    private fun initSpinners() {

//Third spinner set up

        spinValues0.add(
            arrayListOf(
                "Напражение выход",
                "Напражение вход",
                "Емкость С1",
                "Емкость С2"
            )
        )


        val adapter0: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues0[0])
        adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_capacity_screen4_1.adapter = adapter0

        spinner_capacity_screen4_1.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    calculation()
                    when (pos) {
                        0 -> {
                            capacity_screen_title.setText("Напражение выход")

                            spinner_capacity_screen4_2.isEnabled = true
                            input_capacity_screen4_1.enableDisable(true)

                            spinner_capacity_screen4_3.isEnabled = true
                            input_capacity_screen4_2.enableDisable(true)

                            spinner_capacity_screen4_4.isEnabled = true
                            input_capacity_screen4_3.enableDisable(true)

                            spinner_capacity_screen4_5.isEnabled = false
                            input_capacity_screen4_4.enableDisable(false)


                        }
                        1 -> {
                            capacity_screen_title.setText("Напражение вход")

                            spinner_capacity_screen4_2.isEnabled = false
                            input_capacity_screen4_1.enableDisable(false)

                            spinner_capacity_screen4_3.isEnabled = true
                            input_capacity_screen4_2.enableDisable(true)

                            spinner_capacity_screen4_4.isEnabled = true
                            input_capacity_screen4_3.enableDisable(true)

                            spinner_capacity_screen4_5.isEnabled = true
                            input_capacity_screen4_4.enableDisable(true)

                        }
                        2 -> {
                            capacity_screen_title.setText("Емкость С1")



                            spinner_capacity_screen4_2.isEnabled = true
                            input_capacity_screen4_1.enableDisable(true)

                            spinner_capacity_screen4_3.isEnabled = false
                            input_capacity_screen4_2.enableDisable(false)

                            spinner_capacity_screen4_4.isEnabled = true
                            input_capacity_screen4_3.enableDisable(true)

                            spinner_capacity_screen4_5.isEnabled = true
                            input_capacity_screen4_4.enableDisable(true)
                        }
                        3 -> {
                            capacity_screen_title.setText("Емкость С2")

                            spinner_capacity_screen4_2.isEnabled = true
                            input_capacity_screen4_1.enableDisable(true)

                            spinner_capacity_screen4_3.isEnabled = true
                            input_capacity_screen4_2.enableDisable(true)

                            spinner_capacity_screen4_4.isEnabled = false
                            input_capacity_screen4_3.enableDisable(false)

                            spinner_capacity_screen4_5.isEnabled = true
                            input_capacity_screen4_4.enableDisable(true)
                        }
                    }
                }
            }


        //Second spinner set up
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
        spinner_capacity_screen4_2.adapter = adapter1


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
        spinner_capacity_screen4_3.adapter = adapter2


        //Fourth spinner set up

        val adapter3: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues2[0])
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_capacity_screen4_4.adapter = adapter3

        val adapter4: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues1[0])
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_capacity_screen4_5.adapter = adapter4


    }

    private fun initClicks() {
        close.setOnClickListener { finishFragment() }


        clear_bt_capacity_screen4.setOnClickListener {
            input_capacity_screen4_1.text?.clear()
            input_capacity_screen4_2.text?.clear()
            input_capacity_screen4_3.text?.clear()
            input_capacity_screen4_4.text?.clear()
        }

        TextChanged(input_capacity_screen4_1)
        TextChanged(input_capacity_screen4_2)
        TextChanged(input_capacity_screen4_3)
        TextChanged(input_capacity_screen4_4)

        spinnerSelectedListener(spinner_capacity_screen4_1)
        spinnerSelectedListener(spinner_capacity_screen4_2)
        spinnerSelectedListener(spinner_capacity_screen4_3)
        spinnerSelectedListener(spinner_capacity_screen4_4)
        spinnerSelectedListener(spinner_capacity_screen4_5)


    }

    fun calculation() {

        when (spinner_capacity_screen4_1.selectedItemPosition) {
            0 -> {

                capacity_4result.text=""
                if (input_capacity_screen4_1.text.toString() != "" && input_capacity_screen4_2.text.toString() != "" && input_capacity_screen4_3.text.toString() != "") {
                    //hisoblash
                    inputU = input_capacity_screen4_1.text.toString().toFloat()
                    C1 = input_capacity_screen4_2.text.toString().toFloat()
                    C2 = input_capacity_screen4_3.text.toString().toFloat()
                    outputU = (inputU * getBirlik(spinner_capacity_screen4_2) * C1 * getBirlik(
                        spinner_capacity_screen4_3
                    )) / (getBirlik(spinner_capacity_screen4_3) * C1 + C2 * getBirlik(
                        spinner_capacity_screen4_4
                    ))

                    if (outputU >= 0)
                        capacity_4result.setText(outputU.toString() + " V")
                    else
                        Toast.makeText(context, "ошибка, U<0", Toast.LENGTH_SHORT).show()


                }
            }

            1 -> {
                capacity_4result.text=""
                if (input_capacity_screen4_2.text.toString() != "" && input_capacity_screen4_3.text.toString() != "" && input_capacity_screen4_4.text.toString() != "") {
                    //hisoblash
                    outputU = input_capacity_screen4_4.text.toString().toFloat()
                    C1 = input_capacity_screen4_2.text.toString().toFloat()
                    C2 = input_capacity_screen4_3.text.toString().toFloat()
                    inputU = (outputU * getBirlik(spinner_capacity_screen4_5) * (getBirlik(
                        spinner_capacity_screen4_3
                    ) * C1 + C2 * getBirlik(spinner_capacity_screen4_4))) / getBirlik(
                        spinner_capacity_screen4_3
                    ) * C1

                    if (inputU >= 0)
                        capacity_4result.setText(inputU.toString() + " V")
                    else
                        Toast.makeText(context, "ошибка, U<0", Toast.LENGTH_SHORT).show()


                }


            }

            2 -> {
                capacity_4result.text=""
                if (input_capacity_screen4_1.text.toString() != "" && input_capacity_screen4_3.text.toString() != "" && input_capacity_screen4_4.text.toString() != "") {
                    //hisoblash

                    outputU = input_capacity_screen4_4.text.toString().toFloat()
                    inputU = input_capacity_screen4_1.text.toString().toFloat()
                    C2 = input_capacity_screen4_3.text.toString().toFloat()
//c1 text
                    C1 = outputU * getBirlik(spinner_capacity_screen4_5) * getBirlik(
                        spinner_capacity_screen4_4
                    ) * C2 / (inputU * getBirlik(spinner_capacity_screen4_2) - outputU * getBirlik(
                        spinner_capacity_screen4_5
                    ))



                    if (C1 >= 0)
                        capacity_4result.setText(C1.toString() + " V")
                    else
                        Toast.makeText(context, "ошибка, U<0", Toast.LENGTH_SHORT).show()

                }


            }

            3 -> {
                capacity_4result.text=""
                if (input_capacity_screen4_1.text.toString() != "" && input_capacity_screen4_2.text.toString() != "" && input_capacity_screen4_4.text.toString() != "") {
                    //hisoblash
                    outputU = input_capacity_screen4_4.text.toString().toFloat()
                    inputU = input_capacity_screen4_1.text.toString().toFloat()
                    C1 = input_capacity_screen4_2.text.toString().toFloat()

                    C2 = getBirlik(spinner_capacity_screen4_3) * C1 * (inputU * getBirlik(
                        spinner_capacity_screen4_2
                    ) - outputU * getBirlik(spinner_capacity_screen4_5)) / outputU * getBirlik(
                        spinner_capacity_screen4_5
                    )
                    if (C2 >= 0)
                        capacity_4result.setText(C2.toString() + " V")
                    else
                        Toast.makeText(context, "ошибка, U<0", Toast.LENGTH_SHORT).show()

                }
            }

        }
    }


    fun getBirlik(spinner: Spinner): Float {
        when (spinner.selectedItemPosition) {
            0 -> birlik = 1.0e-12.toFloat()
            1 -> birlik = 1.0e-9.toFloat()
            2 -> birlik = 1.0e-6.toFloat()
            3 -> birlik = 1.0e-3.toFloat()
            4 -> birlik = 1.toFloat()
            5 -> birlik = 1.0e3.toFloat()
            6 -> birlik = 1.0e6.toFloat()
            7 -> birlik = 1.0e9.toFloat()
            else -> 1.toFloat()
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


}