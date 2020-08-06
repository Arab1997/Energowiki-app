package com.reactive.energowiki.utils.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

@SuppressLint("SimpleDateFormat")
val sdf1 = SimpleDateFormat("dd.MM.yyyy")

@SuppressLint("SimpleDateFormat")
val sdf2 = SimpleDateFormat("HH:mm dd.MM.yyyy")

fun String.parseSdf1(): String = sdf1.format(sdf.parse(this)!!)

fun String.parseSdf2(): String = sdf2.format(sdf.parse(this)!!)

@SuppressLint("SimpleDateFormat")
fun utcToLocal(utcTime: String): Long {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val value = formatter.parse(utcTime)!!
    return value.time
}