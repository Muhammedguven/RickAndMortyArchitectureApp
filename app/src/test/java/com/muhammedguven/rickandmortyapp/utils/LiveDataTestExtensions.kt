package com.muhammedguven.rickandmortyapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.mockk.spyk

fun <T> LiveData<T>.test(): TestObserver<T> {
    val spiedObserver = spyk(TestObserver<T>())
    observeForever(spiedObserver)
    return spiedObserver
}

class TestObserver<T> : Observer<T> {
    private val values = mutableListOf<T>()

    fun getValues(): List<T> = values

    override fun onChanged(t: T) {
        values.add(t)
    }
}
