package com.payback.domain.repositories

import com.payback.domain.repositories.RemoteRepo

class RemoteRepoImpl(private val apiRemote: RemoteRepo) {

     fun getHits(query: String) = apiRemote.getHits(query)
}