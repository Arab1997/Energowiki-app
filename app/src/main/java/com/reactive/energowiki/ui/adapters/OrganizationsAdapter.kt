package com.reactive.energowiki.ui.adapters

import android.annotation.SuppressLint
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.network.Organizations
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_links.view.*

class OrganizationsAdapter(private val listener: (Organizations) -> Unit) :
    BaseAdapter<Organizations>(R.layout.item_links) {

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(holder: ViewHolder, data: Organizations) {
        holder.itemView.apply {

            data.let {
                setOnClickListener { _ -> listener.invoke(it) }

                name.setLangText(it.name_uz, it.name_ru)
            }
        }
    }
}


