package com.example.unittest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.unittest.R

class UnitTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_test)
        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this@UnitTestActivity, com.example.pluginlibrary.MainActivity::class.java))
        }
    }
}