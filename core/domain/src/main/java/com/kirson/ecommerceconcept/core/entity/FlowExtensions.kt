package com.kirson.ecommerceconcept.core.entity

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

fun <T1 : Any, T2 : Any> Flow<T1>.mapDistinctChanges(transform: suspend (T1) -> T2): Flow<T2> {
  return this.map(transform).distinctUntilChanged()
}

fun <T1 : Any, T2> Flow<T1>.mapDistinctNotNullChanges(
  transform: (T1) -> T2?,
): Flow<T2> {
  return this.mapNotNull(transform).distinctUntilChanged()
}
