package com.muhammedguven.rickandmortyapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterListResponse(
    @SerializedName("info")
    val info: InfoResponse? = null,
    @SerializedName("results")
    val results: List<ResultsResponse?>? = null
)