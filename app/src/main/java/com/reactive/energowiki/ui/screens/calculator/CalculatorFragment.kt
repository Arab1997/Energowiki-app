package com.reactive.energowiki.ui.screens.calculator

import android.widget.Toast
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.CalculatorAdapter
import kotlinx.android.synthetic.main.fragment_calculator.*

class CalculatorFragment : BaseFragment(R.layout.fragment_calculator) {
    //
    override fun initialize() {
        recycler.adapter = CalculatorAdapter {
            when (it) {
                1 -> addFragment(ResistivityCalcScreen())
                2 -> addFragment(ConductorCalcScreen())
                3 -> addFragment(ResistanceCalcScreen())
                4 -> addFragment(SurfaceCalcScreen())
                else -> Toast.makeText(requireContext(), "inDevelopment", Toast.LENGTH_SHORT).show()
            }
        }.apply {
            setData(arrayListOf(1, 2, 3, 4, 5))
        }
    }
}