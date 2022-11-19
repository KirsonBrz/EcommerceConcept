package com.kirson.baseproject.component

import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.kirson.baseproject.components.CountSelector
import com.kirson.baseproject.components.HorizontalDivider
import com.kirson.baseproject.components.mirroringBackIcon
import com.kirson.baseproject.main.ui.R
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

private val BottomBarHeight = 56.dp
private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun PhoneDetail(
    //snackId: Long,
    upPress: () -> Unit
) {
    // val snack = remember(snackId) { SnackRepo.getSnack(snackId) }
    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Header()
        Body(scroll)
        Title( /* snack*/) { scroll.value }
        ImageCollapse(R.drawable.phone_24) { scroll.value }
        Up(upPress)
        CartBottomBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(BaseProjectAppTheme.colors.tornado1))
    )
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Color.White,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = mirroringBackIcon(),
            tint = BaseProjectAppTheme.colors.mainColor,
            contentDescription = "back"
        )
    }
}

@Composable
private fun Body(

    scroll: ScrollState
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(Modifier.height(GradientScroll))
            Surface(Modifier.fillMaxWidth(), color = Color.White) {
                Column {
                    Spacer(Modifier.height(ImageOverlap))
                    Spacer(Modifier.height(TitleHeight))

                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "stringResource(R.string.detail_header)",
                        style = MaterialTheme.typography.overline,
                        color = BaseProjectAppTheme.colors.textSecondary,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(16.dp))
                    var seeMore by remember { mutableStateOf(true) }
                    Text(
                        text = "stringResource(R.string.detail_placeholder)",
                        style = MaterialTheme.typography.body1,
                        color = BaseProjectAppTheme.colors.textSecondary,
                        maxLines = if (seeMore) 5 else Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis,
                        modifier = HzPadding
                    )
                    val textButton = if (seeMore) {
                        "stringResource(id = R.string.see_more)\n" +
                                "stringResource(id = R.string.see_more)\n" +
                                "stringResource(id = R.string.see_more)\n" +
                                "stringResource(id = R.string.see_more)\n" +
                                "stringResource(id = R.string.see_more)\n"
                    } else {
                        "stringResource(id = R.string.see_less)" +
                                "stringResource(id = R.string.see_less)\n" +
                                "stringResource(id = R.string.see_less)\n"
                    }
                    Text(
                        text = textButton,
                        style = MaterialTheme.typography.button,
                        textAlign = TextAlign.Center,
                        color = BaseProjectAppTheme.colors.textTertiary,
                        modifier = Modifier
                            .heightIn(20.dp)
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                            .clickable {
                                seeMore = !seeMore
                            }
                    )
                    Spacer(Modifier.height(40.dp))
                    Text(
                        text = "ingredients",
                        style = MaterialTheme.typography.overline,
                        color = BaseProjectAppTheme.colors.textSecondary,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "\"CPU\": \"Exynos 990\",\n" +
                                "  \"camera\": \"108 + 12 mp\",\n" +
                                "  \"capacity\": [\n" +
                                "    \"126\",\n" +
                                "    \"252\"\n" +
                                "  ],\n" +
                                "  \"color\": [\n" +
                                "    \"#772D03\",\n" +
                                "    \"#010035\"\n" +
                                "  ],\n" +
                                "  \"id\": \"3\",\n" +
                                "  \"images\": [\n" +
                                "    \"https://avatars.mds.yandex.net/get-mpic/5235334/img_id5575010630545284324.png/orig\",\n" +
                                "    \"https://www.manualspdf.ru/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.jpg\"\n" +
                                "  ],\n" +
                                "  \"isFavorites\": true,\n" +
                                "  \"price\": 1500,\n" +
                                "  \"rating\": 4.5,\n" +
                                "  \"sd\": \"256 GB\",\n" +
                                "  \"ssd\": \"8 GB\",\n" +
                                "  \"title\": \"Galaxy Note 20 Ultra\"\n",
                        style = MaterialTheme.typography.body1,
                        color = BaseProjectAppTheme.colors.textSecondary,
                        modifier = HzPadding
                    )

                    Spacer(Modifier.height(16.dp))
                    HorizontalDivider()


                    Spacer(
                        modifier = Modifier
                            .padding(bottom = BottomBarHeight)
                            .navigationBarsPadding()
                            .height(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun Title(
    //snack: Snack,
    scrollProvider: () -> Int
) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .offset {
                val scroll = scrollProvider()
                val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
                IntOffset(x = 0, y = offset.toInt())
            }
            .background(color = Color.White)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Iphone 11", //phone.name
            style = MaterialTheme.typography.h4,
            color = BaseProjectAppTheme.colors.textSecondary,
            modifier = HzPadding
        )
        Text(
            text = "", //"phone.tagline",
            style = MaterialTheme.typography.subtitle2,
            fontSize = 20.sp,
            color = BaseProjectAppTheme.colors.textSecondary,
            modifier = HzPadding
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "$1500",
            style = MaterialTheme.typography.h6,
            color = BaseProjectAppTheme.colors.textPrimary,
            modifier = HzPadding
        )

        Spacer(Modifier.height(8.dp))
        HorizontalDivider()
    }
}

@Composable
private fun ImageCollapse(
    imageUrl: Int,
    scrollProvider: () -> Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }

    CollapsingImageLayout(
        collapseFractionProvider = collapseFractionProvider,
        modifier = HzPadding.then(Modifier.statusBarsPadding())
    ) {
        Icon(
            painter = painterResource(id = imageUrl),
            tint = BaseProjectAppTheme.colors.mainColor,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2, // centered when expanded
            constraints.maxWidth - imageWidth, // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}

fun lerp(start: Int, stop: Int, fraction: Float): Int {
    return start + ((stop - start) * fraction.toDouble()).roundToInt()
}

@Composable
private fun CartBottomBar(modifier: Modifier = Modifier) {
    val (count, updateCount) = remember { mutableStateOf(1) }
    Surface(modifier, color = Color.White) {
        Column {
            HorizontalDivider()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .navigationBarsPadding()
                    .then(HzPadding)
                    .heightIn(min = BottomBarHeight)
            ) {
                CountSelector(
                    count = count,
                    decreaseItemCount = { if (count > 0) updateCount(count - 1) },
                    increaseItemCount = { updateCount(count + 1) }
                )
                Spacer(Modifier.width(16.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                    onClick = { /* todo */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "add to cart",
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun SnackDetailPreview() {
    BaseProjectAppTheme {
        PhoneDetail(
            // snackId = 1L,
            upPress = { }
        )
    }
}