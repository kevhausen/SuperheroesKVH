package com.example.superheroeskvh.model

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.superheroeskvh.model.dataclass.Superhero
import com.example.superheroeskvh.model.db.DBSuperhero
import com.example.superheroeskvh.model.retrofit.RetrofitClientK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

//del repositorio, se llaman los poderes del DAO y de RETROFIT
class RepoSuperhero(context: Context) {
        //aca se instancia primero la base de datos, para luego instanciar nuestra lista desde la base de datos
        private val db : DBSuperhero= DBSuperhero.getDBSuperhero(context)
        private val heroListFromDB=db.daoSuperhero().getAllSuperheroesFromDB()

        //funcion para obtener la lista de superheroes desde la DB (esta vacia aun, tengo que meterle datos)
        fun getLiveDataHeroListFromDB(): LiveData<List<Superhero>>{
                return heroListFromDB
        }

        fun getHeroByIdFromDB(id:Int): LiveData<Superhero>{
                val heroObtainedById = db.daoSuperhero().getHeroById(id)
                return heroObtainedById
        }



        suspend fun getAllSuperheroes()=RetrofitClientK.retrofitInstance().getAllSuperheroesFromApi()

        fun insertSuperheroesFromWebIntoDB(){
                CoroutineScope(IO).launch {
                        db.daoSuperhero().insertAllSuperheroesInDB(getAllSuperheroes())
                }
        }


        /*aca quiero insertar lo que me esta llegando desde internet directamente a la base de datos, pero parece que no se hace asi
        asi lo tenia antes:
        suspend fun getAllSuperheroes()=RetrofitClientK.retrofitInstance().getAllSuperheroesFromApi()*/

        /* aca estoy intentando hacer la llamda a internet y la insercion de esos datos a la db en una pura funcion, pero no estoy seguro
        suspend fun getAllSuperheroes()= CoroutineScope(IO).launch {
                let{db.daoSuperhero().insertAllSuperheroesInDB(RetrofitClientK.retrofitInstance().getAllSuperheroesFromApi())}
        }
     */


        //db.DaoSuperhero().insertSuperhero(getAllSuperheroes) creo que con esto se insertarian en la base de datos

}