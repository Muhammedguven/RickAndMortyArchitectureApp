package com.muhammedguven.rickandmortyapp.ui.characterlist

import com.muhammedguven.rickandmortyapp.domain.model.CharacterList
import com.muhammedguven.rickandmortyapp.domain.model.Results

class CharacterListViewState(private val characters: CharacterList){

    fun getCharacterResults(): List<Results> {
        return characters.results
    }
}