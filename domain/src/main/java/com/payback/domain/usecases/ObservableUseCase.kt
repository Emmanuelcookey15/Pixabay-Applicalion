package com.payback.domain.usecases

import io.reactivex.Observable

interface ObservableUseCase<T> {
    fun execute(query: String): Observable<List<T>>
}