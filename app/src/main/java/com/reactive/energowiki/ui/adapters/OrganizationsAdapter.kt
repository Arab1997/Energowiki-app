package com.reactive.energowiki.ui.adapters

import android.annotation.SuppressLint
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_links.view.*

class OrganizationsAdapter(private val listener: (Documents) -> Unit) :
    BaseAdapter<Documents>(R.layout.item_links) {

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(holder: ViewHolder, data: Documents) {
        holder.itemView.apply {

            data.let {
                setOnClickListener { _ -> listener.invoke(it) }

                name.setLangText(it.name_ru, it.name_uz)
            }
        }
    }
}


