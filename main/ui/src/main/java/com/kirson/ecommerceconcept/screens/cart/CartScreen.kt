package com.kirson.ecommerceconcept.screens.cart


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kirson.ecommerceconcept.components.ConnectivityStatus
import com.kirson.ecommerceconcept.components.EmptyContentMessage
import com.kirson.ecommerceconcept.components.HorizontalDivider
import com.kirson.ecommerceconcept.components.TopAppBar
import com.kirson.ecommerceconcept.entity.CartDomainModel
import com.kirson.ecommerceconcept.main.ui.R
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme
import com.kirson.ecommerceconcept.utils.ConnectionState
import com.kirson.ecommerceconcept.utils.connectivityState
import com.kirson.ecommerceconcept.utils.parse


@Composable
fun CartScreen(
    viewModel: CartScreenViewModel,
    onBackScreen: () -> Unit
) {

    val uiStateFlow by viewModel.uiStateFlow.collectAsState()
    val uiState by viewModel.uiState

    CartContent(
        uiState = uiState,
        uiStateFlow = uiStateFlow,
        onBackScreen = onBackScreen
    )


}

@Composable
private fun CartContent(
    uiState: CartScreenUIState,
    uiStateFlow: State,
    onBackScreen: () -> Unit
) {
    val connection by connectivityState()

    val isConnected = connection == ConnectionState.Available


    when (uiState) {
        is CartScreenUIState.Error -> {
            uiState.state.message?.let { error ->
                EmptyContentMessage(
                    imgRes = R.drawable.img_status_disclaimer_170,
                    title = "Ошибка",
                    description = error,
                )
            }
        }

        CartScreenUIState.Initial -> {
            ContentLoadingState()
        }

        is CartScreenUIState.Loaded -> {
            ScreenSlot(
                isConnected = isConnected,
                onBackScreen = onBackScreen
            ) {
                if (uiState.state.cartDomainModel != null) {
                    ContentCartReady(
                        state = uiStateFlow,
                        cartDomainModel = uiState.state.cartDomainModel,
                    )
                } else {
                    EmptyContentMessage(
                        imgRes = R.drawable.img_status_disclaimer_170,
                        title = "Cart",
                        description = "No data :(",
                    )
                }
            }
        }

        is CartScreenUIState.Loading -> {
            ScreenSlot(
                isConnected = isConnected,
                onBackScreen = onBackScreen
            ) {
                ContentLoadingState()
            }
        }
    }
}


@Composable
private fun ContentCartReady(
    state: State,
    modifier: Modifier = Modifier,
    cartDomainModel: CartDomainModel,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {

        Column {


            CartTopContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.23f)

            )
            CartBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        color = EcommerceConceptAppTheme.colors.secondaryColor,
                        shape = RoundedCornerShape(30.dp)
                    ), cartDomainModel = cartDomainModel
            )

        }


    }
}


@Composable
fun CartTopContent(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {

        Text(
            text = "My Cart",
            color = EcommerceConceptAppTheme.colors.secondaryColor,
            fontSize = 35.sp,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 40.dp)
        )

    }


}


@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
internal fun CartBox(
    modifier: Modifier = Modifier,
    cartDomainModel: CartDomainModel,

    ) {
    Box(
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(horizontal = 4.dp, vertical = 40.dp)) {

            val listState = rememberLazyListState()





            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(0.68f)
                    .padding(horizontal = 8.dp, vertical = 5.dp), state = listState
            ) {
                items(items = cartDomainModel.cartPhonesList) { cartPhone ->
                    var phoneCount by remember { mutableStateOf(1) }
                    if (phoneCount > 0) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(cartPhone.images).crossfade(true).build(),
                                contentDescription = "kek",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(width = 95.dp, height = 88.dp)
                                    .padding(horizontal = 8.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Column(
                                modifier = Modifier.width(155.dp)
                            ) {
                                Text(
                                    text = cartPhone.title,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 20.sp,
                                    maxLines = 2,
                                    color = Color.White,
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "$%d.00".format(cartPhone.price),
                                    fontWeight = FontWeight.W500,
                                    fontSize = 20.sp,
                                    color = EcommerceConceptAppTheme.colors.primaryColor
                                )
                            }
                            Spacer(modifier = Modifier.width(33.dp))
                            CartCountSelector(phoneCount,
                                onAdd = { phoneCount += 1 },
                                onDelete = { if (phoneCount > 0) phoneCount -= 1 })
                            Spacer(modifier = Modifier.width(12.dp))

                            IconButton(
                                onClick = { phoneCount = 0 },
                                modifier = Modifier.align(Alignment.CenterVertically)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "delete",
                                    tint = Color.parse("#36364D"),
                                    modifier = Modifier.size(18.dp)
                                )
                            }


                        }
                        Spacer(modifier = Modifier.height(32.dp))

                    }
                }
            }
            HorizontalDivider(color = Color.White.copy(alpha = 0.25f))
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .padding(start = 55.dp, end = 33.dp)
            ) {
                Column {
                    Text(
                        text = "Total",
                        fontWeight = FontWeight.W400,
                        fontSize = 15.sp,
                        color = Color.White,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Delivery",
                        fontWeight = FontWeight.W400,
                        fontSize = 15.sp,
                        color = Color.White,
                    )
                }
                Spacer(modifier = Modifier.width(170.dp))
                Column {
                    Text(
                        text = "$%,d us".format(cartDomainModel.total),
                        fontWeight = FontWeight.W700,
                        fontSize = 15.sp,
                        color = Color.White,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = cartDomainModel.delivery,
                        fontWeight = FontWeight.W700,
                        fontSize = 15.sp,
                        color = Color.White,

                        )
                }


            }
            Spacer(modifier = Modifier.height(15.dp))
            HorizontalDivider(
                color = Color.White.copy(alpha = 0.25f),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = { },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = EcommerceConceptAppTheme.colors.primaryColor,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
                    .height(54.dp)


            ) {
                Text(
                    text = "Checkout",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )

            }


        }


    }
}

@Composable
fun CartCountSelector(phoneCount: Int, onAdd: () -> Unit, onDelete: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(top = 5.dp)
            .background(
                color = Color.parse("#282843"), shape = RoundedCornerShape(26.dp)
            ),

        ) {

        IconButton(
            onClick = onDelete,

            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "decrease",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
        Crossfade(
            targetState = phoneCount, modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "$it",
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
        IconButton(
            onClick = onAdd,

            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "increase",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}


@Composable
private fun ScreenSlot(
    isConnected: Boolean,
    onBackScreen: () -> Unit,
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


        }, rightContent = {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically { fullHeight -> fullHeight },
                exit = slideOutVertically { fullHeight -> fullHeight },
            ) {
                Row {
                    Text(
                        text = "Add address",
                        fontWeight = FontWeight.W500,
                        fontSize = 15.sp,
                        color = EcommerceConceptAppTheme.colors.secondaryColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 15.dp)
                    )
                    IconButton(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 26.dp)
                            .background(
                                color = EcommerceConceptAppTheme.colors.primaryColor,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        onClick = { }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location_24),
                            contentDescription = "cart",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )

                    }
                }

            }

        })
        ConnectivityStatus(isConnected = isConnected, onBackOnline = {})
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
fun CartBoxPreview() {
    EcommerceConceptAppTheme {
//        CartBox(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.6f)
//        )
    }
}

