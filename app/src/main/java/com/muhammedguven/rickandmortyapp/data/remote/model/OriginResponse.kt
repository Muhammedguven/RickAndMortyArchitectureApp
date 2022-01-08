package com.muhammedguven.rickandmortyapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class OriginResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)