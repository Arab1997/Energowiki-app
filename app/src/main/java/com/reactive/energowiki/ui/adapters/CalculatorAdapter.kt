package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_calculator.view.*

class CalculatorAdapter(private val listener: (Any) -> Unit): BaseAdapter<Any>(R.layout.item_calculator){
    override fun bindViewHolder(holder: ViewHolder, data: Any) {
        when (data) {
            1 -> holder.itemView.name.text = "Удельное сопротивление и проводимость"
            2 -> holder.itemView.name.text = "Длина проводника"
            3 -> holder.itemView.name.text = "Потери напряжения"
            4 -> holder.itemView.name.text = "Закон Ома постоянного тока"
            5 -> holder.itemView.name.text = "Закон Ома переменного тока"
            6 -> holder.itemView.name.text = "Сопротивление проводника"
            7 -> holder.itemView.name.text = "Площадь поперечного сечения"
        }
        holder.itemView.setOnClickListener {
            listener.invoke(data)
        }
    }
}