package com.kirson.ecommerceconcept.screens.explorer

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
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.kirson.ecommerceconcept.components.BestSellersList
import com.kirson.ecommerceconcept.components.ConnectivityStatus
import com.kirson.ecommerceconcept.components.EmptyContentMessage
import com.kirson.ecommerceconcept.components.GlobalSearchComponent
import com.kirson.ecommerceconcept.components.HomeStoreList
import com.kirson.ecommerceconcept.components.MainModalBottomSheetScaffold
import com.kirson.ecommerceconcept.components.SelectCategoryTabs
import com.kirson.ecommerceconcept.components.SwipeRefresh
import com.kirson.ecommerceconcept.components.TopAppBar
import com.kirson.ecommerceconcept.core.entity.SortConfiguration
import com.kirson.ecommerceconcept.entity.BestSellerDomainModel
import com.kirson.ecommerceconcept.entity.HomeStoreDomainModel
import com.kirson.ecommerceconcept.main.ui.R
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme
import com.kirson.ecommerceconcept.utils.CategoryItem
import com.kirson.ecommerceconcept.utils.ConnectionState
import com.kirson.ecommerceconcept.utils.connectivityState

@Composable
fun ExplorerScreen(
    viewModel: ExplorerScreenViewModel,
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
        onBackOnline = {
            viewModel.getData()
        }


    )
}

@Composable
private fun MainContent(
    uiState: ExplorerScreenUIState,
    uiStateFlow: State,
    onRefresh: () -> Unit,
    onHomeStoreClick: (HomeStoreDomainModel) -> Unit,
    onBestSellerClick: (BestSellerDomainModel) -> Unit,
    applySortConfiguration: (SortConfiguration) -> Unit,
    dismissSortConfigurationDialog: () -> Unit,
    changeSorting: () -> Unit,
    onBackOnline: () -> Unit,
) {
    val connection by connectivityState()

    val isConnected = connection == ConnectionState.Available
    when (uiState) {
        is ExplorerScreenUIState.Error -> {
            uiState.state.message?.let { error ->
                EmptyContentMessage(
                    imgRes = R.drawable.img_status_disclaimer_170,
                    title = "Error",
                    description = error,
                )
            }
        }

        ExplorerScreenUIState.Initial -> {
            ContentLoadingState()
        }

        is ExplorerScreenUIState.Loaded -> {
            ScreenSlot(
                changeSorting = changeSorting,
                isConnected = isConnected,
                sortConfiguration = uiStateFlow.sortConfiguration,
                onBackOnline = onBackOnline
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

        is ExplorerScreenUIState.Loading -> {
            ScreenSlot(
                changeSorting = changeSorting,
                isConnected = isConnected,
                onBackOnline = onBackOnline
            ) {
                ContentLoadingState()
            }
        }
    }
}

@Composable
private fun ContentStateReady(
    state: State,
    homeStore: List<HomeStoreDomainModel>,
    bestSellers: List<BestSellerDomainModel>,
    onRefresh: () -> Unit,
    onHomeStoreClick: (HomeStoreDomainModel) -> Unit,
    onBestSellerClick: (BestSellerDomainModel) -> Unit,
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
    homeStore: List<HomeStoreDomainModel>,
    bestSellers: List<BestSellerDomainModel>,
    onRefresh: () -> Unit,
    onHomeStoreClick: (HomeStoreDomainModel) -> Unit,
    onBestSellerClick: (BestSellerDomainModel) -> Unit
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

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)) {

                    Text(
                        text = "Select Category",
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
                            text = "view all",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400,
                            color = EcommerceConceptAppTheme.colors.primaryColor,
                            textAlign = TextAlign.Center
                        )
                    }
                }

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
    isConnected: Boolean,
    sortConfiguration: SortConfiguration? = null,
    onBackOnline: () -> Unit,
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
                        tint = EcommerceConceptAppTheme.colors.primaryColor,
                        modifier = Modifier.weight(0.2f)
                    )
                    Text(text = "Zihuatanejo, Gro",
                        color = EcommerceConceptAppTheme.colors.secondaryColor,
                        style = EcommerceConceptAppTheme.typography.text1,
                        modifier = Modifier
                            .weight(0.7f)
                            .clickable {
                                checked.value = !checked.value
                            })
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
                            tint = if (checked.value) EcommerceConceptAppTheme.colors.primaryColor else EcommerceConceptAppTheme.colors.black
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
                            tint = EcommerceConceptAppTheme.colors.secondaryColor
                        )
                    }
                }

            }
        )
        ConnectivityStatus(isConnected = isConnected, onBackOnline = onBackOnline)
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
            color = EcommerceConceptAppTheme.colors.contendAccentTertiary
        )
    }
}

