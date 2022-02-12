package com.example.couroutinewithroom

import androidx.lifecycle.LiveData
import com.example.couroutinewithroom.database.Person
import com.example.couroutinewithroom.database.PersonDao

/**
 * Created by PrernaSurbhi on 11/02/22.
 */
class PersonRepository(private val personDao: PersonDao) {

    fun getAllPersons(): LiveData<List<PersonListItem>>{
        return personDao.getAllPersons()
    }

    fun getPlayer(id:Int):LiveData<Person>{
        return personDao.getPerson(id)
    }

   suspend fun deletePerson(person:Person){
       personDao.delete(person)
   }

    suspend fun updatePerson(person:Person){
        personDao.updatePerson(person)
    }
}