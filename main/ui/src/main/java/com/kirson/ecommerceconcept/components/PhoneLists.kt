package com.kirson.ecommerceconcept.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kirson.ecommerceconcept.entity.BestSellerDomainModel
import com.kirson.ecommerceconcept.entity.HomeStoreDomainModel
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme
import com.kirson.ecommerceconcept.utils.isHalfPastItemLeft
import com.kirson.ecommerceconcept.utils.isHalfPastItemRight
import com.kirson.ecommerceconcept.utils.scrollBasic

@Composable
internal fun HomeStoreList(
    modifier: Modifier = Modifier,
    homeStore: List<HomeStoreDomainModel>,
    onHomeStoreClick: (HomeStoreDomainModel) -> Unit
) {
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = "Hot sales",
                fontSize = 25.sp,
                fontWeight = FontWeight.W700,
                color = EcommerceConceptAppTheme.colors.secondaryColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 5.dp, bottom = 10.dp)
                    .align(Alignment.CenterStart)
            )

            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text(
                    text = "see more",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                    color = EcommerceConceptAppTheme.colors.primaryColor,
                    textAlign = TextAlign.Center
                )
            }
        }
        val listState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()
        LazyRow(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            state = listState
        ) {
            items(items = homeStore) { phone ->
                Box(modifier = Modifier.clickable { onHomeStoreClick(phone) }) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(phone.picture)
                            .crossfade(true)
                            .build(),
                        contentDescription = "kek",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(367.dp, 182.dp)
                            .padding(horizontal = 8.dp)
                            .align(Alignment.Center)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Column(modifier = Modifier.align(Alignment.CenterStart)) {
                        Text(
                            text = phone.title,
                            fontWeight = FontWeight.W700,
                            fontSize = 25.sp,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 21.dp)
                        )
                        Text(
                            text = phone.subtitle,
                            fontWeight = FontWeight.W400,
                            fontSize = 11.sp,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 21.dp)
                        )
                    }
                    if (phone.is_new) {
                        Surface(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 14.dp, start = 25.dp)
                                .clip(CircleShape)
                                .size(27.dp),
                            color = EcommerceConceptAppTheme.colors.primaryColor
                        ) {
                            Text(
                                text = "New",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 5.dp)
                            )

                        }
                    }

                    Surface(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(bottom = 26.dp, start = 25.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .size(98.dp, 23.dp),
                        color = Color.White
                    ) {
                        Text(
                            text = "Buy now!",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.W700,
                            color = EcommerceConceptAppTheme.colors.secondaryColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(4.dp)
                        )
                    }


                }
                if (!listState.isScrollInProgress) {
                    if (listState.isHalfPastItemLeft())
                        coroutineScope.scrollBasic(listState, left = true)
                    else
                        coroutineScope.scrollBasic(listState)

                    if (listState.isHalfPastItemRight())
                        coroutineScope.scrollBasic(listState)
                    else
                        coroutineScope.scrollBasic(listState, left = true)
                }

            }
        }
    }

}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
internal fun BestSellersList(
    modifier: Modifier = Modifier,
    bestSellers: List<BestSellerDomainModel>,
    onBestSellerClick: (BestSellerDomainModel) -> Unit,
    top: @Composable () -> Unit,
) {
    Column {
        val listState = rememberLazyGridState()
        LazyVerticalGrid(
            state = listState,
            modifier = modifier.padding(horizontal = 8.dp, vertical = 5.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Column {
                    top()
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Best Sellers",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.W700,
                            color = EcommerceConceptAppTheme.colors.secondaryColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .align(Alignment.CenterStart)
                        )

                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                        ) {
                            Text(
                                text = "see more",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W400,
                                color = EcommerceConceptAppTheme.colors.primaryColor,
                                textAlign = TextAlign.Center
                            )

                        }
                    }
                }

            }
            items(items = bestSellers) { phone ->
                Spacer(modifier = Modifier.height(5.dp))
                Box(modifier = Modifier
                    .size(width = 181.dp, height = 240.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .clickable { onBestSellerClick(phone) }
                ) {
                    val checkedState = remember { mutableStateOf(phone.is_favorites) }

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(phone.picture)
                            .crossfade(true)
                            .build(),
                        contentDescription = "best seller ${phone.title} by price ${phone.discount_price}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(width = 187.dp, height = 168.dp)
                            .align(Alignment.TopCenter)


                    )

                    IconToggleButton(
                        checked = checkedState.value,
                        onCheckedChange = {
                            checkedState.value = !checkedState.value
                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.TopEnd)
                    ) {
                        val transition = updateTransition(checkedState.value)


                        val tint by transition.animateColor(label = "iconColor") { isChecked ->
                            if (isChecked) Color.Red else Color.Red
                        }

                        // om below line we are specifying transition
                        val size by transition.animateDp(
                            transitionSpec = {
                                // on below line we are specifying transition
                                if (false isTransitioningTo true) {
                                    // on below line we are specifying key frames
                                    keyframes {
                                        durationMillis = 250

                                        30.dp at 0 with LinearOutSlowInEasing
                                        35.dp at 15 with FastOutLinearInEasing
                                        40.dp at 75
                                        35.dp at 150
                                    }
                                } else {
                                    spring(stiffness = Spring.StiffnessVeryLow)
                                }
                            },
                            label = "Size"
                        ) {
                            30.dp
                        }


                        Icon(
                            imageVector = if (checkedState.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Icon",
                            tint = tint,
                            modifier = Modifier.size(size)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 21.dp, bottom = 33.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = "$${phone.discount_price}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W700,
                            color = EcommerceConceptAppTheme.colors.secondaryColor,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = "$${phone.price_without_discount}",
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.LightGray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 6.dp)
                        )

                    }
                    Text(
                        text = phone.title,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W400,
                        color = EcommerceConceptAppTheme.colors.secondaryColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 21.dp, bottom = 15.dp)
                            .align(Alignment.BottomStart)
                    )


                }


            }
        }
    }
}