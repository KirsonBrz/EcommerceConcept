package com.kirson.ecommerceconcept.entity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
sealed class TransientComponentState<out T> {
  @Immutable
  data class Visible<T>(val config: T) : TransientComponentState<T>()

  @Immutable
  object None : TransientComponentState<Nothing>()
}

fun <R, T> TransientComponentState<T>.map(
  mapper: (T) -> R,
): TransientComponentState<R> {
  return when (this) {
    TransientComponentState.None -> TransientComponentState.None
    is TransientComponentState.Visible -> TransientComponentState.Visible(
      mapper(this.config)
    )
  }
}

@Composable
fun <R, T> TransientComponentState<T>.mapInComposable(
  mapper: @Composable (T) -> R,
): TransientComponentState<R> {
  return when (this) {
    TransientComponentState.None -> TransientComponentState.None
    is TransientComponentState.Visible -> TransientComponentState.Visible(
      mapper(this.config)
    )
  }
}
