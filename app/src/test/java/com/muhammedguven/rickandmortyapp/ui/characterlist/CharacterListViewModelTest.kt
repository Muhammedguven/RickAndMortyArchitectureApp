package com.muhammedguven.rickandmortyapp.ui.characterlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.muhammedguven.rickandmortyapp.utils.TestCoroutineRule
import com.muhammedguven.rickandmortyapp.common.Status
import com.muhammedguven.rickandmortyapp.common.StatusManager
import com.muhammedguven.rickandmortyapp.domain.characterlist.CharacterListUseCase
import com.muhammedguven.rickandmortyapp.domain.model.CharacterList
import com.muhammedguven.rickandmortyapp.domain.model.Info
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
class CharacterListViewModelTest{

    @MockK
    lateinit var useCase: CharacterListUseCase

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @InjectMockKs
    lateinit var viewModel: CharacterListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CharacterListViewModel(
            useCase
        )
    }

    @Test
    fun `given success state, when initializeViewModel called, then update live data for content status`() {
        // Given
        val mockObserver = viewModel.getStatusManagerLiveData().test()
        val expectedValue = StatusManager(Status.CONTENT).status

        every { useCase.fetchCharacterList() } returns flow { emit(CharacterList(info = Info(0,0,"next","prev"), results = listOf())) }


        // When
        viewModel.initializeViewModel()

        // Then
        Truth.assertThat(mockObserver.getValues().last().status).isEqualTo(expectedValue)
    }

    @Test
    fun `given success state, when initializeViewModel called, then update page live data for results`() {
        // Given
        val mockObserver = viewModel.getPageViewStateLiveData().test()
        val results = provideResults()
        val response = CharacterList(info = Info(0,0,"next","prev"), results = results)
        every { useCase.fetchCharacterList() } returns flow { emit(response) }


        // When
        viewModel.initializeViewModel()

        // Then
        Truth.assertThat(mockObserver.getValues().last().getCharacterResults().first()).isEqualTo(results.first())
    }

    @Test
    fun `given error state, when initializeViewModel called, then update live data for error status`() {
        // Given
        val mockObserver = viewModel.getStatusManagerLiveData().test()
        val expectedValue = StatusManager(Status.ERROR).status

        every { useCase.fetchCharacterList() } returns flow { emit(null) }


        // When
        viewModel.initializeViewModel()

        // Then
        Truth.assertThat(mockObserver.getValues().last().status).isEqualTo(expectedValue)
    }

    fun provideResults() : List<Results> {
        return listOf<Results>(
            Results(
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
        )
    }


}