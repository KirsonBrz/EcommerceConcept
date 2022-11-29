package com.kirson.ecommerceconcept.screens.details

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.kirson.ecommerceconcept.components.ConnectivityStatus
import com.kirson.ecommerceconcept.components.DetailsScreenTabs
import com.kirson.ecommerceconcept.components.EmptyContentMessage
import com.kirson.ecommerceconcept.components.TopAppBar
import com.kirson.ecommerceconcept.entity.PhoneDetailDomainModel
import com.kirson.ecommerceconcept.main.ui.R
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme
import com.kirson.ecommerceconcept.utils.ConnectionState
import com.kirson.ecommerceconcept.utils.connectivityState
import kotlin.math.absoluteValue


@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel,
    onBackScreen: () -> Unit,
    onCartScreen: () -> Unit
) {

    val uiStateFlow by viewModel.uiStateFlow.collectAsState()
    val uiState by viewModel.uiState

    DetailsContent(
        uiState = uiState,
        uiStateFlow = uiStateFlow,
        onBackScreen = { onBackScreen() },
        onCartScreen = { onCartScreen() },
        onBackOnline = {
            viewModel.getData()
        }
    )


}

@Composable
private fun DetailsContent(
    uiState: DetailsScreenUIState,
    uiStateFlow: State,
    onBackScreen: () -> Unit,
    onCartScreen: () -> Unit,
    onBackOnline: () -> Unit,
) {
    val connection by connectivityState()

    var basketCount by remember { mutableStateOf(0) }

    val isConnected = connection == ConnectionState.Available


    when (uiState) {
        is DetailsScreenUIState.Error -> {
            uiState.state.message?.let { error ->
                EmptyContentMessage(
                    imgRes = R.drawable.img_status_disclaimer_170,
                    title = "Ошибка",
                    description = error,
                )
            }
        }

        DetailsScreenUIState.Initial -> {
            ContentLoadingState()
        }

        is DetailsScreenUIState.Loaded -> {
            ScreenSlot(
                isConnected = isConnected,
                onBackOnline = onBackOnline,
                onBackScreen = onBackScreen,
                onCartScreen = onCartScreen,
                cartCount = basketCount
            ) {
                if (uiState.state.phoneDetails != null) {
                    ContentDetailsReady(
                        state = uiStateFlow,
                        phoneDetails = uiState.state.phoneDetails,
                        onAddToCart = { basketCount += 1 }
                    )
                } else {
                    EmptyContentMessage(
                        imgRes = R.drawable.img_status_disclaimer_170,
                        title = "Phone Details",
                        description = "No data :(",
                    )
                }
            }
        }

        is DetailsScreenUIState.Loading -> {
            ScreenSlot(
                isConnected = isConnected,
                onBackScreen = onBackScreen,
                onCartScreen = onCartScreen,
                onBackOnline = onBackOnline,
                cartCount = basketCount
            ) {
                ContentLoadingState()
            }
        }
    }
}


@Composable
private fun ContentDetailsReady(
    state: State,
    phoneDetails: PhoneDetailDomainModel,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {

        Column {


            DetailsPhotoList(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.38f),
                imgStrings = phoneDetails.images
            )

            Spacer(modifier = Modifier.height(14.dp))
            DetailsBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(1.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(30.dp)
                    ),
                phoneDetails = phoneDetails,
                onAddToCart = onAddToCart,
            )

        }


    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsPhotoList(
    modifier: Modifier = Modifier,
    imgStrings: List<String>,
) {


    HorizontalPager(
        modifier = modifier,
        count = imgStrings.size,
        contentPadding = PaddingValues(horizontal = 32.dp),
    ) { page ->
        Card(
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.White,
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    alpha = lerp(
                        start = 0.3f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imgStrings[page])
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 10.dp)
            )
        }

    }


}

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
internal fun DetailsBox(
    modifier: Modifier = Modifier,
    phoneDetails: PhoneDetailDomainModel,
    onAddToCart: () -> Unit,

    ) {

    Box(
        modifier = modifier
    )
    {
        Column(modifier = Modifier.padding(horizontal = 27.dp, vertical = 30.dp)) {
            Row() {
                Text(
                    text = phoneDetails.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W500,
                    color = EcommerceConceptAppTheme.colors.secondaryColor,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(50.dp))

                val checkedState = remember { mutableStateOf(phoneDetails.isFavorites) }

                IconToggleButton(
                    checked = checkedState.value,
                    onCheckedChange = {
                        checkedState.value = !checkedState.value
                    },

                    modifier = Modifier
                        .background(
                            color = EcommerceConceptAppTheme.colors.secondaryColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    val transition = updateTransition(checkedState.value, label = "")

                    val tint by transition.animateColor(label = "iconColor") { isChecked ->
                        if (isChecked) Color.White else Color.White
                    }

                    val size by transition.animateDp(
                        transitionSpec = {
                            if (false isTransitioningTo true) {
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
            }

            val rating by remember { mutableStateOf(phoneDetails.rating.toFloat()) }
            RatingBar(
                value = rating,
                config = RatingBarConfig()
                    .style(RatingBarStyle.HighLighted)
                    .size(18.dp)
                    .padding(6.dp),
                onValueChange = {
                },
                onRatingChanged = {
                }
            )
            val tabs = listOf(
                "Shop",
                "Details",
                "Features"
            )
            val pagerState = rememberPagerState()

            DetailsScreenTabs(tabs, pagerState, phoneDetails = phoneDetails)
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { onAddToCart() },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = EcommerceConceptAppTheme.colors.primaryColor,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
            ) {
                Text(
                    text = "Add to Cart",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(60.dp))
                Text(
                    text = "$%,d.00".format(phoneDetails.price),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

            }

        }
    }
}


@Composable
private fun ScreenSlot(
    isConnected: Boolean,
    onBackScreen: () -> Unit,
    onCartScreen: () -> Unit,
    onBackOnline: () -> Unit,
    cartCount: Int,
    content: @Composable () -> Unit

) {
    Column(
        modifier = Modifier.statusBarsPadding()
    ) {


        TopAppBar(leftContent = {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically { fullHeight -> fullHeight },
                exit = slideOutVertically { fullHeight -> fullHeight },
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(horizontal = 26.dp)
                        .background(
                            color = EcommerceConceptAppTheme.colors.secondaryColor,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    onClick = onBackScreen
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "back",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )

                }
            }


        }, centerContent = {

            Text(
                text = "Product Details",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                color = EcommerceConceptAppTheme.colors.secondaryColor,
            )


        }, rightContent = {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically { fullHeight -> fullHeight },
                exit = slideOutVertically { fullHeight -> fullHeight },
            ) {
                Box {

                    IconButton(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 26.dp)
                            .background(
                                color = EcommerceConceptAppTheme.colors.primaryColor,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        onClick = onCartScreen
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.cart_24),
                            contentDescription = "cart",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )

                    }
                    if (cartCount > 0) {
                        Surface(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(top = 20.dp, start = 15.dp)
                                .clip(CircleShape)
                                .size(27.dp),
                            color = Color.Red
                        ) {
                            Text(
                                text = cartCount.toString(),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 7.dp)
                            )

                        }
                    }
                }

            }

        })
        ConnectivityStatus(isConnected = isConnected, onBackOnline = { })
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

@Preview
@Composable
fun DetailBoxPreview() {
    EcommerceConceptAppTheme {
        DetailsBox(
            phoneDetails = PhoneDetailDomainModel(
                "sd",
                "sd",
                listOf("128", "256"),
                listOf("#772D03", "#010035"),
                "3",
                listOf(
                    "https://avatars.mds.yandex.net/get-mpic/5235334/img_id5575010630545284324.png/orig",
                    "https://www.manualspdf.ru/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.jpg"
                ),
                true,
                1500,
                4.5,
                "256 GB",
                "8 GB",
                "Galaxy Note 20 Ultra"

            ),
            onAddToCart = {}
        )
    }
}
