package com.kirson.baseproject.repository

interface MainRepository {

    suspend fun getAllData(): String

}