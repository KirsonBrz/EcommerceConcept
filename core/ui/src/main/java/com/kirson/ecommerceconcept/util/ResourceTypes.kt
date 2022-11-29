package com.kirson.ecommerceconcept.util

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class TextRef {
  data class Res(@StringRes val id: Int, val formatArgs: List<Any> = emptyList()) : TextRef() {
    constructor(@StringRes id: Int, vararg formatArgs: Any) : this(id, formatArgs.toList())
  }

  data class QtyRes(@PluralsRes val id: Int, val quantity: Int, val formatArgs: List<Any>) : TextRef() {
    constructor(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any) : this(id, quantity, formatArgs.toList())
  }

  data class Str(val value: CharSequence) : TextRef()

  data class Compound(val refs: List<TextRef>) : TextRef() {
    constructor(vararg refs: TextRef) : this(refs.toList())
  }
}

fun TextRef.append(other: TextRef): TextRef.Compound {
  return when (this) {
    is TextRef.Res -> TextRef.Compound(listOf(this, other))
    is TextRef.QtyRes -> TextRef.Compound(listOf(this, other))
    is TextRef.Str -> TextRef.Compound(listOf(this, other))
    is TextRef.Compound -> TextRef.Compound(this.refs + other)
  }
}

fun emptyTextRef() = TextRef.Str(value = "")
fun TextRef?.orEmpty() = this ?: emptyTextRef()
fun resRef(@StringRes id: Int) = TextRef.Res(id)
fun resRef(@StringRes id: Int, vararg formatArgs: Any) = TextRef.Res(id, *formatArgs)
fun resQtyRef(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any) = TextRef.QtyRes(id, quantity, *formatArgs)
fun strRef(value: CharSequence) = TextRef.Str(value)

sealed class DrawableSrc {
  data class Res(@DrawableRes val id: Int) : DrawableSrc()
  data class Val(val value: Drawable) : DrawableSrc()
}
