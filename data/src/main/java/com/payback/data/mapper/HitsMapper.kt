package com.payback.data.mapper

import com.payback.data.models.PixaBayResponse
import com.payback.domain.model.Hits
import javax.inject.Inject

class HitsMapper @Inject constructor() {

    fun toHits(pixaBayResponse: PixaBayResponse): List<Hits> {
        return pixaBayResponse.results!!.map { hitsResponse ->
            Hits(
                hitsResponse.id,
                hitsResponse.user,
                hitsResponse.image,
                hitsResponse.imageLarge,
                hitsResponse.like,
                hitsResponse.downloads,
                hitsResponse.comments,
                hitsResponse.tags
            )
        }
    }
}