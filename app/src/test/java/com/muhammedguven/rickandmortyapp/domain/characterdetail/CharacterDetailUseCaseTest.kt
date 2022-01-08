package com.muhammedguven.rickandmortyapp.domain.characterdetail

import com.muhammedguven.rickandmortyapp.data.remote.characterdetail.CharacterDetailRepository
import com.muhammedguven.rickandmortyapp.data.remote.model.ResultsResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.Before

import org.junit.Test

class CharacterDetailUseCaseTest {

    @MockK
    lateinit var repository: CharacterDetailRepository

    @MockK
    lateinit var mapper: CharacterDetailMapper

    lateinit var useCase: CharacterDetailUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = CharacterDetailUseCase(
            repository,
            mapper
        )
    }

    @Test
    fun `given successful response, when fetchCharacterDetail called, then should return character`() {
        // Given
        val id = 1
        val response = ResultsResponse()

        every { repository.fetchCharacterDetail(id) } answers { flow { emit(response) } }

        // When
        useCase.fetchCharacterDetail(id)

        // Then
        verify { repository.fetchCharacterDetail(id) }
    }
}