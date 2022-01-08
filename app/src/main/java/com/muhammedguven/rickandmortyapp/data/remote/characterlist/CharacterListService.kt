package com.muhammedguven.rickandmortyapp.data.remote.characterlist

import com.muhammedguven.rickandmortyapp.data.remote.model.CharacterListResponse
import com.muhammedguven.rickandmortyapp.data.remote.model.ResultsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterListService {

    @GET("character")
    suspend fun fetchCharacterList(): CharacterListResponse

    @GET("character/{id}")
    suspend fun fetchCharacterDetail(@Path("id") id:Int): ResultsResponse
}

