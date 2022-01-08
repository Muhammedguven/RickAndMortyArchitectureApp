package com.muhammedguven.rickandmortyapp.ui.characterlist

import com.muhammedguven.rickandmortyapp.domain.model.Results
import javax.inject.Inject

class CharacterItemViewState @Inject constructor(private val character: Results) {

    fun getName() = character.name

    fun getImage() = character.image

    fun getGender() = character.gender
}