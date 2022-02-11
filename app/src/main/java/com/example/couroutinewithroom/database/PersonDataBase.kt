package com.example.couroutinewithroom.database


import android.content.Context
import android.content.res.Resources
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.couroutinewithroom.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by PrernaSurbhi on 11/02/22.
 */
@Database(version = 1,entities = [Person::class],exportSchema = false)
abstract class PersonDataBase():RoomDatabase() {
    abstract fun personDao():PersonDao

    //CoroutineScope - added below in the parameter is used to switch between different thread
    private class PersonDataBaseCallBack(private val scope:CoroutineScope,private val resource:Resources):RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let{database ->
                scope.launch {
                    val playerDao = database.personDao()
                    prePopulateDatabase(playerDao)
                }
            }
        }

        private suspend fun prePopulateDatabase(personDao: PersonDao){
            val jsonString = resource.openRawResource(R.raw.persons).bufferedReader().use{
                it.readLine()
            }
            val typeToken = object : TypeToken<List<Person>>() {}.type
            val personsList = Gson().fromJson<List<Person>>(jsonString,typeToken)
            personDao.insertAllPersonListItem(personsList)
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: PersonDataBase? = null

        fun getDataBase(context: Context,coroutineScope: CoroutineScope,resource: Resources):PersonDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(context,PersonDataBase::class.java,"person_database")
                    .addCallback(PersonDataBaseCallBack(coroutineScope,resource))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }



}