package com.example.superheroeskvh.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.superheroeskvh.model.dataclass.Superhero

//los poderes que tiene nuestra database (insert, obtener, query etc)
@Dao
interface DaoSuperhero {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSuperheroesInDB(listHero:List<Superhero>)

    @Query("SELECT * FROM superhero_table")
    fun getAllSuperheroesFromDB(): LiveData<List<Superhero>>

    @Query("SELECT * FROM superhero_table WHERE id =:idObtained")
    fun getHeroById(idObtained:Int):LiveData<Superhero>
}