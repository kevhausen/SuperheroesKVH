package com.example.superheroeskvh.model.dataclass

import androidx.room.Embedded


data class AppearanceSH(
    val gender: String?,
    val race: String?,
    @Embedded val height: ArrayList<String>?,
    @Embedded val weight: ArrayList<String>?,
    val eyeColor: String?,
    val hairColor:String?) {

}
