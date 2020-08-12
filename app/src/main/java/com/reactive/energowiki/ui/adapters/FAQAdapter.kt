package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_faq.view.*

class FAQAdapter(private val listener: (Documents) -> Unit) :
    BaseAdapter<Documents>(R.layout.item_faq) {

    override fun bindViewHolder(holder: ViewHolder, data: Documents) {

        holder.itemView.apply {
            setOnClickListener { listener.invoke(data) }

            name.setLangText(data.title_ru, data.title_uz)
        }

    }
}


