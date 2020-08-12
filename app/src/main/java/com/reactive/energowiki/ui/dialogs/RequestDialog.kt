package com.reactive.energowiki.ui.dialogs

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.network.StatusResp
import com.reactive.energowiki.ui.adapters.SpinnerAdapter
import com.reactive.energowiki.utils.common.TextWatcherInterface
import com.reactive.energowiki.utils.extensions.*
import kotlinx.android.synthetic.main.dialog_request.*

class RequestDialog : BaseFragment(R.layout.dialog_request) {

    private var isRequested = false
    private var selectedCategory = 0
    private var categories = arrayListOf<Documents>()

    override fun initialize() {

        dim.setOnClickListener { finishFragment() }
        close.setOnClickListener { finishFragment() }

        check()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCategory = position
            }

        }
        name.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })

        address.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })

        info.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })

        phone.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })

        sent.setOnClickListener {
            it.blockClickable()
            isRequested = true
            showProgress(true)
            viewModel.sendApplication(
                name.text.toString(),
                address.text.toString(),
                "+9989${phone.rawText}",
                info.text.toString(),
                categories[selectedCategory].id
            )
        }
    }

    private fun check() {
        sent.disable()

        if (name.text.toString().isEmpty()
            || address.text.toString().isEmpty()
            || info.text.toString().isEmpty()
            || phone.rawText.toString().length < 9
        ) return

        sent.enable()
    }

    override fun observe() {

        viewModel.data.observe(viewLifecycleOwner, Observer {
            if (isRequested && it is StatusResp) {
                showProgress(false)
                toast(requireContext(), "Отправлено")
                finishFragment()
            }
        })

        viewModel.categoryApplication.observe(viewLifecycleOwner, Observer {
            categories = ArrayList(it)
            spinner.adapter = SpinnerAdapter(categories)
        })
    }
}
