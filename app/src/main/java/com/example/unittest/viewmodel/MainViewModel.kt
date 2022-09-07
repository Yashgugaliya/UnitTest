package com.example.unittest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unittest.repository.MainRepository
import com.example.unittest.model.QuotesModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    var messageLiveData = MutableLiveData<String>()

    var quoteLiveData = MutableLiveData<QuotesModel>()
    private lateinit var quoteDataObserver: DisposableSingleObserver<QuotesModel>

    fun getAllQuote(){
        quoteDataObserver = object: DisposableSingleObserver<QuotesModel>(){
            override fun onSuccess(t: QuotesModel) {
                quoteLiveData.postValue(t) }
            override fun onError(e: Throwable) { messageLiveData.postValue(e.localizedMessage) }
        }
        mainRepository.getAllQuote().
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(quoteDataObserver)
    }
}
