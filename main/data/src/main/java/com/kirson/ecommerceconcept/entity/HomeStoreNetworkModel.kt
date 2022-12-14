package com.kirson.ecommerceconcept.entity

data class HomeStoreNetworkModel(
    val id: Int,
    val is_buy: Boolean,
    val is_new: Boolean,
    val picture: String,
    val subtitle: String,
    val title: String
)