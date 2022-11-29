package com.kirson.baseproject.di

import com.kirson.baseproject.MainModel
import com.kirson.baseproject.MainModelImpl
import com.kirson.baseproject.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideMainModel(mainRepository: MainRepository): MainModel =
        MainModelImpl(mainRepository)

}