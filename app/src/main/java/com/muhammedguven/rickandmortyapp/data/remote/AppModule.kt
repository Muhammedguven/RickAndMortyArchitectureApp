package com.muhammedguven.rickandmortyapp.data.remote

import com.muhammedguven.rickandmortyapp.data.remote.characterdetail.CharacterDetailDataSource
import com.muhammedguven.rickandmortyapp.data.remote.characterdetail.CharacterDetailRemoteDataSource
import com.muhammedguven.rickandmortyapp.data.remote.characterlist.CharacterListDataSource
import com.muhammedguven.rickandmortyapp.data.remote.characterlist.CharacterListRemoteDataSource
import com.muhammedguven.rickandmortyapp.data.remote.characterlist.CharacterListService
import com.muhammedguven.rickandmortyapp.data.remote.model.CharacterListResponse
import com.muhammedguven.rickandmortyapp.data.remote.model.ResultsResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    private val BASE_URL = "https://rickandmortyapi.com/api/"

    @Singleton
    @Provides
    fun provideCharactersService(): CharacterListService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CharacterListService::class.java)
    }

    @Singleton
    @Provides
    fun remoteProvider(): CharacterListDataSource.Remote {
        return CharacterListRemoteDataSource(provideCharactersService())
    }

    @Singleton
    @Provides
    fun remoteDetailProvider(): CharacterDetailDataSource.Remote {
        return CharacterDetailRemoteDataSource(provideCharactersService())
    }

    suspend fun fetchCharacters(): CharacterListResponse {
        return provideCharactersService().fetchCharacterList()
    }

    suspend fun fetchCharacterDetail(id: Int): ResultsResponse {
        return provideCharactersService().fetchCharacterDetail(id)
    }
}