package com.kirson.ecommerceconcept.entity

import com.google.gson.annotations.SerializedName

data class PhonesNetworkModel(

    @SerializedName("best_seller")
    val bestSellerList: List<BestSellerNetworkModel>,
    @SerializedName("home_store")
    val homeStoreList: List<HomeStoreNetworkModel>

)