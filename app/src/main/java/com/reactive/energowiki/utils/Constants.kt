package com.reactive.energowiki.utils

import com.reactive.energowiki.BuildConfig

object Constants {

    const val BASE_URL = BuildConfig.BASE_URL
    const val TIMEOUT = 10.toLong()

    const val IS_REGISTER_KEY = "register"
}

data class KeyValue(val key: String, val value: String)
