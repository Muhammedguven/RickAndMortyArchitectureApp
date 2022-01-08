package com.muhammedguven.rickandmortyapp.ui.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedguven.rickandmortyapp.common.Status
import com.muhammedguven.rickandmortyapp.common.StatusManager
import com.muhammedguven.rickandmortyapp.domain.characterlist.CharacterListUseCase
import com.muhammedguven.rickandmortyapp.domain.model.CharacterList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val useCase: CharacterListUseCase
) : ViewModel() {

    private val pageViewStateLiveData: MutableLiveData<CharacterListViewState> = MutableLiveData()
    private val statusManagerLiveData: MutableLiveData<StatusManager> = MutableLiveData()

    fun getPageViewStateLiveData(): LiveData<CharacterListViewState> = pageViewStateLiveData
    fun getStatusManagerLiveData(): LiveData<StatusManager> = statusManagerLiveData

    fun initializeViewModel() {
        onLoadingActive()
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            useCase
                .fetchCharacterList()
                .collect {
                    statusManager(it)
                }
        }
    }

    private fun onLoadingActive() {
        statusManagerLiveData.value = StatusManager(Status.LOADING)
    }

    private fun onErrorReceived() {
        statusManagerLiveData.value = StatusManager(Status.ERROR)
    }

    private fun onCharactersReady(characterList: CharacterList?) {
        if (characterList == null) return

        statusManagerLiveData.value = StatusManager(Status.CONTENT)
        pageViewStateLiveData.value = CharacterListViewState(characterList)
    }

    private fun statusManager(characterList: CharacterList?) {
        if (characterList?.results == null) {
            onErrorReceived()
        } else {
            onCharactersReady(characterList)
        }
    }

}