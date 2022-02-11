package com.example.couroutinewithroom.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
    fun getAllPlayers(): LiveData<List<PersonListItem>>
}