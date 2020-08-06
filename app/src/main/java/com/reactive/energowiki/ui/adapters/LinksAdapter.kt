package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.network.Links
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_links.view.*

class LinksAdapter(private val listener: (Links) -> Unit) :
    BaseAdapter<Links>(R.layout.item_links) {

    override fun bindViewHolder(holder: ViewHolder, data: Links) {
        holder.itemView.apply {

            data.let {
                setOnClickListener { _ -> listener.invoke(it) }
                name.setLangText(it.title_uz, it.title_ru)
            }
        }
    }
}


