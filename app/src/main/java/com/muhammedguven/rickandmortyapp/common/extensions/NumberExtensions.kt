package com.muhammedguven.rickandmortyapp.common.extensions

inline fun <reified T : Number> T?.orZero(): T = this ?: when (T::class) {
    Double::class -> 0.0 as T
    Float::class -> 0f as T
    Long::class -> 0L as T
    else -> 0 as T
}