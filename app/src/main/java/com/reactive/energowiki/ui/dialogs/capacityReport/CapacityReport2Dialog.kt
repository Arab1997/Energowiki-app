package com.reactive.energowiki.ui.dialogs.capacityReport

import android.content.Context
import com.reactive.energowiki.R
import com.reactive.energowiki.ui.dialogs.BaseDialog
import kotlinx.android.synthetic.main.capacity_report_2.view.*

class CapacityReport2Dialog(
    context: Context,
    Fi: String,
    C: String,
    Xc: String
) : BaseDialog(context, R.layout.capacity_report_2) {





    init {
        view.apply {

            capacity_report2_close.setOnClickListener {
                close()
            }
            capacity_report2_text1.setText(Xc)
            capacity_report2_text2.setText(Fi)
            capacity_report2_text3.setText(C)
        }
    }
}


