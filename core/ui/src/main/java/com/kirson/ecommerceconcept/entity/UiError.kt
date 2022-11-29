package com.kirson.ecommerceconcept.entity

data class UiError(
  val message: UiMessage,
  val cause: Throwable? = null,
)
