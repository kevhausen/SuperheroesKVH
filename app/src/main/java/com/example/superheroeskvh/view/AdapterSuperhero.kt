package com.example.superheroeskvh.view

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroeskvh.R
import com.example.superheroeskvh.model.dataclass.Superhero
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.superhero_viewholder.view.*
import org.w3c.dom.Text

//viewholder con id, item y foto chica

class AdapterSuperhero(var mDataset : List<Superhero>,val callAdapter:IAdapter) : RecyclerView.Adapter<AdapterSuperhero.ViewHolderSuperhero>() {



    fun updateData(list:List<Superhero>){
        mDataset=list
        notifyDataSetChanged()
    }

    class ViewHolderSuperhero(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nameSuperhero : TextView = itemView.name_superhero
        var idSuperhero: TextView=itemView.id_superhero
        var imagenSuperhero: ImageView=itemView.image_superhero
        var publisherH:TextView=itemView.publisher_textview

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSuperhero {
        return ViewHolderSuperhero(LayoutInflater.from(parent.context).inflate(R.layout.superhero_viewholder,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderSuperhero, position: Int) {
       val hero =mDataset[position]
        holder.nameSuperhero.text=hero.name
        holder.idSuperhero.text=hero.id.toString()
        holder.publisherH.text=hero.biography.publisher
        Picasso.get().load(hero.images.md).placeholder(R.drawable.ic_launcher_foreground).into(holder.imagenSuperhero)
        //esto hace cada viewholder clikeable
        holder.itemView.setOnClickListener(View.OnClickListener {
            //Toast.makeText(holder.itemView.context,"tocaste",Toast.LENGTH_SHORT).show()
            //aca quiero que me envie al fragment, parece que el viewmodel le tiene que pasar los datos al fragment
            callAdapter.heroFromAdapterToMain(hero.id)

        })
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    interface IAdapter{
        fun heroFromAdapterToMain(id:Int)

    }
}