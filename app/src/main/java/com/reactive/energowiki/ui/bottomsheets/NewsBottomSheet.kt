package com.reactive.energowiki.ui.bottomsheets

import com.reactive.energowiki.R
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.bottomsheet.BottomSheetRoundedFragment
import com.reactive.energowiki.utils.extensions.parseSdf2
import kotlinx.android.synthetic.main.bottomsheet_news.*

class NewsBottomSheet : BottomSheetRoundedFragment(R.layout.bottomsheet_news) {

    companion object {
        private lateinit var data: Documents
        fun newInstance(it: Documents): NewsBottomSheet {
            data = it
            return NewsBottomSheet()
        }
    }

    override fun initialize() {

        close.setOnClickListener { dismiss() }

        header.setLangText(data.title_uz, data.title_uz)
        time.text = data.date?.parseSdf2()
        content.setLangText(data.text_uz, data.text_ru)
    }
}