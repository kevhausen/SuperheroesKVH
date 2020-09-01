package com.example.superheroeskvh.model.dataclass

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "superhero_table")
data class Superhero(
    @PrimaryKey val id:Int,
    val name:String,
    val slug:String,
    @Embedded val powerstats : PowerstatsSH,
    @Embedded val appearance : AppearanceSH,
    @Embedded val biography : BiographySH,
    @Embedded val work : WorkSH,
    @Embedded val connections : ConnectionsSH,
    @Embedded val images : ImagesSH) {

}


