package com.fmt.konichi.di

import com.fmt.konichi.data.repo.AnimeRepo
import com.fmt.konichi.data.repo.AnimeRepoImpl
import com.fmt.konichi.data.repo.MangaRepo
import com.fmt.konichi.data.repo.MangaRepoImpl
import com.fmt.konichi.data.services.ApiService
import com.fmt.konichi.usecase.GetAnimeDetilUseCase
import com.fmt.konichi.usecase.SearchAnimeUseCase
import com.fmt.konichi.usecase.GetMangaDetilUseCase
import com.fmt.konichi.usecase.SearchMangaUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.jikan.moe/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideAnimeRepository(api: ApiService): AnimeRepo =
        AnimeRepoImpl(api)

    @Provides
    @Singleton
    fun provideSearchAnimeUseCase(repo: AnimeRepo): SearchAnimeUseCase =
        SearchAnimeUseCase(repo)

    @Provides
    @Singleton
    fun provideGetAnimeDetailUseCase(repo: AnimeRepo): GetAnimeDetilUseCase =
        GetAnimeDetilUseCase(repo)

    @Provides
    @Singleton
    fun provideMangaRepository(api: ApiService): MangaRepo =
        MangaRepoImpl(api)

    @Provides
    @Singleton
    fun provideSearchMangaUseCase(repo: MangaRepo): SearchMangaUseCase =
        SearchMangaUseCase(repo)

    @Provides
    @Singleton
    fun provideGetMangaDetailUseCase(repo: MangaRepo): GetMangaDetilUseCase =
        GetMangaDetilUseCase(repo)

}