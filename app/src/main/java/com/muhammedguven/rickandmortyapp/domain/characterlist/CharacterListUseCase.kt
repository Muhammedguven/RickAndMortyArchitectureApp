package com.muhammedguven.rickandmortyapp.domain.characterlist

import com.muhammedguven.rickandmortyapp.data.remote.characterlist.CharacterListRepository
import com.muhammedguven.rickandmortyapp.domain.model.CharacterList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterListUseCase @Inject constructor(
    private val characterListRepository: CharacterListRepository,
    private val characterListMapper: CharacterListMapper
) {
    fun fetchCharacterList(): Flow<CharacterList?> {
        return characterListRepository
            .fetchCharacterList()
            .map {
                characterListMapper.mapFromResponse(it)
            }.flowOn(Dispatchers.IO)
    }
}