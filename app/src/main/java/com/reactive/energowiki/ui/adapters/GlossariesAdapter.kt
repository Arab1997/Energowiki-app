package com.reactive.energowiki.ui.adapters

import android.widget.SectionIndexer
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_glossary.view.*


class GlossariesAdapter(private val listener: (Documents) -> Unit) :
    BaseAdapter<Documents>(R.layout.item_glossary), SectionIndexer {

    private var sectionPositions = ArrayList<Int>(26)

    override fun bindViewHolder(holder: ViewHolder, data: Documents) {
        holder.itemView.apply {
            data.apply {
                setOnClickListener { listener.invoke(this) }
                name.setLangText(title_ru, title_uz)
            }
        }
    }

    override fun getSections(): Array<Any> {
        val sections = ArrayList<String>(26)
        for (i in items.indices) {
            val section = items[i].title_ru[0].toString().toUpperCase()
            if (!sections.contains(section)) {
                sections.add(section)
                sectionPositions.add(i)
            }
        }
        return sections.toArray(arrayOfNulls<String>(0))
    }

    override fun getSectionForPosition(position: Int): Int = 0

    override fun getPositionForSection(sectionIndex: Int): Int =
        sectionPositions[sectionIndex]
}