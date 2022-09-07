package com.example.unittest;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.unittest.model.QuotesModel
import com.example.unittest.model.QuotesModelItem
import com.example.unittest.repository.MainRepository
import com.example.unittest.viewmodel.MainViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest  {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    lateinit var repo: MainRepository

    @Mock
    lateinit var quoteLiveData: Observer<QuotesModel>
    private lateinit var viewModel: MainViewModel


    @Before
    fun setUp() {
        viewModel = MainViewModel(repo)
    }

    @Test
    fun `test item Responce`() {
        val item = QuotesModel()
        item.add(QuotesModelItem("abc","c","h","q"))
        Mockito.`when`(repo.getAllQuote()).thenReturn(Single.just(item))
        viewModel.quoteLiveData.observeForever(quoteLiveData)
        viewModel.getAllQuote()
        Mockito.verify(quoteLiveData).onChanged(viewModel.quoteLiveData.value)
        assertNotNull(viewModel.quoteLiveData.value)
        assertEquals("a",viewModel.quoteLiveData.value?.get(0)?.a )
    }

}
