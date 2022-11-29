package com.kirson.baseproject.di


import com.kirson.baseproject.MainRepository
import com.kirson.baseproject.api.MainAPIService
import com.kirson.baseproject.main.data.BuildConfig
import com.kirson.baseproject.repository.MainRepositoryImpl
import com.kirson.baseproject.repository.dataSource.MainRemoteDataSource
import com.kirson.baseproject.repository.dataSourceImpl.MainRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Singleton
    @Provides
    fun provideMainRepository(
        mainRemoteDataSource: MainRemoteDataSource
    ): MainRepository =
        MainRepositoryImpl(mainRemoteDataSource)

}

@Module
@InstallIn(SingletonComponent::class)
object NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()


    @Singleton
    @Provides
    fun provideMainAPIService(retrofit: Retrofit): MainAPIService =
        retrofit.create(MainAPIService::class.java)

    @Singleton
    @Provides
    fun provideMainRemoteDataSource(
        mainAPIService: MainAPIService
    ): MainRemoteDataSource = MainRemoteDataSourceImpl(mainAPIService)


}