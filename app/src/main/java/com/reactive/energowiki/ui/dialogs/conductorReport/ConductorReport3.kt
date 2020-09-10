package com.reactive.energowiki.ui.dialogs.conductorReport

import android.content.Context
import com.reactive.energowiki.R
import com.reactive.energowiki.ui.dialogs.BaseDialog
import kotlinx.android.synthetic.main.conductor_report_3.view.*

class ConductorReport3(
    context: Context,
    text1: String,
    text2: String,
    text3: String,
    text4: String,
    text5: String,
    text6: String,
    text7: String
) : BaseDialog(context, R.layout.conductor_report_3) {
    init {
        view.apply {

            conductor_report3_close.setOnClickListener {
                close()
            }
            conductor_report3_text1.setText(text1)
            conductor_report3_text2.setText(text2)
            conductor_report3_text3.setText(text3)
            conductor_report3_text4.setText(text4)
            conductor_report3_text5.setText(text5)
            conductor_report3_text6.setText(text6)
            conductor_report3_text7.setText(text7)

        }
    }
}


