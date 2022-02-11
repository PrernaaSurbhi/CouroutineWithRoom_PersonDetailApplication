package com.example.couroutinewithroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var personViewModel: PersonViewModel
    private lateinit var personAdapter: PersonAdapter
    private lateinit var personviewModelFactory: PersonviewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personviewModelFactory = PersonviewModelFactory(application)
        personViewModel = ViewModelProvider(this,personviewModelFactory).get(PersonViewModel::class.java)
        setContentView(R.layout.activity_main)
    }
}