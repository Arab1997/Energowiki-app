package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.ui.screens.HomeData
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_home_menu.view.*

class MenuAdapter(private val listener: (HomeData) -> Unit) :
    BaseAdapter<HomeData>(R.layout.item_home_menu) {

    override fun bindViewHolder(holder: ViewHolder, data: HomeData) {
        holder.itemView.apply {
            name.text = data.name
            icon.setImageResource(data.icon)
            setOnClickListener { listener.invoke(data) }
        }
    }
}


