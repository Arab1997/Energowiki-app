package com.reactive.energowiki.ui.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.reactive.energowiki.R
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_glossary.view.*

class SpinnerAdapter(private val data: List<Documents>) : BaseAdapter() {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = parent!!.inflate(R.layout.item_glossary)
        view.name.setLangText(data[position].title_ru, data[position].title_uz)
        return view
    }

    override fun getItem(position: Int) = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = data.size

}