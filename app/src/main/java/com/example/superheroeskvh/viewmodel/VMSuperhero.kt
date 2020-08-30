package com.example.superheroeskvh.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.superheroeskvh.model.RepoSuperhero
import com.example.superheroeskvh.model.dataclass.Superhero
import kotlinx.coroutines.Dispatchers

//del viewmodel se hace el puente entre el repositorio y la mainacitivity
class VMSuperhero(application: Application):AndroidViewModel(application){
private val repository=RepoSuperhero(application)

    //aca hay una lista vacia aun, neecsito cargarle los datos con un metodo del repositorio
    private val heroListFromDB=repository.getLiveDataHeroListFromDB()

    //tengo que hacer esto primero, para guardar los datos a la lista
    fun cargarDatosDeInternetAListaDeDB(){
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