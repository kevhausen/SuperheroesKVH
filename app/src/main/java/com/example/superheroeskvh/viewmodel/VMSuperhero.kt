package com.example.superheroeskvh.viewmodel

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.superheroeskvh.model.RepoSuperhero
import com.example.superheroeskvh.model.dataclass.Superhero
import com.example.superheroeskvh.view.FragmentHero

//del viewmodel se hace el puente entre el repositorio y la mainacitivity
class VMSuperhero(application: Application):AndroidViewModel(application){
private val repository=RepoSuperhero(application)

    //logica fragments
    abstract class fragManager: FragmentManager()
    val heroFragment = FragmentHero()


    fun getHeroFromDB(id:Int):LiveData<Superhero>{
        return repository.getHeroByIdFromDB(id)
    }

    //aca hay una lista vacia aun, neecsito cargarle los datos con un metodo del repositorio
    private val heroListFromDB=repository.getLiveDataHeroListFromDB()

    init {
        loadDataFromWebToDB()
        Log.d("kevin","pasa porm el init")
    }
    //tengo que hacer esto primero, para guardar los datos a la lista
    //ingresar a swipe torefresh adapter

    fun loadDataFromWebToDB(){
        repository.insertSuperheroesFromWebIntoDB()
    }

    //ahora tengo que llamar a esa lista cargada desde la db (esto es como un getter)
    fun getListFromDB():LiveData<List<Superhero>>{
        return heroListFromDB
    }





    //esto llama a la lista de superheroes directamente desde la api, sin pasar por la db
   /* val listSuperhero = liveData(Dispatchers.IO){
        val retrive =repository.getAllSuperheroes()
        emit(retrive)
    }*/




}