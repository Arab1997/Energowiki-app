package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.ui.screens.calculator.CalculatorData
import com.reactive.energowiki.ui.screens.calculator.CalculatorMenus
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_calculator.view.*

class CalculatorAdapter(private val type: CalculatorData, private val listener: (Any) -> Unit): BaseAdapter<Any>(R.layout.item_calculator){
    override fun bindViewHolder(holder: ViewHolder, data: Any) {
        when(type.type){
            CalculatorMenus.BASICS -> when (data) {
                1 -> holder.itemView.name.text = "Удельное сопротивление и проводимость"
                2 -> holder.itemView.name.text = "Длина проводника"
                3 -> holder.itemView.name.text = "Потери напряжения"
                4 -> holder.itemView.name.text = "Закон Ома постоянного тока"
                5 -> holder.itemView.name.text = "Закон Ома переменного тока"
                6 -> holder.itemView.name.text = "Сопротивление проводника"
                7 -> holder.itemView.name.text = "Площадь поперечного сечения"
            }
            CalculatorMenus.CAPACITY -> when (data) {
                1 -> holder.itemView.name.text = "Соединение конденсаторов"
                2 -> holder.itemView.name.text = "Сопротивление емкости переменному току"
                3 -> holder.itemView.name.text = "Конденсатор для пуска трехфазного двигателя в однофазной сети"
                4 -> holder.itemView.name.text = "Емкостной делитель напряжение"
                5 -> holder.itemView.name.text = "Заряд и энергия корденсатора"
                6 -> holder.itemView.name.text = "Заряд и энергия конденсатора"
                7 -> holder.itemView.name.text = "Декодировать в буквенно-цифровую маркировку"
                8 -> holder.itemView.name.text = "Кодировать в буквенно-цифровую маркировку"
                9 -> holder.itemView.name.text = "Гасящий конденсатор"
            }
        }

        holder.itemView.setOnClickListener {
            listener.invoke(data)
        }
    }
}