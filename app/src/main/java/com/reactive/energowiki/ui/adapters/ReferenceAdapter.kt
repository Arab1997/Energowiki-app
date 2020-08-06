package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_reference.view.*

class ReferenceAdapter(private val listener: (Documents) -> Unit) :
    BaseAdapter<Documents>(R.layout.item_reference) {
    override fun bindViewHolder(holder: ViewHolder, data: Documents) {
        holder.itemView.apply {
            data.let {
                setOnClickListener { _ -> listener.invoke(it) }
                name.setLangText(it.title_uz, it.title_ru)
            }
        }

    }
}


