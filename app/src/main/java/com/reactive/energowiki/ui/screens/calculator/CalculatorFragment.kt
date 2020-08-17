package com.reactive.energowiki.ui.screens.calculator

import android.widget.Toast
import androidx.annotation.DrawableRes
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.CalculatorAdapter
import com.reactive.energowiki.ui.screens.HomeMenus
import com.reactive.energowiki.ui.screens.calculator.basics.*
import com.reactive.energowiki.ui.screens.calculator.capacity.Capacity1Screen
import com.reactive.energowiki.ui.screens.calculator.capacity.Capacity2Screen
import kotlinx.android.synthetic.main.fragment_calculator.*

class CalculatorFragment : BaseFragment(R.layout.fragment_calculator) {
    //
    companion object {
        private lateinit var data: CalculatorData
        fun newInstance(data: CalculatorData): CalculatorFragment {
            this.data = data
            return CalculatorFragment()
        }
    }

    override fun initialize() {
        recycler.adapter =
            CalculatorAdapter(data ?: CalculatorData("Основы", CalculatorMenus.BASICS)) {
                when (data.type) {
                    CalculatorMenus.BASICS -> when (it) {
                        1 -> addFragment(ResistivityCalcScreen())
                        2 -> addFragment(ConductorCalcScreen())
                        3 -> addFragment(VoltageCalcScreen())
                        4 -> addFragment(ContinousCurrCalcScreen())
                        5 -> addFragment(AlternativeCurrCalcScreen())
                        6 -> addFragment(ResistanceCalcScreen())
                        7 -> addFragment(SurfaceCalcScreen())
                        else -> Toast.makeText(
                            requireContext(),
                            "inDevelopment",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    CalculatorMenus.CAPACITY -> when (it) {
                        1 -> addFragment(Capacity1Screen())
                        2 -> addFragment(Capacity2Screen())
                        else -> Toast.makeText(
                            requireContext(),
                            "inDevelopment",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }.apply {
                setData(
                    when (data.type) {
                        CalculatorMenus.BASICS -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7)
                        CalculatorMenus.CAPACITY -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9)
                        else -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9)
                    }
                )
            }
    }
}

data class CalculatorData(val name: String, val type: CalculatorMenus)

enum class CalculatorMenus {
    BASICS, CAPACITY
}