package com.xcheko51x.DragonballAPI.data.api.retrofit


import DBZCharacter
import com.xcheko51x.DragonballAPI.data.api.models.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("characters/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<DBZCharacter>

    @GET("characters")
    fun getListOfCharacters(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Call<CharacterResponse>
}