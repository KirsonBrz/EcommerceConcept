package com.kirson.ecommerceconcept.di

import com.kirson.ecommerceconcept.MainModel
import com.kirson.ecommerceconcept.MainModelImpl
import com.kirson.ecommerceconcept.MainRepository
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