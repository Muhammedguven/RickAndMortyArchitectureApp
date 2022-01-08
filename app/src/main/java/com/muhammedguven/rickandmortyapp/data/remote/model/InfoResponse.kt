package com.muhammedguven.rickandmortyapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("pages")
    val pages: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("prev")
    val prev: String? = null
)