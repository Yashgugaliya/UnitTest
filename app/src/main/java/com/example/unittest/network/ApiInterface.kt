package com.example.unittest.network

import com.example.unittest.model.QuotesModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/quotes ")
    fun getAllQuote() : Single<QuotesModel>
}