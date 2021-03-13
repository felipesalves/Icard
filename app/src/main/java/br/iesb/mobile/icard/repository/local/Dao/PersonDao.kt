package br.iesb.mobile.icard.repository.local.Dao

import androidx.room.*
import br.iesb.mobile.icard.domain.LoginLocalData.Person

@Dao
interface PersonDao {

    @Query("select * from person")
    suspend fun getPerson(): Person

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(p: Person)

    @Update
    suspend fun updatePerson(p: Person)

    @Delete
    suspend fun deletePerson(p: Person)
}