package com.reactive.energowiki.ui.dialogs

import android.content.Context
import android.view.View
import android.widget.Toast
import com.reactive.energowiki.R
import kotlinx.android.synthetic.main.capacity_report_1.view.*

class CapacityReport1Dialog (context: Context, C1 :String,C2:String,C3:String,Ctotal:String, state:String) : BaseDialog(context, R.layout.capacity_report_1) {





        init {
            view.apply {

                capacity_report1_close.setOnClickListener {
                    close()
                }
                capacity_report1_text1.setText(state)
                capacity_report1_text2.setText(Ctotal)
                capacity_report1_text3.setText(C1)
                capacity_report1_text4.setText(C2)
                capacity_report1_text5.setText(C3)

                if(C1=="") capacity_report1_liner1.visibility= View.GONE
                if(C2=="") capacity_report1_liner2.visibility= View.GONE
                if(C3=="") capacity_report1_liner3.visibility= View.GONE
            }
        }
    }


