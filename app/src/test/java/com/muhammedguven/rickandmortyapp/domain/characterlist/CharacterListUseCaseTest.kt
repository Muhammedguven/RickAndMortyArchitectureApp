package com.muhammedguven.rickandmortyapp.domain.characterlist

import com.google.common.truth.Truth
import com.google.gson.annotations.SerializedName
import com.muhammedguven.rickandmortyapp.data.remote.characterlist.CharacterListRepository
import com.muhammedguven.rickandmortyapp.data.remote.model.CharacterListResponse
import com.muhammedguven.rickandmortyapp.data.remote.model.InfoResponse
import com.muhammedguven.rickandmortyapp.domain.model.CharacterList
import com.muhammedguven.rickandmortyapp.domain.model.Info
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test


class CharacterListUseCaseTest{

    @MockK
    lateinit var repository: CharacterListRepository

    @MockK
    lateinit var mapper: CharacterListMapper

    lateinit var useCase: CharacterListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = CharacterListUseCase(
            repository,
            mapper
        )
    }

    @Test
    fun `given successful response, when fetchCharacterList called, then should return character list`() {
        // Given
        val response = CharacterListResponse(info = InfoResponse(), results = listOf())

        every { repository.fetchCharacterList() } answers { flow { emit(response) } }

        // When
        useCase.fetchCharacterList()

        // Then
        verify { repository.fetchCharacterList() }
    }
}