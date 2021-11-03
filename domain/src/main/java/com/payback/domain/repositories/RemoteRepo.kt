package com.payback.domain.repositories


import com.payback.domain.model.Hits
import io.reactivex.Observable

interface RemoteRepo {
    fun getHits(query: String): Observable<List<Hits>>
}