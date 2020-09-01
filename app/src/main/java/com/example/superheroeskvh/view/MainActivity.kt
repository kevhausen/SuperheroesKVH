package com.example.superheroeskvh.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroeskvh.R
import com.example.superheroeskvh.model.dataclass.Superhero
import com.example.superheroeskvh.viewmodel.VMSuperhero
import kotlinx.android.synthetic.main.activity_main.*
/*A group grows great when old men plant trees in whose shade they shall never sit*/
//instancia de recicler, adapter y viewmodel
class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel:VMSuperhero
    private lateinit var lista:List<Superhero>

private lateinit var heroAdapter : AdapterSuperhero
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lista= ArrayList()
        mViewModel=ViewModelProvider(this).get(VMSuperhero::class.java)
        //mViewModel.cargarDatosDeInternetAListaDeDB() //este paso parece que se puede unir a otro, desde el repositorio
        heroAdapter=AdapterSuperhero(lista)
        initializeRecycler()
        

        //aca le estoy agregando a "lista" lo que contiene la lista de la DB, creo que se puede resumir poniendo un parametro que recibe lista en getListFRomDB()
        mViewModel.getListFromDB().observe(this, Observer {
            //lista=it
            heroAdapter.updateData(it)

            Log.d("lista","dentro observer: ${lista}")
        })
        Log.d("lista","fuera del observe: ${lista}")

    }

    //inicializa recicler con cardview y un espaciado de 30 (el padding)
    fun initializeRecycler(){
        recycler_superhero.apply {
            adapter=heroAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
            addItemDecoration(TopSpacingItemDecoration(30))

        }
    }
}