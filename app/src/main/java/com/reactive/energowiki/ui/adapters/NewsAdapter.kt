package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.utils.common.ViewHolder

class NewsAdapter(private val listener: (Any) -> Unit) : BaseAdapter<Any>(R.layout.item_news) {
    override fun bindViewHolder(holder: ViewHolder, data: Any) {

    }
}


