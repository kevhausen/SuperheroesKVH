package com.example.superheroeskvh.model.dataclass

import androidx.room.Embedded
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

data class BiographySH(val fullName : String?,
                       val alterEgos : String?,
                       @TypeConverters val aliases : ArrayList<String>?,//pasar a typeconverter
                       val placeOfBirth : String?,
                       val firstAppearance : String?,
                       val publisher : String?,
                       val alignment : String?) {

}
