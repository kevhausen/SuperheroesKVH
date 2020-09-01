package com.example.superheroeskvh.model.dataclass

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class BiographySH(val fullName : String,
                       val alterEgos : String,
                       @Embedded val aliases : ArrayList<String>,
                       val placeOfBirth : String,
                       val firstAppearance : String,
                       val publisher : String,
                       val alignment : String) {

}
