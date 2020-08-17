package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_form.view.name

class Dvigatel1_1Adapter(private val listener: (Any) -> Unit) :
    BaseAdapter<Any>(R.layout.item_form) {

    override fun bindViewHolder(holder: ViewHolder, data: Any) {
        when (data) {
            1 -> holder.itemView.name.text = "I=U/R"
            2 -> holder.itemView.name.text = "I=P/U"
            3 -> holder.itemView.name.text = "I=√(P/R)"
            4 -> holder.itemView.name.text =  "U=I*R"
            5 -> holder.itemView.name.text = "U=P/I"
            6 -> holder.itemView.name.text =  "U=√(P*R)"
            7 -> holder.itemView.name.text = "P=I*U"
            8 -> holder.itemView.name.text= "P=I²*R"
            9 -> holder.itemView.name.text= "P=U²/R"
            10 -> holder.itemView.name.text= "R=U/I"
            11 -> holder.itemView.name.text=  "R=P/I²"
            12 -> holder.itemView.name.text=   "R=U²/P"
        }

        holder.itemView.setOnClickListener {
            listener.invoke(data)
        }
    }
}