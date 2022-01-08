package com.muhammedguven.rickandmortyapp.data.remote.characterdetail

import com.muhammedguven.rickandmortyapp.data.remote.model.ResultsResponse

class CharacterDetailDataSource {

    interface Remote {
        suspend fun fetchCharacterDetail(id: Int): ResultsResponse
    }
}