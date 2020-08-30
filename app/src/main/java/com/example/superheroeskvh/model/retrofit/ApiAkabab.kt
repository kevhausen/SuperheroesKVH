package com.example.superheroeskvh.model.retrofit

import com.example.superheroeskvh.model.dataclass.Superhero
import retrofit2.Call
import retrofit2.http.GET

//endpoints para obtener los superheroes
interface ApiAkabab {

    @GET("all.json")
    suspend fun getAllSuperheroesFromApi():List<Superhero>
}