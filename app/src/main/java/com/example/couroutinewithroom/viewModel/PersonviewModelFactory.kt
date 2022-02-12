package com.example.couroutinewithroom

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * Created by PrernaSurbhi on 10/02/22.
 */
@Suppress("UNCHECKED_CAST")
class PersonviewModelFactory(private var application: Application):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PersonViewModel::class.java)){
            return PersonViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown PersonviewModelFactory class")
    }

}