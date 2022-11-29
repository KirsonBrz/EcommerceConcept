package com.kirson.baseproject.entity

data class CartDomainModel(
    val cartPhonesList: List<BasketDomainModel>,
    val delivery: String,
    val id: String,
    val total: Int
)
