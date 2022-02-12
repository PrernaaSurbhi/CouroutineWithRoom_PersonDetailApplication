package com.example.couroutinewithroom.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.couroutinewithroom.PersonListItem

/**
 * Created by PrernaSurbhi on 11/02/22.
 */
@Dao
interface PersonDao {

    //OnConflictStrategy.IGNORE is to ignoere the conflict
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPersonListItem(persons:List<Person>)

    @Query("SELECT id, firstName, lastName, country, favorite, imageUrl FROM persons")
    fun getAllPersons(): LiveData<List<PersonListItem>>

    @Query("SELECT * FROM persons   WHERE id = :id")
    fun getPerson(id:Int):LiveData<Person>

    @Delete
    suspend fun delete(person:Person)

    @Update
    suspend fun updatePerson(person:Person)


}