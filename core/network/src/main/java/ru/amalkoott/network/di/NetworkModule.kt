package ru.amalkoott.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.amalkoott.domain.repository.AppRemoteRepository
import ru.amalkoott.network.ApiService
import ru.amalkoott.network.ServerRequestRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import ru.amalkoott.network.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
    @Singleton
    @Provides
    fun provideInternetConnection(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideServerApi(retrofit: Retrofit): AppRemoteRepository {
        return ServerRequestRepository(retrofit.create(ApiService::class.java))
    }
}