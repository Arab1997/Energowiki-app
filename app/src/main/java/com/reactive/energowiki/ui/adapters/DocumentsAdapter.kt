package com.reactive.energowiki.ui.adapters

import android.annotation.SuppressLint
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.common.ViewHolder
import com.reactive.energowiki.utils.extensions.parseSdf1
import kotlinx.android.synthetic.main.item_norm_doc.view.*

class DocumentsAdapter(private val listener: (Documents) -> Unit) :
    BaseAdapter<Documents>(R.layout.item_norm_doc) {

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(holder: ViewHolder, data: Documents) {
        holder.itemView.apply {
            setOnClickListener { listener.invoke(data) }

            text.setLangText(data.title_uz, data.title_ru)
            number.text = "№ ${data.number}"
            time.text = data.date?.parseSdf1()
        }
    }
}


