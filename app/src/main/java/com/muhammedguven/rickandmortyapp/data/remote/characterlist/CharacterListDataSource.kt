package com.muhammedguven.rickandmortyapp.data.remote.characterlist

import com.muhammedguven.rickandmortyapp.data.remote.model.CharacterListResponse

class CharacterListDataSource {

    interface Remote {
        suspend fun fetchCharacterList(): CharacterListResponse
    }
}