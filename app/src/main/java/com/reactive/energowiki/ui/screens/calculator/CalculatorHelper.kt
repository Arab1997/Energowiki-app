package com.reactive.energowiki.ui.screens.calculator

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner

fun Spinner.onItemSelected(listener: (position: Int) -> Unit){

    onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            listener.invoke(position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
    }
}

fun EditText.onTextChanged(listener: (s: CharSequence?) -> Unit){
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            listener.invoke(s)
        }

    })
}