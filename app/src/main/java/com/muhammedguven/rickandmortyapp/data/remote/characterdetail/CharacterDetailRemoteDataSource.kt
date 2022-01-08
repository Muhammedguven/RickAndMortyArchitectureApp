package com.muhammedguven.rickandmortyapp.data.remote.characterdetail

import com.muhammedguven.rickandmortyapp.data.remote.characterlist.CharacterListService
import com.muhammedguven.rickandmortyapp.data.remote.model.ResultsResponse
import retrofit2.HttpException
import javax.inject.Inject

class CharacterDetailRemoteDataSource @Inject constructor(
    private val service: CharacterListService
) : CharacterDetailDataSource.Remote  {

    override suspend fun fetchCharacterDetail(id: Int): ResultsResponse {
        return try {
            service.fetchCharacterDetail(id)
        } catch (e: HttpException){
            e.printStackTrace()
            ResultsResponse()
        }
    }
}