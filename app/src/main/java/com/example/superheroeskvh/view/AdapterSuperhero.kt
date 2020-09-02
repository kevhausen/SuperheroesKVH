package com.example.superheroeskvh.view

import android.app.Application
import android.app.Dialog
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.*
import android.graphics.Color.WHITE
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroeskvh.R
import com.example.superheroeskvh.model.dataclass.Superhero
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hero_dialog_layout.*
import kotlinx.android.synthetic.main.superhero_viewholder.view.*
import org.w3c.dom.Text
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
            //Toast.makeText(holder.itemView.context,"tocaste",Toast.LENGTH_SHORT).show()
            //aca quiero que me envie al fragment, parece que el viewmodel le tiene que pasar los datos al fragment

            //este metodo es para traspasar el id al main, y despues iniciar un fragment, voy a intentar con un dialog primero
            //callAdapter.heroFromAdapterToMain(hero.id)

            val heroDialog = Dialog(holder.itemView.context)
            heroDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            heroDialog.setCancelable(true)
            heroDialog.setContentView(R.layout.hero_dialog_layout)
            heroDialog.hero_dialog_name.text = hero.name
            heroDialog.publisher_dialog.text=hero.biography.publisher
            //biography sets
            heroDialog.fullname_dialogsv.text=hero.biography.fullName
            heroDialog.alteregos_dialogsv.text=hero.biography.alterEgos
            heroDialog.aliases_sv.text= hero.biography.aliases.toString() //no me esta llegando esa arraylist embedded desde la base de datos o quizas de retrofit
            heroDialog.birth_dialogsv.text=hero.biography.placeOfBirth
            heroDialog.firstAppearance_dialogsv.text=hero.biography.firstAppearance
            heroDialog.alignment_dialogsv.text=hero.biography.alignment



            val mColor = ContextCompat.getColor(holder.itemView.context, R.color.colorWhite)


            //aca estoy haciendo blanquecina la imagen que va a ponerse en hero_image_dialog
            //heroDialog.hero_image_dialog.colorFilter=

            //Picasso.get().load(hero.images.lg).into(heroDialog.hero_image_dialog)
            Picasso.get().load(hero.images.lg).into(object : com.squareup.picasso.Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    val backgroundImg = BitmapDrawable(holder.itemView.context.resources, bitmap)
                    backgroundImg.colorFilter = PorterDuffColorFilter(mColor, PorterDuff.Mode.ADD)
                    heroDialog.dialog_layout.background = backgroundImg
                    //heroDialog.dialog_layout.backgroundTintBlendMode=BlendMode.HARD_LIGHT
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    Log.e("dialog", "fail")
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    //TODO que muestre un progress bar
                    Log.e("dialog", "prepare")
                }
            })
            //ImageViewCompat.setImageTintList(heroDialog.hero_image_dialog, ColorStateList.valueOf(mColor))


            heroDialog.show()


        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    //interface creada para traspasar datos al main
    interface IAdapter{
        fun heroFromAdapterToMain(id:Int)

    }

    //metodo para iniciar el dialog al ahcer click en un viewholder




}