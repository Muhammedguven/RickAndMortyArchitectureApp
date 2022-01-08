package com.muhammedguven.rickandmortyapp.domain.characterdetail

import com.google.common.truth.Truth
import com.muhammedguven.rickandmortyapp.data.remote.model.ResultsResponse
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test

class CharacterDetailMapperTest{

    lateinit var mapper: CharacterDetailMapper

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        mapper = CharacterDetailMapper()
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
    fun `given response id, when mapFromResponse called, then it returns null`(){
        //given
        val response = ResultsResponse(id = null)

        //when
        val actualResult = mapper.mapFromResponse(response)

        //then
        Truth.assertThat(actualResult).isNull()
    }

    @Test
    fun `given info and result not null, when mapFromResponse called, then it returns value`(){
        //given
        val response = ResultsResponse(id = 1)

        //when
        val actualResult = mapper.mapFromResponse(response)

        //then
        Truth.assertThat(actualResult).isNotNull()
    }
}