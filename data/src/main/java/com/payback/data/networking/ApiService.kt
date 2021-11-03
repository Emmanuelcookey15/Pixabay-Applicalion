package com.payback.data.networking

import com.payback.data.models.PixaBayResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        //https://pixabay.com/api/?key={ KEY }&q=yellow+flowers&image_type=photo
        const val BASE_URL = "https://pixabay.com/"
    }


    @GET("api/")
    fun searchImage(@Query("key") api_key: String,
                    @Query("q") q: String,
                    @Query("image_type") imageType: String): Observable<PixaBayResponse>

}
