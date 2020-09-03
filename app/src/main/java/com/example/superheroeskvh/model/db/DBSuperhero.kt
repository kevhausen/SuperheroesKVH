package com.example.superheroeskvh.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.superheroeskvh.model.dataclass.StringListConverter
import com.example.superheroeskvh.model.dataclass.Superhero

//la instancia unica, con if!=null para que solo se cree una vez la base de datos
@Database(entities = [Superhero::class],version = 5)
@TypeConverters(StringListConverter::class)
abstract class DBSuperhero:RoomDatabase() {
abstract fun daoSuperhero():DaoSuperhero
    companion object{
        @Volatile
        private var INSTANCE: DBSuperhero?=null

        //este metodo te devuelve createdInstance si la base de datos ya se ha creado o; newInstance si es que no se ha creado.
        //newInstance se creara la primera vez que se inicie la aplicacion, todas las siguientes veces devolvera createdInstance
        fun getDBSuperhero(context : Context):DBSuperhero{
            val createdInstance = INSTANCE
            if(createdInstance!=null){
                return createdInstance
            }
            synchronized(this){
                val newInstance = Room.databaseBuilder(context.applicationContext,DBSuperhero::class.java,"superhero_db")
                    .fallbackToDestructiveMigrationFrom(1,2,3,4,5,6,7).build()
                INSTANCE=newInstance
                return newInstance
            }
        }
    }

}