package com.reactive.energowiki.ui.screens

import android.annotation.SuppressLint
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_calculation.*


class CalculatorScreen : BaseFragment(R.layout.screen_calculation) {


    override fun initialize() {

        initViews()

        initClicks()

        initEditTexts()

        initSpinners()
    }

    private fun initViews() {

        header.text = "Закон Ома постоянного тока"
    }

    private fun initSpinners() {
        val rValues = arrayListOf<String>("пОм", "нОм", "мкОм", "мОм", "Ом", "кОм", "МОм", "ГОм")
        val lValues = arrayListOf<String>("м", "ft", "км", "см", "мм")
        val SValues = arrayListOf<String>(Html.fromHtml("мм<sup>2</sup>").toString(), "мм", "kcmil")
        val ra: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, rValues)
        ra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = ra
        val la: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, lValues)
        la.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = la
        val aa: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, SValues)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = aa

        spinner1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

        }
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {
            input1.text?.clear()
            input2.text?.clear()
            input3.text?.clear()
        }

        resultBtn.setOnClickListener {
            initCalculation()
        }
    }

    private fun initEditTexts() {
        input1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }

        })
        input2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }

        })
        input3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }

        })
    }

    private fun initCalculation() {
        val R = input1.text.toString().let { if (it.isEmpty()) 0 else it.toInt() }
        val l = input2.text.toString().let { if (it.isEmpty()) 1 else it.toInt() }
        val s = input3.text.toString().let { if (it.isEmpty()) 0 else it.toInt() }
        val result = R * s / l
        showResult(result)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(res: Int) {
        result.text = "$res В"
    }
}