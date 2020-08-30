package com.example.superheroeskvh.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.example.superheroeskvh.R
import com.example.superheroeskvh.model.dataclass.Superhero
import com.example.superheroeskvh.viewmodel.VMSuperhero

//instancia de recicler, adapter y viewmodel
class MainActivity : AppCompatActivity() {
private lateinit var mViewModel:VMSuperhero
    private lateinit var lista:List<Superhero>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lista= ArrayList()
        mViewModel=ViewModelProvider(this).get(VMSuperhero::class.java)
        mViewModel.cargarDatosDeInternetAListaDeDB()
        mViewModel.getListFromDB().observe(this, Observer {
            lista=it
            Log.d("lista","dentro observer: ${lista}")
        })
        Log.d("lista","fuera del observe: ${lista}")

        /*mViewModel.listSuperhero.observe(this, Observer {
            lista=it //aca tendria que pasar esta lista al recicler view, pero primero necesito guardar lo de internet en mi base de datos.
            Log.d("lista","${lista}")

        })*/



    }
}