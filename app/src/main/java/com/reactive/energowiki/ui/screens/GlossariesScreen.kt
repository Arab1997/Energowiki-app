package com.reactive.energowiki.ui.screens

import androidx.lifecycle.Observer
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.getLangText
import com.reactive.energowiki.ui.adapters.GlossariesAdapter
import com.reactive.energowiki.ui.bottomsheets.DetailBottomSheet
import com.reactive.energowiki.utils.common.RxEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.content_search.*
import kotlinx.android.synthetic.main.screen_glossaries.*
import java.util.concurrent.TimeUnit

class GlossariesScreen : BaseFragment(R.layout.screen_glossaries) {

    private lateinit var adapter: GlossariesAdapter
    private var data = arrayListOf<Documents>()
    override fun initialize() {

        close.setOnClickListener { finishFragment() }

        header.text = "Глоссарий"

        adapter = GlossariesAdapter {
            val bottomSheet = DetailBottomSheet.newInstance(it)
            removePreviousCallback({
                bottomSheet.show(childFragmentManager, "")
            })
        }
        recycler.adapter = adapter

        initSearch()
    }


    private fun initSearch() {

        RxEditText.getTextWatcherObservable(searchEdt).apply {
            debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe { text ->
                    if (text.length >= 2) {
                        val filtered = ArrayList(
                            data.filter { it.title_ru.toLowerCase().contains(text.toLowerCase()) })
                        adapter.setData(filtered)
                    } else if (text.isEmpty()) {
                        hideKeyboard()
                        adapter.setData(data)
                    }
                }
        }
    }

    override fun observe() {
        viewModel.glossaries.observe(viewLifecycleOwner, Observer {
            data = ArrayList(it.sortedBy { getLangText(it.title_ru, it.title_uz) })
            adapter.setData(data)
        })
    }
}