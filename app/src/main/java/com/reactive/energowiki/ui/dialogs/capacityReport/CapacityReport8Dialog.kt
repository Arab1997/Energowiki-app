package com.reactive.energowiki.ui.dialogs.capacityReport

import android.content.Context
import com.reactive.energowiki.R
import com.reactive.energowiki.ui.dialogs.BaseDialog
import kotlinx.android.synthetic.main.capacity_report_8.view.*

class CapacityReport8Dialog(
    context: Context,
    text1: String,
    text2: String,
    text3: String,
    text4: String,
    text5: String,
    text6: String
) : BaseDialog(context, R.layout.capacity_report_8) {
    init {
        view.apply {

            capacity_report8_close.setOnClickListener {
                close()
            }
            capacity_report8_text1.setText(text1)
            capacity_report8_text2.setText(text2)
            capacity_report8_text3.setText(text3)
            capacity_report8_text4.setText(text4)
            capacity_report8_text5.setText(text5)
            capacity_report8_text6.setText(text6)
        }
    }
}


