package com.example.superheroeskvh.model.dataclass

import androidx.room.Embedded
import androidx.room.TypeConverter
import androidx.room.TypeConverters


data class AppearanceSH(
    val gender: String?,
    val race: String?,
    @TypeConverters val height: ArrayList<String>?, //agregar typreconverter
    @TypeConverters val weight: ArrayList<String>?, //agregar typreconverter
    val eyeColor: String?,
    val hairColor:String?) {

}
