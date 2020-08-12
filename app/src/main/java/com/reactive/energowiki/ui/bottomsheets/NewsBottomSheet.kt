package com.reactive.energowiki.ui.bottomsheets

import com.reactive.energowiki.R
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.Constants
import com.reactive.energowiki.utils.bottomsheet.BottomSheetRoundedFragment
import com.reactive.energowiki.utils.extensions.loadImage
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
        back.loadImage(Constants.BASE_IMAGE_URL + data.image)
        header.setLangText(data.title_ru, data.title_uz)
        time.text = data.date.parseSdf2()
        content.setLangText(data.text_ru, data.text_uz)
    }
}