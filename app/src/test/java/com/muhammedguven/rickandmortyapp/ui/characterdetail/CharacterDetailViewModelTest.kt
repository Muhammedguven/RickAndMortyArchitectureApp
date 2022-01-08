package com.muhammedguven.rickandmortyapp.ui.characterdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.muhammedguven.rickandmortyapp.utils.TestCoroutineRule
import com.muhammedguven.rickandmortyapp.common.Status
import com.muhammedguven.rickandmortyapp.common.StatusManager
import com.muhammedguven.rickandmortyapp.domain.characterdetail.CharacterDetailUseCase
import com.muhammedguven.rickandmortyapp.domain.model.Location
import com.muhammedguven.rickandmortyapp.domain.model.Origin
import com.muhammedguven.rickandmortyapp.domain.model.Results
import com.muhammedguven.rickandmortyapp.utils.test
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharacterDetailViewModelTest{
    @MockK
    lateinit var useCase: CharacterDetailUseCase

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @InjectMockKs
    lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CharacterDetailViewModel(
            useCase
        )
    }

    @Test
    fun `given success state, when fetchCharacterDetail called, then update live data for content status`() {
        // Given
        val mockObserver = viewModel.getStatusManagerLiveData().test()
        val expectedValue = StatusManager(Status.CONTENT).status

        every { useCase.fetchCharacterDetail(1) } returns flow { emit(provideResult()) }


        // When
        viewModel.fetchCharacterDetail(1)

        // Then
        Truth.assertThat(mockObserver.getValues().last().status).isEqualTo(expectedValue)
    }

    @Test
    fun `given success state, when fetchCharacterDetail called, then update page live data for result`() {
        // Given
        val mockObserver = viewModel.getDetailPageViewStateLiveData().test()
        val response = provideResult()
        every { useCase.fetchCharacterDetail(1) } returns flow { emit(response) }


        // When
        viewModel.fetchCharacterDetail(1)

        // Then
        Truth.assertThat(mockObserver.getValues().last().character).isEqualTo(provideResult())
    }

    @Test
    fun `given error state, when fetchCharacterDetail called, then update live data for error status`() {
        // Given
        val mockObserver = viewModel.getStatusManagerLiveData().test()
        val expectedValue = StatusManager(Status.ERROR).status

        every { useCase.fetchCharacterDetail(1) } returns flow { emit(null) }


        // When
        viewModel.fetchCharacterDetail(1)

        // Then
        Truth.assertThat(mockObserver.getValues().last().status).isEqualTo(expectedValue)
    }

    fun provideResult() : Results {
        return Results(
                id = 1,
                name = "asd",
                status = "sad",
                species = "sad",
                type = "sad",
                gender = "sad",
                origin = Origin("sad", "www.google.com"),
                location = Location("loc", "www.google.com"),
                image = "www.google.com",
                episode = listOf(),
                url = "www.google.com",
                created = "asdasdsdf"
            )
    }
}