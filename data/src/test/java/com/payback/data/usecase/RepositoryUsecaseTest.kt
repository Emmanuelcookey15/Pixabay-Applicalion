package com.payback.data.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.payback.data.mapper.HitsMapper
import com.payback.data.models.HitsResponse
import com.payback.data.models.PixaBayResponse
import com.payback.data.networking.ApiService
import com.payback.domain.model.Hits
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryUsecaseTest{


    private lateinit var repositoryUsecase: RepositoryUsecase

    @Mock
    lateinit var apiService: ApiService

    lateinit var hitsMapper: HitsMapper



    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setup(){
        hitsMapper = HitsMapper()
        repositoryUsecase = RepositoryUsecase(apiService, hitsMapper)
    }


    @Test
    fun `test that the query is properly formated for to be posted on ApiService search`(){

        val formattedQuery = repositoryUsecase.processQueryInApiFormat("Yellow Blue")

        assertEquals(formattedQuery, "Yellow+Blue")

    }


}