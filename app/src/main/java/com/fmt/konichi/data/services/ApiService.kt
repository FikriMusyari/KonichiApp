package com.fmt.konichi.data.services

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("anime")
    suspend fun searchAnime(@Query("q") query: String): AnimeSearchResponse

    @GET("anime/{id}/full")
    suspend fun getAnimeFullById(@Path("id") id: Int): AnimeDetilDto

    @GET("manga")
    suspend fun searchManga(@Query("q") query: String): MangaSearchResponse

    @GET("manga/{id}/full")
    suspend fun getMangaFullById(@Path("id") id: Int): MangaDetilDto

}