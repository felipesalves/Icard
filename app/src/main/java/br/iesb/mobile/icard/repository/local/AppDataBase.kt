package br.iesb.mobile.icard.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.iesb.mobile.icard.domain.LoginLocalData.Person
import br.iesb.mobile.icard.repository.local.Dao.PersonDao

@Database(entities = [Person::class], version = 1)
    abstract class AppDataBase : RoomDatabase() {
        abstract fun getPersonDao(): PersonDao
    }

