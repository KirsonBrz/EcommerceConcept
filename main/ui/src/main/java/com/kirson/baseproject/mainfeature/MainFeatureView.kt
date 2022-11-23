package com.kirson.baseproject.mainfeature

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.kirson.baseproject.components.BestSellersList
import com.kirson.baseproject.components.EmptyContentMessage
import com.kirson.baseproject.components.GlobalSearchComponent
import com.kirson.baseproject.components.HomeStoreList
import com.kirson.baseproject.components.MainModalBottomSheetScaffold
import com.kirson.baseproject.components.SelectCategoryTabs
import com.kirson.baseproject.components.SwipeRefresh
import com.kirson.baseproject.components.TopAppBar
import com.kirson.baseproject.core.entity.SortConfiguration
import com.kirson.baseproject.entity.BestSeller
import com.kirson.baseproject.entity.CategoryItem
import com.kirson.baseproject.entity.HomeStore
import com.kirson.baseproject.main.ui.R
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme

@Composable
fun MainFeatureView(
    viewModel: MainFeatureViewModel,
    onPhoneDetails: () -> Unit
) {
    MainScreen(viewModel, onPhoneDetails)
}

@Composable
fun MainScreen(
    viewModel: MainFeatureViewModel,
    onPhoneDetails: () -> Unit
) {


    val uiStateFlow by viewModel.uiStateFlow.collectAsState()
    val uiState by viewModel.uiState

    MainContent(
        uiState = uiState, uiStateFlow = uiStateFlow,
        onRefresh = {
            viewModel.observeData()
        },
        onHomeStoreClick = { homeStore ->
            onPhoneDetails()
            Log.i("MYTAG", "выбран ${homeStore.title} store")
        },
        onBestSellerClick = { bestSeller ->
            onPhoneDetails()
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
    applySortConfiguration: (SortConfiguration) -> Unit,
    dismissSortConfigurationDialog: () -> Unit,
    changeSorting: () -> Unit
) {
    when (uiState) {
        is MainFeatureUIState.Error -> {
            uiState.state.message?.let { error ->
                EmptyContentMessage(
                    imgRes = R.drawable.img_status_disclaimer_170,
                    title = "Error",
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
                        homeStore = uiState.state.homeStorePhones,
                        bestSellers = uiState.state.bestSellersPhones,
                        onRefresh = { onRefresh() },
                        onHomeStoreClick = onHomeStoreClick,
                        onBestSellerClick = onBestSellerClick,
                        applySortConfiguration = applySortConfiguration,
                        dismissSortConfigurationDialog = dismissSortConfigurationDialog,
                    )
                } else {
                    EmptyContentMessage(
                        imgRes = R.drawable.img_status_disclaimer_170,
                        title = "Categories",
                        description = "No data :(",
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
    onRefresh: () -> Unit,
    onHomeStoreClick: (HomeStore) -> Unit,
    onBestSellerClick: (BestSeller) -> Unit,
    applySortConfiguration: (SortConfiguration) -> Unit,
    dismissSortConfigurationDialog: () -> Unit,
) {
    MainModalBottomSheetScaffold(
        state = state,
        content = {
            ContentMain(
                state = state,
                homeStore = homeStore,
                bestSellers = bestSellers,
                onRefresh = onRefresh,
                onHomeStoreClick = onHomeStoreClick,
                onBestSellerClick = onBestSellerClick,
            )
        },
        applySortConfiguration = applySortConfiguration,
        dismissSortConfigurationDialog = dismissSortConfigurationDialog,
    )
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ContentMain(
    state: State,
    modifier: Modifier = Modifier,
    homeStore: List<HomeStore>,
    bestSellers: List<BestSeller>,
    onRefresh: () -> Unit,
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

                val categories = listOf(
                    CategoryItem.Phones,
                    CategoryItem.Computer,
                    CategoryItem.Health,
                    CategoryItem.Books,
                    CategoryItem.Tools,
                    CategoryItem.TV
                )

                val pagerState = rememberPagerState()
                SelectCategoryTabs(tabs = categories, pagerState = pagerState)

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
        TopAppBar(
            leftContent = {},
            centerContent = {
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

            },
            rightContent = {
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

            }
        )
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

