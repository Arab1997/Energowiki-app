package com.reactive.energowiki.ui.dialogs.pueReports

import android.content.Context
import com.reactive.energowiki.R
import com.reactive.energowiki.ui.dialogs.BaseDialog
import kotlinx.android.synthetic.main.pue_report_screen1.view.*

class PueReport1(
    context: Context,
    text1: String,
    text2: String,
    text3: String,
    text4: String,
    text5: String,
    text6: String,
    text7: String,
    text8: String
) : BaseDialog(context, R.layout.pue_report_screen1) {
    init {
        view.apply {

            reulst_btn.setOnClickListener {
                close()
            }
            tok_result.text = text1
            material_result.text = text2.toString()
            secheniya_result.text = text3
            temp_result.text = text4
            tempp_result.text = text5
            count_result.text = text6
            koefsient_result.text = text7
            svoy_koefsient_result.text = text8
        }
    }
}


