package com.example.couroutinewithroom.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.couroutinewithroom.PersonListItem
import com.example.couroutinewithroom.PersonRepository
import com.example.couroutinewithroom.database.Person
import com.example.couroutinewithroom.database.PersonDao
import com.example.couroutinewithroom.database.PersonDataBase
import kotlinx.coroutines.launch

/**
 * Created by PrernaSurbhi on 12/02/22.
 */
class DetailFragViewModel(application: Application): ViewModel() {
   val personRepository:PersonRepository

   init{
       val personDao = PersonDataBase.getDataBase(application,viewModelScope,application.resources).personDao()
       personRepository = PersonRepository(personDao)
   }

   fun getperson(personListItem: PersonListItem) :LiveData<Person>{
      return personRepository.getPlayer(personListItem.id)
   }

    fun deletePerson(person:Person) = viewModelScope.launch {
       personRepository.deletePerson(person)
   }


    fun update(person:Person) = viewModelScope.launch {
        personRepository.updatePerson(person)
    }
}