package com.example.superheroeskvh.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.superheroeskvh.model.RepoSuperhero
import kotlinx.coroutines.Dispatchers

//del viewmodel se hace el puente entre el repositorio y la mainacitivity
class VMSuperhero(application: Application):AndroidViewModel(application){
private val repository=RepoSuperhero(application)
    private val heroListFromDB=repository.getLiveDataHeroListFromDB()


    

    val listSuperhero = liveData(Dispatchers.IO){
        val retrive =repository.getAllSuperheroes()
        emit(retrive)
    }




}