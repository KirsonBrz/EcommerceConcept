package com.kirson.ecommerceconcept.utils

import com.kirson.ecommerceconcept.main.ui.R

sealed class CategoryItem(val name: String, val imageId: Int) {

    object Phones : CategoryItem("Phones", R.drawable.phone_24)
    object Computer : CategoryItem("Computer", R.drawable.computer_24)
    object Health : CategoryItem("Health", R.drawable.health_24)
    object Books : CategoryItem("Books", R.drawable.books_24)
    object Tools : CategoryItem("Tools", R.drawable.tools_24)
    object TV : CategoryItem("TV", R.drawable.tv_24)

}
