package com.reactive.energowiki.ui.dialogs

import androidx.lifecycle.Observer
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.network.StatusResp
import com.reactive.energowiki.utils.common.TextWatcherInterface
import com.reactive.energowiki.utils.extensions.blockClickable
import com.reactive.energowiki.utils.extensions.disable
import com.reactive.energowiki.utils.extensions.enable
import com.reactive.energowiki.utils.extensions.toast
import kotlinx.android.synthetic.main.dialog_request.*

class RequestDialog : BaseFragment(R.layout.dialog_request) {

    private var isRequested = false
    override fun initialize() {

        dim.setOnClickListener { finishFragment() }
        close.setOnClickListener { finishFragment() }

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
                "" // todo
            )
        }
    }

    private fun check() {
        sent.disable()

        if (name.text.toString().isEmpty()
            || address.text.toString().isEmpty()
            || info.text.toString().isEmpty()
            || phone.rawText.toString().length < 9
        ) {
            return
        }

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
    }
}