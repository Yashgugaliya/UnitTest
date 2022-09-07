package com.example.unittest.repository

import com.example.unittest.network.ApiInterface
import com.example.unittest.model.QuotesModel
import io.reactivex.Single

class MainRepository(private val apiInterface: ApiInterface){

     fun getAllQuote(): Single<QuotesModel>{
       return apiInterface.getAllQuote()
    }
}
