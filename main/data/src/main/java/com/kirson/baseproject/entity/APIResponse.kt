package com.kirson.baseproject.entity

data class APIResponse(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)