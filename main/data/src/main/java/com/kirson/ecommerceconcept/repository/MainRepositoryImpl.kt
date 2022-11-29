package com.kirson.ecommerceconcept.repository


import android.util.Log
import com.kirson.ecommerceconcept.MainRepository
import com.kirson.ecommerceconcept.entity.CartNetworkModel
import com.kirson.ecommerceconcept.entity.PhoneDetailNetworkModel
import com.kirson.ecommerceconcept.entity.PhonesNetworkModel

import com.kirson.ecommerceconcept.repository.dataSource.MainRemoteDataSource
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val mainRemoteDataSource: MainRemoteDataSource,
    //private val mainLocalDataSource: MainLocalDataSource
) : MainRepository {

    override suspend fun getAllPhones(): PhonesNetworkModel? {

        var phones: PhonesNetworkModel? = null

        try {
            val response = mainRemoteDataSource.getPhones()
            val data = response.body()
            if (data != null) {
                phones = data
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }



        return phones
    }


    override suspend fun getPhoneDetails(): PhoneDetailNetworkModel? {

        var details: PhoneDetailNetworkModel? = null

        try {
            val response = mainRemoteDataSource.getPhoneDetails()
            val data = response.body()
            if (data != null) {
                details = data
            }

        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }


        return details
    }

    override suspend fun getCart(): CartNetworkModel? {

        var cart: CartNetworkModel? = null

        try {
            val response = mainRemoteDataSource.getCart()
            val data = response.body()
            if (data != null) {
                cart = data
            }

        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }


        return cart
    }


}


