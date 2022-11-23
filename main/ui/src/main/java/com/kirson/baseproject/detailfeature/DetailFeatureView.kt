package com.kirson.baseproject.detailfeature

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import com.kirson.baseproject.components.DetailsScreenTabs
import com.kirson.baseproject.components.EmptyContentMessage
import com.kirson.baseproject.components.TopAppBar
import com.kirson.baseproject.entity.APIPhoneDetails
import com.kirson.baseproject.main.ui.R
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme
import kotlin.math.absoluteValue


@Composable
fun DetailFeatureView(viewModel: DetailFeatureViewModel) {
    DetailScreen(viewModel)
}

@Composable
fun DetailScreen(viewModel: DetailFeatureViewModel) {

    val uiStateFlow by viewModel.uiStateFlow.collectAsState()
    val uiState by viewModel.uiState

    DetailContent(uiState = uiState, uiStateFlow = uiStateFlow, onAddToCart = { phoneDetail ->
        viewModel.addToCart(phoneDetail)
    })


}

@Composable
private fun DetailContent(
    uiState: DetailFeatureUIState, uiStateFlow: State, onAddToCart: (APIPhoneDetails) -> Unit
) {

    when (uiState) {
        is DetailFeatureUIState.Error -> {
            uiState.state.message?.let { error ->
                EmptyContentMessage(
                    imgRes = R.drawable.img_status_disclaimer_170,
                    title = "Ошибка",
                    description = error,
                )
            }
        }

        DetailFeatureUIState.Initial -> {
            ContentLoadingState()
        }

        is DetailFeatureUIState.Loaded -> {
            ScreenSlot() {
                if (uiState.state.phoneDetails != null) {
                    ContentDetailsReady(
                        state = uiStateFlow,
                        phoneDetails = uiState.state.phoneDetails,
                        onAddToCart = onAddToCart
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

        is DetailFeatureUIState.Loading -> {
            ScreenSlot(

            ) {
                ContentLoadingState()
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ContentDetailsReady(
    state: State,
    phoneDetails: APIPhoneDetails,
    onAddToCart: (APIPhoneDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {

        Column {


            DetailPhotoList(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.38f),
                imgStrings = phoneDetails.images
            )

            Spacer(modifier = Modifier.height(14.dp))
            DetailBox(
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
fun DetailPhotoList(
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
internal fun DetailBox(
    modifier: Modifier = Modifier,
    phoneDetails: APIPhoneDetails,
    onAddToCart: (APIPhoneDetails) -> Unit,

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
                    color = BaseProjectAppTheme.colors.backgroundSecondary,
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
                            color = BaseProjectAppTheme.colors.backgroundSecondary,
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
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = BaseProjectAppTheme.colors.mainColor,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
            ) {
                androidx.compose.material.Text(
                    text = "Add to Cart",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(60.dp))
                androidx.compose.material.Text(
                    text = "$%,d.00".format(phoneDetails.price),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center
                )

            }

        }
    }
}


@Composable
private fun ScreenSlot(
    content: @Composable () -> Unit,
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
                IconButton(modifier = Modifier.padding(horizontal = 26.dp), onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter_24),
                        contentDescription = "filter Icon",
                        tint = BaseProjectAppTheme.colors.backgroundSecondary
                    )
                }
            }


        }, centerContent = {

        }, rightContent = {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically { fullHeight -> fullHeight },
                exit = slideOutVertically { fullHeight -> fullHeight },
            ) {
                IconButton(modifier = Modifier.padding(horizontal = 26.dp), onClick = {}) {
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

@Preview
@Composable
fun DetailBoxPreview() {
    BaseProjectAppTheme {
        DetailBox(
            phoneDetails = APIPhoneDetails(
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
