package com.example.superheroeskvh.view

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroeskvh.R
import com.example.superheroeskvh.model.dataclass.Superhero
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.superhero_viewholder.view.*

//viewholder con id, item y foto chica

class AdapterSuperhero(var mDataset : List<Superhero>) : RecyclerView.Adapter<AdapterSuperhero.ViewHolderSuperhero>() {

    fun updateData(list:List<Superhero>){
        mDataset=list
        notifyDataSetChanged()
    }

    class ViewHolderSuperhero(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nameSuperhero : TextView = itemView.name_superhero
        var idSuperhero: TextView=itemView.id_superhero
        var imagenSuperhero: ImageView=itemView.image_superhero

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSuperhero {
        return ViewHolderSuperhero(LayoutInflater.from(parent.context).inflate(R.layout.superhero_viewholder,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderSuperhero, position: Int) {
       val hero =mDataset[position]
        holder.nameSuperhero.text=hero.name
        holder.idSuperhero.text=hero.id.toString()
        Picasso.get().load("https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/sm/${hero.slug}.jpg").into(holder.imagenSuperhero)
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}