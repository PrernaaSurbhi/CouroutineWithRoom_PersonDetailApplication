package com.example.couroutinewithroom.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by PrernaSurbhi on 13/02/22.
 */
class DetailFragViewModelFactory(private val application: Application): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailFragViewModel::class.java)){
            return DetailFragViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown DetailFragViewModel  class")
    }
}