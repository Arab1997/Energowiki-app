package com.reactive.energowiki.ui.screens.dvigates

import android.widget.Toast
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.Dvigatel1Adapter
import com.reactive.energowiki.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.fragment_calculator.*

class Calculator1Fragment : BaseFragment(R.layout.fragment_calculator) {
    //
    override fun initialize() {
        recycler.adapter = Dvigatel1Adapter {
            when (it) {
                1 ->addFragment(Dvigatel1_1CalcScreen())
                2 -> inDevelopment(requireContext())
                3 -> inDevelopment(requireContext())
                4 -> inDevelopment(requireContext())
                5 -> inDevelopment(requireContext())
                6 -> inDevelopment(requireContext())
                7 -> inDevelopment(requireContext())
                8 -> inDevelopment(requireContext())
                9 -> inDevelopment(requireContext())
                else -> Toast.makeText(requireContext(), "inDevelopment", Toast.LENGTH_SHORT).show()
            }
        }.apply {
            setData(arrayListOf(1, 2, 3, 4, 5, 6, 7,8,9))
        }
    }
}