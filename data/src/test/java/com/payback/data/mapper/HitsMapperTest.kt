package com.payback.data.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.payback.data.models.HitsResponse
import com.payback.data.models.PixaBayResponse
import com.payback.domain.model.Hits
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock

class HitsMapperTest{

    private lateinit var mapper: HitsMapper



    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setup(){
        mapper = HitsMapper()
    }

    @Test
    fun `test that the Pixa bay response from ApiService is mapped to the Hits Model as represented in Database`(){

        val pixaBayResponse = PixaBayResponse(200, 50, mutableListOf<HitsResponse>())

        assertEquals(mapper.toHits(pixaBayResponse), mutableListOf<Hits>())

    }



}