package com.kirson.baseproject.formatter

interface Formatter<T> {
  fun format(value: T): String
}
