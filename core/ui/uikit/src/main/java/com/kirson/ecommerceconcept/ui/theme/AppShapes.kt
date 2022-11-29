package com.kirson.ecommerceconcept.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
data class AppShapes(
  val smallest: RoundedCornerShape,
  val small: RoundedCornerShape,
  val medium1: RoundedCornerShape,
  val medium2: RoundedCornerShape,
  val large: RoundedCornerShape,
  val large2: RoundedCornerShape,
)

val Shapes = AppShapes(
  smallest = RoundedCornerShape(8.dp),
  small = RoundedCornerShape(12.dp),
  medium1 = RoundedCornerShape(16.dp),
  medium2 = RoundedCornerShape(20.dp),
  large = RoundedCornerShape(24.dp),
  large2 = RoundedCornerShape(32.dp),
)
