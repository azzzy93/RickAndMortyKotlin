package kg.geektech.rickandmortykotlin.data.remote

import kg.geektech.rickandmortykotlin.data.model.Character
import kg.geektech.rickandmortykotlin.data.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): Response<Character>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Result>
}