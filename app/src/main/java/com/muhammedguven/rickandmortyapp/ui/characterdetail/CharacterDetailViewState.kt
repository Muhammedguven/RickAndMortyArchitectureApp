package com.muhammedguven.rickandmortyapp.ui.characterdetail

import com.muhammedguven.rickandmortyapp.domain.model.Results

class CharacterDetailViewState (val character: Results) {

    fun getImage() = character.image

    fun getCreated() = character.created

    fun getType() = character.type

    fun getGender() = character.gender

    fun getName() = character.name
}