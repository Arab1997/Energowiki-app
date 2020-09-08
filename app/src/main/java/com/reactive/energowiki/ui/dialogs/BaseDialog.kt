package com.reactive.energowiki.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import com.reactive.energowiki.R

abstract class BaseDialog(context: Context, @LayoutRes resId: Int) {
    private val dialog = AlertDialog.Builder(context, R.style.DialogTheme).create()
    internal val view = LayoutInflater.from(context).inflate(resId, null, false)

    init {
        dialog.setView(view)
    }

    fun show() {
        dialog.show()
    }

    fun close() {
        dialog.dismiss()
    }
}

