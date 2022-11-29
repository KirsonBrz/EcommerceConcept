package com.kirson.ecommerceconcept.formatter

interface Formatter<T> {
  fun format(value: T): String
}
