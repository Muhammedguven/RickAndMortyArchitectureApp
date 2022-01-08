package com.muhammedguven.rickandmortyapp.data.remote.characterlist

import com.muhammedguven.rickandmortyapp.data.remote.model.CharacterListResponse
import retrofit2.HttpException
import javax.inject.Inject

class CharacterListRemoteDataSource @Inject constructor(
    private val service: CharacterListService
) : CharacterListDataSource.Remote {

    override suspend fun fetchCharacterList(): CharacterListResponse {
        return try {
            service.fetchCharacterList()
        } catch (e: HttpException){
            e.printStackTrace()
            CharacterListResponse()
        }
    }

}