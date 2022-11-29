package com.kirson.baseproject.entity

import com.google.gson.annotations.SerializedName

data class CartNetworkModel(
    @SerializedName("basket")
    val cartPhonesList: List<BasketNetworkModel>,
    @SerializedName("delivery")
    val delivery: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("total")
    val total: Int
)