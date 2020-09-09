package com.reactive.energowiki.ui.dialogs.capacityReport

import android.content.Context
import android.view.View
import com.reactive.energowiki.R
import com.reactive.energowiki.ui.dialogs.BaseDialog
import kotlinx.android.synthetic.main.capacity_report_3.view.*

class CapacityReport3Dialog(
    context: Context,
    text1: String,
    text3: String,
    text4: String,
    text5: String,
    text6: String,
    text7: String,
    text8: String,
    text9: String,
    text10: String,
    text11: String,
    text12: String,
    isSelectedPower: Boolean
) : BaseDialog(context, R.layout.capacity_report_3) {





    init {
        view.apply {

            capacity_report3_close.setOnClickListener {
                close()
            }
            capacity_report3_text1.setText(text1)
            //capacity_report3_text2.setText(text2)
            capacity_report3_text3.setText(text3)
            capacity_report3_text4.setText(text4)
            capacity_report3_text5.setText(text5)
            capacity_report3_text6.setText(text6)
            capacity_report3_text7.setText(text7)
            capacity_report3_text8.setText(text8)
            capacity_report3_text9.setText(text9)
            capacity_report3_text10.setText(text10)
            capacity_report3_text11.setText(text11)
            capacity_report3_text12.setText(text12)
            if (isSelectedPower) capacity_report3_liner.visibility = View.VISIBLE else

                capacity_report3_liner.visibility = View.GONE

            if (text1.equals("Треугольник")) {


                capacity_report3_text2.setText(
                    "C Рабочий = 3 / (2 * ( I / ( 2πφ * U))) * 1000000\n" +
                            "C Пусковой = C Рабочий * (2.3....3)\n" +
                            "Минимальное напряжение = Напряжение * 1.15"
                )
                capacity_report3_image.setBackgroundResource(R.drawable.report_picture4)

            } else if (text1.equals("Звезда")) {
                capacity_report3_text2.setText(
                    "C Рабочий = √3 / 2 * (I / (2πφ * U)) * 1000000\n" +
                            "C Пусковой = C Рабочий * (2.3....3)\n" +
                            "Минимальное напряжение = Напряжение * 1.15"
                )
                capacity_report3_image.setBackgroundResource(R.drawable.report_picture3)
            } else if (text1.equals("Неполная звезда (а)")) {
                capacity_report3_text2.setText(
                    "C Рабочий = 1 / 2 * (I / (2πφ * U)) * 1000000\n" +
                            "C Пусковой = C Рабочий * (2.3....3)\n" +
                            "Минимальное напряжение = Напряжение * 2.2"
                )
                capacity_report3_image.setBackgroundResource(R.drawable.report_picture1)
            } else if (text1.equals("Неполная звезда (б)")) {
                capacity_report3_text2.setText(
                    "C Рабочий = √3 / 2 * (I / (2πφ * U)) * 1000000\n" +
                            "C Пусковой = C Рабочий * (2.3....3)\n" +
                            "Минимальное напряжение = Напряжение * 2.22"
                )
                capacity_report3_image.setBackgroundResource(R.drawable.report_picture2)
            }
        }
    }
}


