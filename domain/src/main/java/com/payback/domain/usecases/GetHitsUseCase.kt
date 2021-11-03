package com.payback.domain.usecases

import com.payback.domain.model.Hits
import com.payback.domain.repositories.RemoteRepo
import io.reactivex.Observable
import javax.inject.Inject

class GetHitsUseCase @Inject constructor(private val apiRepo: RemoteRepo): ObservableUseCase<Hits> {

    override fun execute(query: String): Observable<List<Hits>> {
        return apiRepo.getHits(query)
    }

}