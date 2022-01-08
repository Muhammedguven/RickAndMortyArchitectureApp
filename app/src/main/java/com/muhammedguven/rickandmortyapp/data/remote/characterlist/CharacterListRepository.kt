package com.muhammedguven.rickandmortyapp.data.remote.characterlist

import com.muhammedguven.rickandmortyapp.data.remote.model.CharacterListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterListRepository @Inject constructor(
    private val characterListDataSource: CharacterListDataSource.Remote
) {
    fun fetchCharacterList(): Flow<CharacterListResponse> {
        return flow { emit(characterListDataSource.fetchCharacterList()) }
    }

}