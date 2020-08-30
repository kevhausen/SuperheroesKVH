package com.example.superheroeskvh.model.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

//pojo json
@Entity(tableName = "superhero_table")
data class Superhero(
    @PrimaryKey val id:Int,
    val name:String,
    val slug:String) {
}