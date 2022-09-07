package com.example.unittest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unittest.databinding.ActivityMain2Binding
import com.example.unittest.model.QuotesModel
import com.example.unittest.model.QuotesModelItem
import com.example.unittest.ui.adaptor.QuotesAdaptor
import com.example.unittest.viewmodel.MainViewModel

import org.koin.android.ext.android.inject


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private val mainViewModel: MainViewModel by inject()
    private var quotesAdaptor: QuotesAdaptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        mainViewModel.getAllQuote()
        mainViewModel.quoteLiveData.observe(this) {
            setRv(it)
        }
        installSplashScreen()
        setContentView(binding.root)
        mainViewModel.messageLiveData.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.bt.setOnClickListener {
            startActivity(Intent(this@MainActivity2, UnitTestActivity::class.java))
        }
    }

    private fun setRv(list: QuotesModel) {
        val layoutManager = LinearLayoutManager(this)
        binding.rv.layoutManager = layoutManager
        if (list.isNotEmpty()) {
            quotesAdaptor = QuotesAdaptor(list as ArrayList<QuotesModelItem>)
            binding.rv.adapter = quotesAdaptor
        }
    }

}