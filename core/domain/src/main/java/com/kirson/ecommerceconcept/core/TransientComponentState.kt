package com.kirson.ecommerceconcept.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

/**
 * Для состояния видимости компонентов Compose и их связи с состоянием компонентов модели**/
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
