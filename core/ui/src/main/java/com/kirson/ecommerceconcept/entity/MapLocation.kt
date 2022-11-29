package com.kirson.ecommerceconcept.entity

sealed class MapLocation {
  data class Address(val address: String) : MapLocation()
  data class Position(
    val latitude: Double,
    val longitude: Double,
    val label: String? = null,
  ) : MapLocation()
}
