package com.example.couroutinewithroom

import androidx.lifecycle.LiveData
import com.example.couroutinewithroom.database.PersonDao

/**
 * Created by PrernaSurbhi on 11/02/22.
 */
class PersonRepository(private val personDao: PersonDao) {

    fun getAllPersons(): LiveData<List<PersonListItem>>{
        return personDao.getAllPersons()
    }
}