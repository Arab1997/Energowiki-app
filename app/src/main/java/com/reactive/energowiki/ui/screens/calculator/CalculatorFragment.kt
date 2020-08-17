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
import com.reactive.energowiki.ui.screens.calculator.dvigatel.Dvigatel1Screen
import com.reactive.energowiki.utils.extensions.toast
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
                        else -> toast(requireContext(), "inDevelopment")
                    }
                    CalculatorMenus.CAPACITY -> when (it) {
                        1 -> addFragment(Capacity1Screen())
                        2 -> addFragment(Capacity2Screen())
                        else -> toast(requireContext(), "inDevelopment")
                    }
                    CalculatorMenus.ENGINE -> when (it) {
                        1 -> addFragment(Dvigatel1Screen())
                        else -> toast(requireContext(), "inDevelopment")
                    }
                }

            }.apply {
                setData(
                    when (data.type) {
                        CalculatorMenus.BASICS -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7)
                        CalculatorMenus.CAPACITY -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9)
                        CalculatorMenus.CONDUCTOR -> arrayListOf<Any>(1, 2, 3, 4, 5, 6)
                        CalculatorMenus.ENGINE -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
                        CalculatorMenus.CABEL -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                        else -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9)
                    }
                )
            }
    }
}

data class CalculatorData(val name: String, val type: CalculatorMenus)

enum class CalculatorMenus {
    BASICS, CAPACITY, CONDUCTOR, ENGINE, CABEL
}