package com.kirson.ecommerceconcept.components.sorting

import androidx.compose.material.LocalTextStyle
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.takeOrElse
import kotlin.math.max

fun Modifier.fillLineHeight(
  lineHeight: TextUnit = TextUnit.Unspecified
): Modifier = composed(
  debugInspectorInfo {
    name = "fillLineHeight"
    value = lineHeight
  }
) {
  val resolvedLineHeight = lineHeight.takeOrElse { LocalTextStyle.current.lineHeight }
  if (resolvedLineHeight.isSpecified) {
    val lineHeightPx = with(LocalDensity.current) { resolvedLineHeight.roundToPx() }
    Modifier.layout { measurable, constraints ->
      val placeable = measurable.measure(constraints)
      val space = max(0, lineHeightPx - placeable.height)
      layout(placeable.width, placeable.height + space) {
        placeable.place(x = 0, y = space / 2)
      }
    }
  } else {
    Modifier
  }
}