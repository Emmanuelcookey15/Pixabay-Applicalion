package com.payback.data.usecase

import com.payback.data.mapper.HitsMapper
import com.payback.data.networking.ApiService
import com.payback.domain.model.Hits
import com.payback.domain.repositories.RemoteRepo
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryUsecase @Inject constructor(
    private val apiService: ApiService,
    private val hitsMapper: HitsMapper
): RemoteRepo {

    companion object {
        const val API_KEY = "24121865-ffe599d876773218eca23bd06"
        const val IMAGE_TYPE = "photo"
    }


    override fun getHits(query: String): Observable<List<Hits>> {
        return  apiService.searchImage(API_KEY, query, IMAGE_TYPE)
            .map {
                hitsMapper.toHits(it)
            }
    }

    fun processQueryInApiFormat(query: String): String {
        var res = ""
        for (i in query.indices){
            if (query[i] == ' '){
                res = "$res+"
            }else{
                res += query[i]
            }
        }
        return res
    }


}