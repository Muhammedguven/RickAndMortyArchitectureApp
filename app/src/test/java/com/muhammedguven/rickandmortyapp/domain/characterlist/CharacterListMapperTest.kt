package com.muhammedguven.rickandmortyapp.domain.characterlist

import com.google.common.truth.Truth
import com.muhammedguven.rickandmortyapp.data.remote.model.CharacterListResponse
import com.muhammedguven.rickandmortyapp.data.remote.model.InfoResponse
import com.muhammedguven.rickandmortyapp.data.remote.model.ResultsResponse
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test

class CharacterListMapperTest {

    lateinit var mapper: CharacterListMapper

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        mapper = CharacterListMapper()
    }

    @Test
    fun `given response null, when mapFromResponse called, then it returns null`(){
        //given
        val response = null

        //when
        val actualResult = mapper.mapFromResponse(response)

        //then
        Truth.assertThat(actualResult).isNull()
    }

    @Test
    fun `given results null, when mapFromResponse called, then it returns null`(){
        //given
        val results = null
        val response = CharacterListResponse(InfoResponse(), results = results)

        //when
        val actualResult = mapper.mapFromResponse(response)

        //then
        Truth.assertThat(actualResult).isNull()
    }

    @Test
    fun `given info null, when mapFromResponse called, then it returns null`(){
        //given
        val info = null
        val response = CharacterListResponse(info = info, listOf<ResultsResponse>())

        //when
        val actualResult = mapper.mapFromResponse(response)

        //then
        Truth.assertThat(actualResult).isNull()
    }

    @Test
    fun `given info and result not null, when mapFromResponse called, then it returns value`(){
        //given
        val response = CharacterListResponse(info = InfoResponse(), listOf<ResultsResponse>())

        //when
        val actualResult = mapper.mapFromResponse(response)

        //then
        Truth.assertThat(actualResult).isNotNull()
    }

    @Test
    fun `given id null, when mapResults called, then it returns null`(){
        //given
        val response = ResultsResponse(id = null)

        //when
        val actualResult = mapper.mapResults(response)

        //then
        Truth.assertThat(actualResult).isNull()
    }

    @Test
    fun `given id is not null, when mapResults called, then it returns value`(){
        //given
        val id = 1
        val response = ResultsResponse(id = id)

        //when
        val actualResult = mapper.mapResults(response)

        //then
        Truth.assertThat(actualResult?.id).isEqualTo(id)
    }
}