package com.reactive.energowiki.ui.bottomsheets

import com.reactive.energowiki.R
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.bottomsheet.BottomSheetRoundedFragment
import kotlinx.android.synthetic.main.bottomsheet_detail.*

class DetailBottomSheet : BottomSheetRoundedFragment(R.layout.bottomsheet_detail) {

    companion object {
        private lateinit var data: Documents
        fun newInstance(it: Documents): DetailBottomSheet {
            data = it
            return DetailBottomSheet()
        }
    }

    override fun initialize() {

        close.setOnClickListener { dismiss() }
        header.setLangText(data.title_ru, data.title_uz)
        content.setLangText(data.text_ru, data.text_uz)
    }
}