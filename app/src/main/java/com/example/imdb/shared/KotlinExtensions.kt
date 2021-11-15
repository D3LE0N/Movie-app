package com.example.imdb.shared

import com.google.gson.Gson

fun Any.getJson(): String {
    val value = this
    return Gson().toJson(value)
}

inline fun <reified T> String.getObject(): T {

    return Gson().fromJson<T>(this, T::class.java)
}