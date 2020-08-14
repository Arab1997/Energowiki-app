package com.reactive.energowiki.utils

import com.reactive.energowiki.BuildConfig

object Constants {

    const val BASE_URL = BuildConfig.BASE_URL
    const val BASE_IMAGE_URL = BuildConfig.BASE_IMAGE_URL
    const val TIMEOUT = 20.toLong()
}

data class KeyValue(val key: String, val value: String)
