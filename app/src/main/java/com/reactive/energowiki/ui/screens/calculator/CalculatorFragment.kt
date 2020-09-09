package com.reactive.energowiki.ui.screens.calculator

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.CalculatorAdapter
import com.reactive.energowiki.ui.screens.calculator.basics.*
import com.reactive.energowiki.ui.screens.calculator.cabel.*
import com.reactive.energowiki.ui.screens.calculator.capacity.*
import com.reactive.energowiki.ui.screens.calculator.conductor.Conductor1Screen
import com.reactive.energowiki.ui.screens.calculator.engine.*
import com.reactive.energowiki.utils.extensions.toast
import kotlinx.android.synthetic.main.fragment_calculator.*

class CalculatorFragment : BaseFragment(R.layout.fragment_calculator) {

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
                        3 -> addFragment(Capacity3Screen())
                        4 -> addFragment(Capacity4Screen())
                        5 -> addFragment(Capacity5Screen())
                        6 -> toast(requireContext(), "inDevelopment")
                        7 -> toast(requireContext(), "inDevelopment")
                        8 -> addFragment(Capacity8Screen())
                        else -> toast(requireContext(), "inDevelopment")
                    }
                    CalculatorMenus.ENGINE -> when (it) {
                        1 -> addFragment(Engine1Screen())
                        2 -> addFragment(Engine2Screen())
                        3 -> addFragment(Engine3Screen())
                        4 -> addFragment(Engine4Screen())
                        5 -> addFragment(Engine5Screen())
                        6 ->addFragment(Engine6Screen())
                        7 -> addFragment(Engine7Screen())
                        8 -> addFragment(Engine8Screen())
                        9 -> addFragment(Engine9Screen())
                        10 -> addFragment(Engine10Screen())
                        11 -> addFragment(Engine11Screen())
                        12 -> addFragment(Engine12Screen())
                        13 -> addFragment(Engine13Screen())
                        else -> toast(requireContext(), "inDevelopment")
                    }
                    CalculatorMenus.CABEL -> when (it) {
                        1 -> addFragment(Cabel1Screen())
                        2 -> addFragment(Cabel2Screen())
                        3 -> addFragment(Cabel3Screen())
                        4 -> addFragment(Cabel4Screen())
                        5 -> addFragment(Cabel5Screen())
                        6 -> addFragment(Cabel6Screen())
                        7 -> addFragment(Cabel7Screen())
                        8 -> addFragment(Cabel8Screen())
                        9 -> addFragment(Cabel9Screen())
                        10 -> addFragment(Cabel10Screen())
                        else -> toast(requireContext(), "inDevelopment")
                    }

                    CalculatorMenus.CONDUCTOR -> when (it) {
                        1 -> addFragment(Conductor1Screen())
                      /*  2 -> addFragment(Conductor1Screen())
                        3 -> addFragment(Conductor1Screen())
                        4 -> addFragment(Conductor1Screen())
                        5 -> addFragment(Conductor1Screen())
                        6 -> addFragment(Conductor1Screen())*/
                        else -> toast(requireContext(), "inDevelopment")
                    }
                }

            }.apply {
                setData(
                    when (data.type) {
                        CalculatorMenus.BASICS -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7)
                        CalculatorMenus.CAPACITY -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8)
                        CalculatorMenus.CONDUCTOR -> arrayListOf<Any>(1, 2, 3, 4, 5, 6)
                        CalculatorMenus.ENGINE -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13)
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