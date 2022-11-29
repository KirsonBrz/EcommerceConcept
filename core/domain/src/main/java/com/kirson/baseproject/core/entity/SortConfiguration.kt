package com.kirson.baseproject.core.entity

data class SortConfiguration(
  val property: Property,
  val direction: SortDirection,
) {
  enum class Property {
    Name,
    Value,
      Size
  }

  enum class SortDirection {
    Ascending,
    Descending
  }
}
