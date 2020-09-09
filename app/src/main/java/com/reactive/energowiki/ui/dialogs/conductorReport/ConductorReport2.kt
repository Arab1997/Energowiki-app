package com.reactive.energowiki.ui.dialogs.conductorReport

import android.content.Context
import com.reactive.energowiki.R
import com.reactive.energowiki.ui.dialogs.BaseDialog
import kotlinx.android.synthetic.main.conductor_report_2.view.*

class ConductorReport2(
    context: Context,
    text1: String,
    text2: String,
    text3: String,
    text4: String,
    text5: String
) : BaseDialog(context, R.layout.conductor_report_2) {
    init {
        view.apply {

            conductor_report2_close.setOnClickListener {
                close()
            }
            conductor_report2_text1.setText(text1)
            conductor_report2_text2.setText(text2)
            conductor_report2_text3.setText(text3)
            conductor_report2_text4.setText(text4)
            conductor_report2_text5.setText(text5)

        }
    }
}


