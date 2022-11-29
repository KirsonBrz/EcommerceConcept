package com.kirson.ecommerceconcept.util

import android.content.Context
import android.content.res.Resources
import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

@Suppress("SpreadOperator") // not in a performance critical path
fun Context.resolveRef(
  source: TextRef,
  vararg formatArgs: Any?,
): CharSequence {
  return when (source) {
    is TextRef.Res -> getString(source.id, formatArgs)
    is TextRef.QtyRes -> {
      if (formatArgs.isNotEmpty()) resources.getQuantityString(source.id, source.quantity, *formatArgs)
      else resources.getQuantityString(source.id, source.quantity)
    }
    is TextRef.Str -> if (formatArgs.isNotEmpty()) source.value.toString().format(*formatArgs) else source.value
    is TextRef.Compound -> buildString {
      source.refs.forEach { append(this@resolveRef.resolveRef(it, *formatArgs)) }
    }
  }
}

@Suppress("SpreadOperator") // is not usually called on a performance-critical path
@Composable
@ReadOnlyComposable
fun resolveRef(source: TextRef): String {
  return when (source) {
    is TextRef.Res -> {
      if (source.formatArgs.isEmpty()) {
        stringResource(source.id)
      } else {
        stringResource(source.id, *source.formatArgs.toTypedArray())
      }
    }
    is TextRef.QtyRes -> {
      if (source.formatArgs.isEmpty()) {
        pluralResource(source.id, source.quantity)
      } else {
        pluralResource(source.id, source.quantity, *source.formatArgs.toTypedArray())
      }
    }
    is TextRef.Str -> source.value.toString()
    is TextRef.Compound -> {
      buildString {
        source.refs.forEach {
          append(resolveRef(source = it))
        }
      }
    }
  }
}

// NOTE_PLURALS_SHOULD_BE_IMPLEMENTED_IN_COMPOSE
// Временное решение, аналогичная функция должна быть в api compose
// see: https://issuetracker.google.com/issues/191375123
@Composable
@ReadOnlyComposable
fun pluralResource(
  @PluralsRes id: Int,
  quantity: Int,
  vararg formatArgs: Any? = emptyArray(),
): String {
  return resources().getQuantityString(id, quantity, *formatArgs)
}

// см. NOTE_PLURALS_SHOULD_BE_PROVIDED_BY_COMPOSE
@Composable
@ReadOnlyComposable
fun pluralResource(
  @PluralsRes id: Int,
  quantity: Int,
): String {
  return resources().getQuantityString(id, quantity)
}

// см. NOTE_PLURALS_SHOULD_BE_PROVIDED_BY_COMPOSE
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
  LocalConfiguration.current
  return LocalContext.current.resources
}
