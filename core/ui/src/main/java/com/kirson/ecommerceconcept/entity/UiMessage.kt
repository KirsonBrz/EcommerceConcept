package com.kirson.ecommerceconcept.entity

import com.kirson.ecommerceconcept.util.TextRef

data class UiMessage(
  val title: TextRef,
  val description: TextRef? = null,
  val imageResource: Int? = null,
  val primaryAction: Action? = null,
) {
  data class Action(
    val name: TextRef,
    val listener: (() -> Unit),
  )
}
