package com.kirson.baseproject.entity

data class UiError(
  val message: UiMessage,
  val cause: Throwable? = null,
)
