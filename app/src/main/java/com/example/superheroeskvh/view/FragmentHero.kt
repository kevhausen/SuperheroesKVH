package com.example.superheroeskvh.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.superheroeskvh.R
import com.example.superheroeskvh.viewmodel.VMSuperhero
import kotlinx.android.synthetic.main.activity_main.view.*

class FragmentHero : Fragment(),AdapterSuperhero.IAdapter {
    lateinit var mViewModel:VMSuperhero
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        //se agregan recicler y vistas que se cargan
        val view = inflater.inflate(R.layout.fragment_hero_layout,container,false)

        return view

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel= ViewModelProvider(requireActivity()).get(VMSuperhero::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //aqui ya paso por la inicializacion de las vistas

    }

    override fun heroFromAdapterToMain(id: Int) {
        mViewModel.getHeroFromDB(id)
    }


}