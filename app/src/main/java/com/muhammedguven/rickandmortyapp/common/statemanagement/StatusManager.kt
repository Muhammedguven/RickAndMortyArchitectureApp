package com.muhammedguven.rickandmortyapp.common

class StatusManager(
    val status: Status
) {

    fun isLoadingVisible() = status == Status.LOADING

    fun isContentVisible() = status == Status.CONTENT

    fun isRetryButtonVisible() = status == Status.ERROR
}

enum class Status{
    LOADING,
    ERROR,
    CONTENT
}