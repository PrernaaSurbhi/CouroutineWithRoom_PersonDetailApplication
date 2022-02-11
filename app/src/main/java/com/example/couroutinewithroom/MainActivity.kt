package com.example.couroutinewithroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.couroutinewithroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var personViewModel: PersonViewModel
   // private lateinit var personAdapter: PersonAdapter
    private lateinit var personviewModelFactory: PersonviewModelFactory
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        personviewModelFactory = PersonviewModelFactory(application)
        personViewModel = ViewModelProvider(this,personviewModelFactory).get(PersonViewModel::class.java)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }
}