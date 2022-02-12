package com.example.couroutinewithroom

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.couroutinewithroom.database.PersonDataBase

/**
 * Created by PrernaSurbhi on 10/02/22.
 */
class PersonViewModel(application:Application): ViewModel() {
     private val repository:PersonRepository

     init {
         val personDao = PersonDataBase
                       .getDataBase(application,viewModelScope,application.resources).personDao()

         repository = PersonRepository(personDao)
     }

    fun getallPersonsList(): LiveData<List<PersonListItem>> {
        return repository.getAllPersons()
    }

}