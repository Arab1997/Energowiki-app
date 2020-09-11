package com.reactive.energowiki.ui.screens

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.PueAdapter
import com.reactive.energowiki.ui.screens.calculator.cabel.*
import com.reactive.energowiki.ui.screens.calculator.capacity.Capacity1Screen
import com.reactive.energowiki.ui.screens.calculator.capacity.Capacity2Screen
import com.reactive.energowiki.ui.screens.calculator.capacity.Capacity3Screen
import com.reactive.energowiki.ui.screens.calculator.engine.*
import com.reactive.energowiki.ui.screens.pue.*
import com.reactive.energowiki.utils.extensions.toast
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.fragment_pue.*

class PueFragment : BaseFragment(R.layout.fragment_pue) {

    companion object {
        private lateinit var data: PueData
        fun newInstance(data: PueData): PueFragment {
            this.data = data
            return PueFragment()
        }
    }

    override fun initialize() {
        header.text = "ПУЭ"
        close.setOnClickListener { finishFragment() }

        recyclerPue.adapter =
            PueAdapter(
                data ?: PueData("Основы", PueMenus.BASICS)
            ) {
                when (data.type) {
                    PueMenus.BASICS -> when (it) {
                        //1 -> addFragment(AllowAmpacityForWireScreen())
                        1 -> addFragment(Screen1())
                       // 2 -> addFragment(WireSizeScreen())
                        2 -> addFragment(Screen2())
                        3 -> addFragment(MotorProtectionScreen())
                        4 -> addFragment(MaxCirCondLengthScreen())
                        5 -> addFragment(MaxCirAmpacityScreen())

                        else -> toast(requireContext(), "inDevelopment")
                    }
                    PueMenus.CAPACITY -> when (it) {
                        1 -> addFragment(Capacity1Screen())
                        2 -> addFragment(Capacity2Screen())
                        3 -> addFragment(Capacity3Screen())
                        else -> toast(requireContext(), "inDevelopment")
                    }
                    PueMenus.ENGINE -> when (it) {
                        1 -> addFragment(Engine1Screen())
                        2 -> addFragment(Engine2Screen())
                        3 -> addFragment(Engine3Screen())
                        4 -> addFragment(Engine4Screen())
                        5 -> addFragment(Engine5Screen())
                        6 -> addFragment(Engine6Screen())
                        7 -> addFragment(Engine7Screen())
                        8 -> addFragment(Engine8Screen())
                        9 -> addFragment(Engine9Screen())
                        10 -> addFragment(Engine10Screen())
                        11 -> addFragment(Engine11Screen())
                        12 -> addFragment(Engine12Screen())
                        13 -> addFragment(Engine13Screen())
                        else -> toast(requireContext(), "inDevelopment")
                    }
                    PueMenus.CABEL -> when (it) {
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
                }

            }.apply {
                setData(
                    when (data.type) {
                        PueMenus.BASICS -> arrayListOf<Any>(1, 2, 3, 4, 5)
                        PueMenus.CAPACITY -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9)
                        PueMenus.CONDUCTOR -> arrayListOf<Any>(1, 2, 3, 4, 5, 6)
                        PueMenus.ENGINE -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13)
                        PueMenus.CABEL -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                        else -> arrayListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9)
                    }
                )
            }
    }
}

data class PueData(val name: String, val type: PueMenus)

enum class PueMenus {
    BASICS, CAPACITY, CONDUCTOR, ENGINE, CABEL
}