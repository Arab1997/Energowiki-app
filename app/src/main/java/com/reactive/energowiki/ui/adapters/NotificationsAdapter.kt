package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_notifications.view.*

class NotificationsAdapter : BaseAdapter<String>(R.layout.item_notifications) {

    override fun bindViewHolder(holder: ViewHolder, data: String) {
        holder.itemView.apply {
            name.text = data
            close.setOnClickListener {
                items.remove(data)
                notifyDataSetChanged()
            }
        }
    }

}