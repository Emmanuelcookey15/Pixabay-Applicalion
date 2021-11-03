package com.payback.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PixaBayResponse (

    @SerializedName("total")
    @Expose
    var total: Int? = null,
    @SerializedName("totalHits")
    @Expose
    var totalHits: Int? = null,
    @SerializedName("hits")
    @Expose
    var results: List<HitsResponse>? = null


)

data class HitsResponse(

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("user")
    @Expose
    var user: String? = null,
    @SerializedName("previewURL")
    @Expose
    var image: String? = null,
    @SerializedName("largeImageURL")
    @Expose
    var imageLarge: String? = null,
    @SerializedName("likes")
    @Expose
    var like: Int? = null,
    @SerializedName("downloads")
    @Expose
    var downloads: Int? = null,
    @SerializedName("comments")
    @Expose
    var comments: Int? = null,
    @SerializedName("tags")
    @Expose
    var tags: String? = null
)