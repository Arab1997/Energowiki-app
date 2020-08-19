package com.reactive.energowiki.ui.screens.calculator.cabel

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_cabel_6.*
import kotlin.math.pow

class Cabel6Screen: BaseFragment(R.layout.screen_cabel_6){
    private val spinValues = arrayListOf<ArrayList<String>>()
    var koef1=2.71
    var koef2=1.0
    var koef3=1.0
    var koef4=1.0
    var koef5=1.0
    var koef6=1.0

    override fun initialize() {
        initViews()

        initClicks()

        initSpinners()

        initEditTexts()

    }

    private fun initViews() {

        header.text = "Расчет веса кабеля"
    }
    private fun initSpinners() {
        spinValues.add(arrayListOf("Медь", "Алюминий"))
        spinValues.add(arrayListOf("м", "фт"))
        spinValues.add(
            arrayListOf(
                "ПВХ 105°С",
                "Неопрен 60°С",
                "ЕР резина 105°С",
                "Полиетелин устойчив к огню 80°С",
                "Полиетелин/полиефирная пленка 105°С",
                "Полисульфон 125°С",
                "Полиуретан 80°С",
                "Нейлон 105°С",
                "Полиетилен 80°С",
                "Полипропилен 80°С",
                "Вспененный полиетилен 80°С"
            )
        )
        spinValues.add(arrayListOf("мм²", "мм", "kcmil"))
        spinValues.add(arrayListOf("1 сплошной", "19", "37", "49", "133 и боле"))

        val a1: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[0])
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = a1
        val a2: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[1])
        a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = a2
        val a3: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[2])
        a3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = a3
        spinner8.adapter = a3
        val a4: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[3])
        a4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner6.adapter = a4
        val a5: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinValues[4])
        a5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner7.adapter = a4

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                if (pos == 1) koef1 = 8.89
                else koef1 = 2.71
                initCalculation()
            }
        }
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                if (pos == 1) koef2 = 0.305
                else koef2 = 1.0

                initCalculation()
            }
        }
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                koef3 = 1.0
                koef3 += pos //todo
                initCalculation()
            }
        }
        spinner6.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                koef4 = 1.0
                koef4 *= when (pos) {
                    1 -> 1.0// todo
                    2 -> 2 * 10.0.pow(-6)
                    else -> 1.0
                }

                initCalculation()
            }
        }
        spinner7.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                koef5 = 1.0
                koef5 *= when (pos) {
                    1 -> 1.02
                    2 -> 1.026
                    3 -> 1.03
                    4 -> 1.04
                    else -> 1.0
                }
                initCalculation()
            }
        }
        spinner8.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                koef6 = 1.0
                koef6 += pos //todo
                initCalculation()
            }
        }
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        clearBtn.setOnClickListener {

            input2.text?.clear()
            input4.text?.clear()
            input5.text?.clear()
            input61.text?.clear()
            input62.text?.clear()
            input9.text?.clear()

        }
        resultBtn.setOnClickListener {
            initCalculation()
        }
    }

    private fun initEditTexts() {
        input2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input5.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input61.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input62.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initCalculation()
            }
        })
        input9.addTextChangedListener(object : TextWatcher {
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
        var inputNum2 = input2.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum4 = input4.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum5 = input5.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum61 = input61.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum62 = input62.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }
        var inputNum9 = input9.text.toString().let { if (it.isEmpty()) 0.0 else it.toDouble() }

        var res1:Double= 0.0
        var res2:Double= 0.0


//        res1 = //todo
        res2=res1*2.205
        showResult((res1*1000).toInt().toDouble()/1000, (res2*1000).toInt().toDouble()/1000)
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(res1: Double, res2:Double){
        result.text = "$res1 кг | $res2 lbs"
    }
}
