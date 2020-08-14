package com.reactive.energowiki.ui.screens.calculator

import android.widget.Toast
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.CalculatorAdapter
import kotlinx.android.synthetic.main.fragment_calculator.*

class CalculatorFragment : BaseFragment(R.layout.fragment_calculator) {

    override fun initialize() {
        recycler.adapter = CalculatorAdapter {
            when (it) {
                1 -> Toast.makeText(requireContext(), "asd1", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(requireContext(), "asd2", Toast.LENGTH_SHORT).show()
            }
        }.apply {
            setData(arrayListOf(1, 2, 3, 4, 5))
        }
    }
}