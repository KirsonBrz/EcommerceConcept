package com.kirson.ecommerceconcept.mappers

import com.kirson.ecommerceconcept.entity.BasketDomainModel
import com.kirson.ecommerceconcept.entity.BasketNetworkModel
import com.kirson.ecommerceconcept.entity.BestSellerDomainModel
import com.kirson.ecommerceconcept.entity.BestSellerNetworkModel
import com.kirson.ecommerceconcept.entity.CartDomainModel
import com.kirson.ecommerceconcept.entity.CartNetworkModel
import com.kirson.ecommerceconcept.entity.HomeStoreDomainModel
import com.kirson.ecommerceconcept.entity.HomeStoreNetworkModel
import com.kirson.ecommerceconcept.entity.PhoneDetailDomainModel
import com.kirson.ecommerceconcept.entity.PhoneDetailNetworkModel
import com.kirson.ecommerceconcept.entity.PhonesDomainModel
import com.kirson.ecommerceconcept.entity.PhonesNetworkModel


fun PhonesNetworkModel.toDomainModel(): PhonesDomainModel = PhonesDomainModel(
    bestSellerList = this.bestSellerList.toDomainBestSellers(),
    homeStoreList = this.homeStoreList.toDomainHomeStores()
)

fun PhoneDetailNetworkModel.toDomainModel(): PhoneDetailDomainModel =
    PhoneDetailDomainModel(
        CPU = this.CPU,
        camera = this.camera,
        capacity = this.capacity,
        color = this.color,
        id = this.id,
        images = this.images,
        isFavorites = this.isFavorites,
        price = this.price,
        rating = this.rating,
        sd = this.sd,
        ssd = this.ssd,
        title = this.title
    )

fun CartNetworkModel.toDomainModel(): CartDomainModel =
    CartDomainModel(
        cartPhonesList = this.cartPhonesList.toDomainBaskets(),
        delivery = this.delivery,
        id = this.id,
        total = this.total
    )

fun List<BestSellerNetworkModel>.toDomainBestSellers(): List<BestSellerDomainModel> =
    this.map {
        BestSellerDomainModel(
            discount_price = it.discount_price,
            id = it.id,
            is_favorites = it.is_favorites,
            picture = it.picture,
            price_without_discount = it.price_without_discount,
            title = it.title,
        )
    }

fun List<HomeStoreNetworkModel>.toDomainHomeStores(): List<HomeStoreDomainModel> =
    this.map {
        HomeStoreDomainModel(
            id = it.id,
            is_buy = it.is_buy,
            is_new = it.is_new,
            picture = it.picture,
            subtitle = it.subtitle,
            title = it.title
        )
    }

fun List<BasketNetworkModel>.toDomainBaskets(): List<BasketDomainModel> =
    this.map {
        BasketDomainModel(
            id = it.id,
            images = it.images,
            price = it.price,
            title = it.title
        )
    }