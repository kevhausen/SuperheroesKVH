package com.example.superheroeskvh.view

import android.app.Dialog
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroeskvh.R
import com.example.superheroeskvh.model.dataclass.Superhero
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hero_dialog_layout.*
import kotlinx.android.synthetic.main.superhero_viewholder.view.*
import java.lang.Exception

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
        holder.itemView.setOnClickListener {

            //INICIALIZACION DE DIALOG
            val heroDialog = Dialog(holder.itemView.context)
            heroDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            heroDialog.setCancelable(true)
            heroDialog.setContentView(R.layout.hero_dialog_layout)

            //FUNCION PARA SETEAR STRING RESOURCES A LOS TEXTVIEW
            fun setString(stringResource:Int,heroDetail:String):String{
                return heroDialog.context.resources.getString(stringResource, heroDetail)
            }

            //GENERAL TEXT SETS
            heroDialog.hero_dialog_name.text=
                hero.name
            heroDialog.publisher_dialog.text=
                hero.biography.publisher

            //BIOGRAPHY TEXT SETS
            heroDialog.fullname_dialogsv.text=
                setString(R.string.full_name,hero.biography.fullName.toString())
            heroDialog.alteregos_dialogsv.text=
                setString(R.string.alter_egos, hero.biography.alterEgos.toString())
            heroDialog.aliases_sv.text=
                setString(R.string.aliases,hero.biography.aliases.toString())
            heroDialog.birth_dialogsv.text=
                setString(R.string.place_of_birth,hero.biography.placeOfBirth.toString())
            heroDialog.firstAppearance_dialogsv.text=
                setString(R.string.first_appearance,hero.biography.firstAppearance.toString())
            heroDialog.alignment_dialogsv.text=
                setString(R.string.alignment,hero.biography.alignment.toString())

            //WORK TEXT SETS
            heroDialog.occupation_dialogsv.text=
                setString(R.string.occupation,hero.work.occupation.toString())
            heroDialog.base_dialogsv.text=
                setString(R.string.base,hero.work.base.toString())

            //POWERSTATS TEXT SET
            heroDialog.int_dialogsv.text=
                setString(R.string.intelligence,hero.powerstats.intelligence.toString())
            heroDialog.str_dialogsv.text=
                setString(R.string.strength,hero.powerstats.strength.toString())
            heroDialog.speed_dialogsv.text=
                setString(R.string.speed,hero.powerstats.speed.toString())
            heroDialog.durability_dialogsv.text=
                setString(R.string.durability,hero.powerstats.durability.toString())
            heroDialog.power_dialogsv.text=
                setString(R.string.power,hero.powerstats.power.toString())
            heroDialog.combat_dialogsv.text=
                setString(R.string.combat,hero.powerstats.combat.toString())

            //CONNECTIONS TEXT SET
            heroDialog.gaffiliation_dialogsv.text=
                setString(R.string.group_affiliation,hero.connections.groupAffiliation.toString())
            heroDialog.relatives_dialogsv.text=
                setString(R.string.relatives,hero.connections.relatives.toString())

            //APPEARANCE TEXT SET
            heroDialog.gender_dialogsv.text=
                setString(R.string.gender,hero.appearance.gender.toString())
            heroDialog.race_dialogsv.text=
                setString(R.string.race, hero.appearance.race.toString())
            heroDialog.height_dialogsv.text=
                setString(R.string.height,hero.appearance.height!![1])
            heroDialog.weight_dialogsv.text=
                setString(R.string.weight,hero.appearance.weight!![1])
            heroDialog.eyecolor_dialogsv.text=
                setString(R.string.eyecolor,hero.appearance.eyeColor.toString())
            heroDialog.haircolor_dialogsv.text=
                setString(R.string.haircolor,hero.appearance.hairColor.toString())

            //COLOR BLANCO PARA METERLO EN EL FILTER
            val mColor=ContextCompat.getColor(holder.itemView.context, R.color.colorWhite)

            //var af = ProgressBar(holder.itemView.context)
            val af=heroDialog.dialog_background_load

            //SETEANDO IMAGEN COMO BACKGROUND DE LAYOUT CON PICASSO
            Picasso.get().load(hero.images.lg).into(object : com.squareup.picasso.Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    val backgroundImg = BitmapDrawable(holder.itemView.context.resources, bitmap)
                    backgroundImg.colorFilter = PorterDuffColorFilter(mColor, PorterDuff.Mode.ADD)
                    heroDialog.dialog_layout.background = backgroundImg
                    af.visibility=View.GONE
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    af.visibility=View.GONE
                    val loadFail=heroDialog.load_failed
                    loadFail.text=e.toString()
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    af.visibility=View.VISIBLE
                    heroDialog.dialog_layout.background=placeHolderDrawable
                }
            })
            heroDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    //interface creada para traspasar datos al main (TODAVIA NO SE USA)
    interface IAdapter{
        fun heroFromAdapterToMain(id:Int)

    }

    //metodo para iniciar el dialog al ahcer click en un viewholder
    fun initializeHeroDetails(){
        //no se como hacerla y me da flojera


    }





}