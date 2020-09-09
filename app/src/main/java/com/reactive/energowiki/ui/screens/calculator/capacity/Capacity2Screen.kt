package com.reactive.energowiki.ui.screens.calculator.capacity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.dialogs.capacityReport.CapacityReport2Dialog
import com.reactive.energowiki.utils.extensions.enableDisable
import kotlinx.android.synthetic.main.bottomsheet_detail.*
import kotlinx.android.synthetic.main.screen_capacity_2.*
import kotlin.math.log
import kotlin.math.pow

class Capacity2Screen : BaseFragment(R.layout.screen_capacity_2) {

    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()

    var birlik = 0F
    var Xc = 0F
    var Fi = 0F
    var C = 0F

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
        result_bt_capacity_screen2.enableDisable(false)

        clear_bt_capacity_screen2.setOnClickListener {
            input_capacity_screen2_1.text?.clear()
            input_capacity_screen2_2.text?.clear()
        }


        result_bt_capacity_screen2.setOnClickListener {

            val dialog = context?.let { it1 ->
                CapacityReport2Dialog(
                    it1,
                    input_capacity_screen2_1.text.toString() + " " + spinner_capacity_screen2_1.selectedItem,
                    input_capacity_screen2_2.text.toString() + " " + spinner_capacity_screen2_1.selectedItem,
                    capacity_screen2_result1.text.toString()
                )
            }
            dialog!!.show()
            calculation()
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
                if (input_capacity_screen2_1.text.toString() == "" && input_capacity_screen2_2.text.toString() == "")
                    result_bt_capacity_screen2.enableDisable(false)
                else result_bt_capacity_screen2.enableDisable(true)
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

            Xc = (1 / (2 * Math.PI * C * Fi * getBirlik(spinner_capacity_screen2_1) * getBirlik(
                spinner_capacity_screen2_2
            ))).toFloat()

            if (Xc >= 0) {
                capacity_screen2_result1.text = getZeroNums(Xc)
            } else {
                Toast.makeText(context, "ошибка, Xc<0", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun getZeroNums(num: Float): String {
        // answers
        var end = ""
        var ansPowDegree = 0

        // constants
        var diff = 100000000
        val cDegrees = listOf(-12, -9, -6, -3, 3, 6, 9, 12)
        val ends = listOf(" pΩ", " nΩ", " µΩ", " mΩ", " kΩ", "MΩ", "GΩ", "TΩ")

        // calculating powDegree(e.g for 0.0045 = 4.5 * 10^(-3). powDegree = -3
        val loggedVal = log(num, 10.toFloat()).toInt()
        val powDegree = loggedVal + if (num * 10f.pow(Math.abs(loggedVal)) < 1.0) -1 else 0

        // calculate which ends and its value
        cDegrees.forEachIndexed { index, value ->
            val tempDif = powDegree - value
            if (diff > Math.abs(tempDif)) {
                diff = tempDif
                end = ends[index]
                ansPowDegree = value
            }
        }

        return "${(num * 10f.pow(-ansPowDegree))} $end"
    }

}