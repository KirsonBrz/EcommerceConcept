package com.kirson.ecommerceconcept.utils

import androidx.compose.ui.graphics.Color

fun Color.Companion.parse(colorText: String): Color =
    Color(color = android.graphics.Color.parseColor(colorText))
