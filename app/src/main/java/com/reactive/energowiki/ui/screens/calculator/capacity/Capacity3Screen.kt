package com.reactive.energowiki.ui.screens.calculator.capacity

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

class Capacity3Screen: BaseFragment(R.layout.screen_capacity_3){
    private val spinValues1 = arrayListOf<ArrayList<String>>()
    private val spinValues2 = arrayListOf<ArrayList<String>>()
    override fun initialize() {
        initClicks()

        initSpinners()

    }

    private fun initSpinners() {



        spinValues1.add(arrayListOf("Треугольник", "Звезда", "Неполная звезда (а)", "Неполная звезда (б)"))
        val adapter1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues1[0])
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner_capacity_screen3_1.adapter = adapter1


        spinValues2.add(arrayListOf("отн.ед", "%"))
        val adapter2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues2[0])
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_capacity_screen3_2.adapter = adapter2

        spinner_capacity_screen3_1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {text_1_screen_capacity.setText( when(pos) {
                0 -> "Треугольник"
                1 -> "Звезда"
                2 -> "Неполная звезда (а)"
                3 -> "Неполная звезда (б)"
                else -> "Треугольник"

            })



               // initCalculation()
            }
        }


        spinner_capacity_screen3_2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                val h = input_capacity_screen3_5.text.toString()
                if(h!=""){
                    if(pos == 1){
                         input_capacity_screen3_5.setText((h.toFloat()*100).toString())
                    }

                }
              //  initCalculation()
            }
        }
    }

    private fun initClicks(){
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


        if(radiobt_capacity_screen3_1.isChecked){


               title_capacity_screen3.visibility = View.GONE
               liner_1_capacity_screen3.visibility = View.GONE
               liner_2_capacity_screen3.visibility = View.GONE
               liner_3_capacity_screen3.visibility = View.GONE
        }

        radiobt_capacity_screen3_2.setOnClickListener{

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

 fun calculateInputs(){



 }

}