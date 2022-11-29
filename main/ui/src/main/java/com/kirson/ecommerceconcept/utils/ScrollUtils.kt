package com.kirson.ecommerceconcept.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.scrollBasic(listState: LazyListState, left: Boolean = false) {
    launch {
        val pos = if (left) listState.firstVisibleItemIndex else listState.firstVisibleItemIndex + 1
        listState.animateScrollToItem(pos)
    }
}

@Composable
fun LazyListState.isHalfPastItemRight(): Boolean =
    firstVisibleItemScrollOffset > 500


@Composable
fun LazyListState.isHalfPastItemLeft(): Boolean =
    firstVisibleItemScrollOffset <= 500

