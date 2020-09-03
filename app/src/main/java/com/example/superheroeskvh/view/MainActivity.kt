package com.example.superheroeskvh.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroeskvh.R
import com.example.superheroeskvh.model.dataclass.Superhero
import com.example.superheroeskvh.model.retrofit.RetrofitClientK
import com.example.superheroeskvh.viewmodel.VMSuperhero
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

/*A group grows great when old men plant trees in whose shade they shall never sit*/
//instancia de recicler, adapter y viewmodel
class MainActivity : AppCompatActivity(),AdapterSuperhero.IAdapter {

    private lateinit var mViewModel:VMSuperhero
    private lateinit var lista:List<Superhero>
    private lateinit var heroFragment:FragmentHero

private lateinit var heroAdapter : AdapterSuperhero
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lista= ArrayList()
        mViewModel=ViewModelProvider(this).get(VMSuperhero::class.java)
        //mViewModel.cargarDatosDeInternetAListaDeDB() //este paso parece que se puede unir a otro, desde el repositorio
        heroAdapter=AdapterSuperhero(lista,this)
        initializeRecycler()

        //aca le estoy agregando a "lista" lo que contiene la lista de la DB, creo que se puede resumir poniendo un parametro que recibe lista en getListFRomDB()
        mViewModel.getListFromDB().observe(this,{
            heroAdapter.updateData(it)
        })

    }

    //inicializa recicler con cardview y un espaciado de 30 (el padding)
    fun initializeRecycler(){
        recycler_superhero.apply {
            adapter=heroAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
            addItemDecoration(TopSpacingItemDecoration(30))
        }
    }
    //todos las funcionalides de vistas van en mainacitivity
    fun initFrag(){
        val firstFrag=FragmentHero()
        supportFragmentManager.beginTransaction().replace(R.id.hero_fragment,firstFrag).commit()
    }

    //metodo que trae el id del superheroe clikeado al main
    override fun heroFromAdapterToMain(id: Int) {
        //Toast.makeText(this,"${id}",Toast.LENGTH_SHORT).show()
        heroFragment= FragmentHero()
        mViewModel.getHeroFromDB(id)//aca se le pasa el id traido desde el adapter al viewmodel, y este va a ir a buscar el superheroe entero a la base de datos.
        // tode eso en una linea de codigo

        supportFragmentManager.beginTransaction().replace(R.id.hero_fragment,heroFragment).addToBackStack("heroFrag").commit()
    }
}