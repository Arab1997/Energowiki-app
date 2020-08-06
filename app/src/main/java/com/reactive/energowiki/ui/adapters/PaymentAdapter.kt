package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.ui.screens.HomeData
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_payment.view.*

class PaymentAdapter(private val listener: (HomeData) -> Unit) :
    BaseAdapter<HomeData>(R.layout.item_payment) {

    override fun bindViewHolder(holder: ViewHolder, data: HomeData) {
        holder.itemView.apply {
            icon.setImageResource(data.icon)
            name.text = data.name
            setOnClickListener { listener.invoke(data) }
        }
    }
}