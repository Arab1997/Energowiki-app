package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.ui.screens.BasicsData
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_basics.view.*

class BasicsAdapter(private val listener: (BasicsData) -> Unit) :
    BaseAdapter<BasicsData>(R.layout.item_basics) {

    override fun bindViewHolder(holder: ViewHolder, data: BasicsData) {
        holder.itemView.apply {
            name.text = data.name

            setOnClickListener { listener.invoke(data) }
        }
    }
}