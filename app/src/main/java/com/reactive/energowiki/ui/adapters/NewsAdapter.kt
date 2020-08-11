package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.Constants
import com.reactive.energowiki.utils.common.ViewHolder
import com.reactive.energowiki.utils.extensions.loadImage
import com.reactive.energowiki.utils.extensions.parseSdf2
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private val listener: (Documents) -> Unit) :
    BaseAdapter<Documents>(R.layout.item_news) {

    override fun bindViewHolder(holder: ViewHolder, data: Documents) {
        holder.itemView.apply {
            setOnClickListener { listener.invoke(data) }

            name.setLangText(data.title_ru, data.title_uz)
            time.text = data.date.parseSdf2()
            img.loadImage(Constants.BASE_IMAGE_URL + data.image)
        }
    }
}


