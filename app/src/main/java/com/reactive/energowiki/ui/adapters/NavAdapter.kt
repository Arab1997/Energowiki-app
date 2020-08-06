package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.ui.screens.HomeData
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_nav.view.*

class NavAdapter(private var listener: (HomeData) -> Unit) :
    BaseAdapter<HomeData>(R.layout.item_nav) {

    override fun bindViewHolder(holder: ViewHolder, data: HomeData) {
        holder.itemView.apply {
            setOnClickListener { listener.invoke(data) }
            name.text = data.name
        }
    }

}