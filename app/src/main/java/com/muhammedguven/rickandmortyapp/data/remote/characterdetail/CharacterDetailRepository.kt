package com.muhammedguven.rickandmortyapp.data.remote.characterdetail

import com.muhammedguven.rickandmortyapp.data.remote.model.ResultsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterDetailRepository @Inject constructor(
    private val characterDetailDataSource: CharacterDetailDataSource.Remote
) {

    fun fetchCharacterDetail(id: Int): Flow<ResultsResponse> {
        return flow { emit(characterDetailDataSource.fetchCharacterDetail(id)) }
    }
}