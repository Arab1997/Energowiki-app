package com.reactive.energowiki.ui.dialogs.pueReports

import android.content.Context
import com.reactive.energowiki.R
import com.reactive.energowiki.ui.dialogs.BaseDialog
import kotlinx.android.synthetic.main.conductor_report_1.view.*

class PueReport1(
    context: Context,
    text1: String,
    text2: String,
    text3: String,
    text4: String,
    text5: String
) : BaseDialog(context, R.layout.conductor_report_1) {
    init {
        view.apply {

            conductor_report1_close.setOnClickListener {
                close()
            }
            conductor_report1_text1.setText(text1)
            conductor_report1_text2.setText(text2)
            conductor_report1_text3.setText(text3)
            conductor_report1_text4.setText(text4)
            conductor_report1_text5.setText(text5)

        }
    }
}


