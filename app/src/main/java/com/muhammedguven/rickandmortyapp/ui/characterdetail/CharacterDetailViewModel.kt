package com.muhammedguven.rickandmortyapp.ui.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedguven.rickandmortyapp.common.Status
import com.muhammedguven.rickandmortyapp.common.StatusManager
import com.muhammedguven.rickandmortyapp.domain.characterdetail.CharacterDetailUseCase
import com.muhammedguven.rickandmortyapp.domain.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val useCase: CharacterDetailUseCase,
) : ViewModel() {

    private val detailPageViewStateLiveData: MutableLiveData<CharacterDetailViewState> = MutableLiveData()
    private val statusManagerLiveData: MutableLiveData<StatusManager> = MutableLiveData()

    fun getDetailPageViewStateLiveData(): LiveData<CharacterDetailViewState> = detailPageViewStateLiveData
    fun getStatusManagerLiveData(): LiveData<StatusManager> = statusManagerLiveData

    fun fetchCharacterDetail(id: Int) {
        onLoadingActive()
        viewModelScope.launch() {
            useCase
                .fetchCharacterDetail(id)
                .collect { statusManager(it) }
        }
    }

    private fun onLoadingActive(){
        statusManagerLiveData.value = StatusManager(Status.LOADING)
    }

    private fun onErrorReceived() {
        statusManagerLiveData.value = StatusManager(Status.ERROR)
    }

    private fun statusManager(character: Results?) {
        if (character == null) {
            onErrorReceived()
        } else {
            onCharactersReady(character)
        }
    }

    private fun onCharactersReady(character: Results) {
        statusManagerLiveData.value = StatusManager(Status.CONTENT)
        detailPageViewStateLiveData.postValue(CharacterDetailViewState(character))
    }

}