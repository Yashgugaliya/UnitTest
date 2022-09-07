package com.example.unittest;

import androidx.lifecycle.Observer
import com.example.unittest.model.QuotesModel
import com.example.unittest.viewmodel.MainViewModel
import org.junit.Test
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import kotlin.test.assertNotNull

class MainViewModelApiTest : AppTest() {

    private val mainViewModel: MainViewModel by inject()

    @Mock
    lateinit var quoteLiveData: Observer<QuotesModel>

    @Test
    fun `test Responce`() {
        mainViewModel.quoteLiveData.observeForever(quoteLiveData)
        mainViewModel.getAllQuote()
        Mockito.verify(quoteLiveData).onChanged(mainViewModel.quoteLiveData.value)
        assertNotNull(mainViewModel.quoteLiveData.value)
    }

}
