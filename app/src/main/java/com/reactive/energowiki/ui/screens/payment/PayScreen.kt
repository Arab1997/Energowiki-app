package com.reactive.energowiki.ui.screens.payment

import android.view.View
import android.widget.AdapterView
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.screens.HomeMenus
import com.reactive.energowiki.utils.common.TextWatcherInterface
import com.reactive.energowiki.utils.extensions.disable
import com.reactive.energowiki.utils.extensions.enable
import com.reactive.energowiki.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_pay.*

class PayScreen : BaseFragment(R.layout.screen_pay) {

    companion object {
        private var type: HomeMenus = HomeMenus.GAS
        fun newInstance(type: HomeMenus): PayScreen {
            this.type = type
            return PayScreen()
        }
    }

    private var regionPos = 0
    private var filialPos = 0

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        header.text = if (type == HomeMenus.GAS) "оплата за газ" else "оплата за электренергию"

        filial.text = if (type == HomeMenus.GAS) "РЭС" else "Филиал"

        pay.disable()

        number.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
        sum.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })

        filialSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                filialPos = position
            }

        }
        regionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                regionPos = position
            }

        }
        pay.setOnClickListener { inDevelopment(requireContext()) }
    }

    private fun check() {
        pay.disable()

        if (number.text.toString().length < 8 || sum.text.toString().length < 3) return

        pay.enable()
    }

}