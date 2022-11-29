package com.kirson.ecommerceconcept.utils

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}