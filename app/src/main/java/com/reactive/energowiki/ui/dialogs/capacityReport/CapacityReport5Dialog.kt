package com.reactive.energowiki.ui.dialogs.capacityReport

import android.content.Context
import com.reactive.energowiki.R
import com.reactive.energowiki.ui.dialogs.BaseDialog
import kotlinx.android.synthetic.main.capacity_report_5.view.*

class CapacityReport5Dialog(
    context: Context,
    text1: String,
    text2: String,
    text3: String,
    text4: String
) : BaseDialog(context, R.layout.capacity_report_5) {
    init {
        view.apply {

            capacity_report5_close.setOnClickListener {
                close()
            }
            capacity_report5_text1.setText(text1)
            capacity_report5_text2.setText(text2)
            capacity_report5_text3.setText(text3)
            capacity_report5_text4.setText(text4)
        }
    }
}


