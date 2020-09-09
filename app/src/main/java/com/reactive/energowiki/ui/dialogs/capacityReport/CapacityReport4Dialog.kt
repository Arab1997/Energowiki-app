package com.reactive.energowiki.ui.dialogs.capacityReport

import android.content.Context
import com.reactive.energowiki.R
import com.reactive.energowiki.ui.dialogs.BaseDialog
import kotlinx.android.synthetic.main.capacity_report_4.view.*

class CapacityReport4Dialog(
    context: Context,
    text1: String,
    text2: String,
    text3: String,
    text4: String
) : BaseDialog(context, R.layout.capacity_report_4) {
    init {
        view.apply {

            capacity_report4_close.setOnClickListener {
                close()
            }
            capacity_report4_text1.setText(text1)
            capacity_report4_text2.setText(text2)
            capacity_report4_text3.setText(text3)
            capacity_report4_text4.setText(text4)

        }
    }
}


