package com.muhammedguven.rickandmortyapp.domain.characterdetail

import com.muhammedguven.rickandmortyapp.data.remote.characterdetail.CharacterDetailRepository
import com.muhammedguven.rickandmortyapp.domain.model.Results
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(
    private val characterDetailRepository: CharacterDetailRepository,
    private val characterDetailMapper: CharacterDetailMapper
) {

    fun fetchCharacterDetail(id: Int): Flow<Results?> {
        return characterDetailRepository
            .fetchCharacterDetail(id)
            .map {
                characterDetailMapper.mapFromResponse(it)
            }
    }
}