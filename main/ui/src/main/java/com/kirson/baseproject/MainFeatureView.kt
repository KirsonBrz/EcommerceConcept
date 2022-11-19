package com.kirson.baseproject

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
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
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kirson.baseproject.component.GlobalSearchComponent
import com.kirson.baseproject.component.MainModalBottomSheetScaffold
import com.kirson.baseproject.component.SelectCategoryList
import com.kirson.baseproject.components.EmptyContentMessage
import com.kirson.baseproject.components.SwipeRefresh
import com.kirson.baseproject.components.TopAppBar
import com.kirson.baseproject.core.entity.SortConfiguration
import com.kirson.baseproject.entity.BestSeller
import com.kirson.baseproject.entity.CategoryModel
import com.kirson.baseproject.entity.HomeStore
import com.kirson.baseproject.main.ui.R
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainFeatureView(viewModel: MainFeatureViewModel) {
    MainScreen(viewModel)
}

@Composable
fun MainScreen(viewModel: MainFeatureViewModel) {

    val uiStateFlow by viewModel.uiStateFlow.collectAsState()
    val uiState by viewModel.uiState

    MainContent(
        uiState = uiState, uiStateFlow = uiStateFlow,
        onRefresh = {
            viewModel.observeData()
        },
        onSelectedCategoryChanged = {
            viewModel.changeCategory(it)
        },
        onHomeStoreClick = { homeStore ->
            Log.i("MYTAG", "выбран ${homeStore.title} store")
        },
        onBestSellerClick = { bestSeller ->
            Log.i("MYTAG", "выбран ${bestSeller.title} best seller")
        },
        applySortConfiguration = { sortConfiguration ->
            viewModel.applySortConfiguration(sortConfiguration)
        },
        dismissSortConfigurationDialog = {
            viewModel.dismissSortConfigurationDialog()
        },
        changeSorting = {
            viewModel.changeSorting()
        },


        )
}

@Composable
private fun MainContent(
    uiState: MainFeatureUIState,
    uiStateFlow: State,
    onRefresh: () -> Unit,
    onHomeStoreClick: (HomeStore) -> Unit,
    onBestSellerClick: (BestSeller) -> Unit,
    onSelectedCategoryChanged: (CategoryModel) -> Unit,
    applySortConfiguration: (SortConfiguration) -> Unit,
    dismissSortConfigurationDialog: () -> Unit,
    changeSorting: () -> Unit
) {
    when (uiState) {
        is MainFeatureUIState.Error -> {
            uiState.state.message?.let { error ->
                EmptyContentMessage(
                    imgRes = R.drawable.img_status_disclaimer_170,
                    title = "Ошибка",
                    description = error,
                )
            }
        }

        MainFeatureUIState.Initial -> {
            ContentLoadingState()
        }

        is MainFeatureUIState.Loaded -> {
            ScreenSlot(
                changeSorting = changeSorting, sortConfiguration = uiStateFlow.sortConfiguration
            ) {
                if (uiState.state.bestSellersPhones != null && uiState.state.homeStorePhones != null) {
                    ContentStateReady(
                        state = uiStateFlow,
                        categories = uiState.state.categories,
                        homeStore = uiState.state.homeStorePhones,
                        bestSellers = uiState.state.bestSellersPhones,
                        onRefresh = { onRefresh() },
                        onSelectedCategoryChanged = onSelectedCategoryChanged,
                        onHomeStoreClick = onHomeStoreClick,
                        onBestSellerClick = onBestSellerClick,
                        applySortConfiguration = applySortConfiguration,
                        dismissSortConfigurationDialog = dismissSortConfigurationDialog,
                    )
                } else {
                    EmptyContentMessage(
                        imgRes = R.drawable.img_status_disclaimer_170,
                        title = "Магазин",
                        description = "Данных нет",
                    )
                }
            }
        }

        is MainFeatureUIState.Loading -> {
            ScreenSlot(
                changeSorting = changeSorting,
            ) {
                ContentLoadingState()
            }
        }
    }
}

@Composable
private fun ContentStateReady(
    state: State,
    homeStore: List<HomeStore>,
    bestSellers: List<BestSeller>,
    categories: List<CategoryModel>,
    onRefresh: () -> Unit,
    onHomeStoreClick: (HomeStore) -> Unit,
    onBestSellerClick: (BestSeller) -> Unit,
    applySortConfiguration: (SortConfiguration) -> Unit,
    onSelectedCategoryChanged: (CategoryModel) -> Unit,
    dismissSortConfigurationDialog: () -> Unit,
) {
    MainModalBottomSheetScaffold(
        state = state,
        content = {
            ContentMain(
                state = state,
                categories = categories,
                homeStore = homeStore,
                bestSellers = bestSellers,
                onRefresh = onRefresh,
                onSelectedCategoryChanged = onSelectedCategoryChanged,
                onHomeStoreClick = onHomeStoreClick,
                onBestSellerClick = onBestSellerClick,
            )
        },
        applySortConfiguration = applySortConfiguration,
        dismissSortConfigurationDialog = dismissSortConfigurationDialog,
    )
}


@Composable
private fun ContentMain(
    state: State,
    modifier: Modifier = Modifier,
    categories: List<CategoryModel>,
    homeStore: List<HomeStore>,
    bestSellers: List<BestSeller>,
    onRefresh: () -> Unit,
    onSelectedCategoryChanged: (CategoryModel) -> Unit,
    onHomeStoreClick: (HomeStore) -> Unit,
    onBestSellerClick: (BestSeller) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        SwipeRefresh(
            isRefreshing = false,
            onRefresh = onRefresh,
        ) {
            Column {

                var selectedCategory by remember { mutableStateOf(value = state.selectedCategory) }

                SelectCategoryList(categories = categories,
                    selectedCategory = selectedCategory,
                    onSelectedChanged = {
                        selectedCategory = it
                        onSelectedCategoryChanged(it)
                    })

                GlobalSearchComponent()



                BestSellersList(
                    bestSellers = bestSellers, onBestSellerClick = onBestSellerClick
                ) {
                    HomeStoreList(
                        homeStore = homeStore, onHomeStoreClick = onHomeStoreClick
                    )
                }


            }


        }
    }
}


@Composable
private fun ScreenSlot(
    changeSorting: () -> Unit,
    sortConfiguration: SortConfiguration? = null,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.statusBarsPadding()
    ) {
        TopAppBar(leftContent = {}, centerContent = {
            val checked = remember { mutableStateOf(false) }
            Row {
                Icon(
                    painterResource(id = R.drawable.location_24),
                    contentDescription = "search on map",
                    tint = BaseProjectAppTheme.colors.mainColor,
                    modifier = Modifier.weight(0.2f)
                )
                Text(text = "Zihuatanejo, Gro",
                    color = BaseProjectAppTheme.colors.backgroundSecondary,
                    style = BaseProjectAppTheme.typography.text1,
                    modifier = Modifier
                        .weight(0.7f)
                        .clickable { checked.value = !checked.value })
                IconToggleButton(
                    checked = checked.value,
                    onCheckedChange = { checked.value = it },
                    modifier = Modifier
                        .weight(0.1f)
                        .size(15.dp)
                ) {
                    Icon(
                        imageVector = if (checked.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Choose location",
                        tint = if (checked.value) BaseProjectAppTheme.colors.mainColor else BaseProjectAppTheme.colors.black
                    )
                }
            }

        }, rightContent = {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically { fullHeight -> fullHeight },
                exit = slideOutVertically { fullHeight -> fullHeight },
            ) {
                IconButton(
                    modifier = Modifier.padding(horizontal = 26.dp), onClick = changeSorting
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter_24),
                        contentDescription = "filter Icon",
                        tint = BaseProjectAppTheme.colors.backgroundSecondary
                    )
                }
            }

        })
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}

@Composable
private fun HomeStoreList(
    modifier: Modifier = Modifier,
    homeStore: List<HomeStore>,
    onHomeStoreClick: (HomeStore) -> Unit
) {
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = "Hot sales",
                fontSize = 25.sp,
                fontWeight = FontWeight.W700,
                color = BaseProjectAppTheme.colors.backgroundSecondary,
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
                    color = BaseProjectAppTheme.colors.mainColor,
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
                            color = BaseProjectAppTheme.colors.mainColor
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
                            color = BaseProjectAppTheme.colors.backgroundSecondary,
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

@Composable
private fun BestSellersList(
    modifier: Modifier = Modifier,
    bestSellers: List<BestSeller>,
    onBestSellerClick: (BestSeller) -> Unit,
    top: @Composable () -> Unit,
) {
    Column {
        val listState = rememberLazyGridState()
        LazyVerticalGrid(
            state = listState,
            modifier = modifier.padding(horizontal = 8.dp),
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
                            color = BaseProjectAppTheme.colors.backgroundSecondary,
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
                                color = BaseProjectAppTheme.colors.mainColor,
                                textAlign = TextAlign.Center
                            )

                        }
                    }
                }

            }
            items(items = bestSellers) { phone ->
                Column() {
                    Box(modifier = Modifier
                        .size(width = 181.dp, height = 227.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onBestSellerClick(phone) }) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(phone.picture)
                                .crossfade(true)
                                .build(),
                            contentDescription = "best seller ${phone.title} by price ${phone.discount_price}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(width = 187.dp, height = 168.dp)
                                .align(Alignment.TopCenter)

                        )
                        Row(
                            modifier = Modifier
                                .padding(start = 21.dp, bottom = 33.dp)
                                .align(Alignment.BottomStart)
                        ) {
                            Text(
                                text = "$${phone.discount_price}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W700,
                                color = BaseProjectAppTheme.colors.backgroundSecondary,
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
                            color = BaseProjectAppTheme.colors.backgroundSecondary,
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
}


@Composable
private fun ContentLoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = BaseProjectAppTheme.colors.contendAccentTertiary
        )
    }
}

private fun CoroutineScope.scrollBasic(listState: LazyListState, left: Boolean = false) {
    launch {
        val pos = if (left) listState.firstVisibleItemIndex else listState.firstVisibleItemIndex + 1
        listState.animateScrollToItem(pos)
    }
}

@Composable
private fun LazyListState.isHalfPastItemRight(): Boolean =
    firstVisibleItemScrollOffset > 500


@Composable
private fun LazyListState.isHalfPastItemLeft(): Boolean =
    firstVisibleItemScrollOffset <= 500
