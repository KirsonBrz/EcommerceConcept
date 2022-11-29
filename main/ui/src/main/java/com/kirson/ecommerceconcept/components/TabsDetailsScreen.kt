package com.kirson.ecommerceconcept.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.kirson.ecommerceconcept.entity.PhoneDetailDomainModel
import com.kirson.ecommerceconcept.main.ui.R
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme
import com.kirson.ecommerceconcept.utils.parse
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsScreenTabs(
    tabs: List<String>, pagerState: PagerState, phoneDetails: PhoneDetailDomainModel
) {
    val scope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = EcommerceConceptAppTheme.colors.primaryColor,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = EcommerceConceptAppTheme.colors.primaryColor
            )
        }) {
        tabs.forEachIndexed { index, tab ->

            Tab(
                text = {
                    Text(
                        text = tab,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                        color = EcommerceConceptAppTheme.colors.secondaryColor,
                        textAlign = TextAlign.Center,
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
    HorizontalPager(
        count = tabs.size,
        state = pagerState,
    ) { page ->
        when (page) {
            0 -> TabShopScreen(phoneDetails = phoneDetails)
            1 -> Box(Modifier.height(150.dp)) { Text("Details") }
            2 -> Box(Modifier.height(150.dp)) { Text("Features") }
        }
    }
}

@Composable
fun TabShopScreen(phoneDetails: PhoneDetailDomainModel) {

    val iconsShop = listOf(
        painterResource(id = R.drawable.cpu_28),
        painterResource(id = R.drawable.camera_28),
        painterResource(id = R.drawable.ssd_28),
        painterResource(id = R.drawable.sd_28),
    )
    val textsShop = listOf(
        phoneDetails.CPU, phoneDetails.camera, "     " + phoneDetails.ssd, "    " + phoneDetails.sd
    )
    Column {
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(4) {
                Column() {
                    Icon(
                        modifier = Modifier.padding(start = 15.dp),
                        painter = iconsShop[it],
                        contentDescription = "",
                        tint = Color.LightGray
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = textsShop[it],
                        fontSize = 11.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.LightGray,
                        textAlign = TextAlign.Center,
                    )
                }


            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Select color and capacity",
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            color = EcommerceConceptAppTheme.colors.secondaryColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(14.dp))
        Row {
            Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                ColorSelector(
                    colors = phoneDetails.color
                )
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                CapacitySelector(
                    capacities = phoneDetails.capacity
                )
            }
        }


    }


}

@Composable
fun ColorSelector(
    colors: List<String>
) {
    Row {
        var selectedColor by remember { mutableStateOf(colors.first()) }
        colors.forEach {

            ColorSwitchChip(colorText = it,
                selected = selectedColor == it,
                onSelectedChanged = { color ->
                    selectedColor = color
                })
            Spacer(modifier = Modifier.width(20.dp))


        }
    }

}

@Composable
fun ColorSwitchChip(
    colorText: String, selected: Boolean, onSelectedChanged: (String) -> Unit
) {

    val color by animateColorAsState(
        targetValue = if (selected) Color.White else Color.parse(colorText),
        animationSpec = tween(durationMillis = 250)
    )

    Surface(
        modifier = Modifier
            .clickable { onSelectedChanged(colorText) }
            .size(40.dp),
        color = Color.parse(colorText),
        shape = CircleShape
    ) {

        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "",
            tint = color,
            modifier = Modifier.padding(7.dp),
        )


    }

}



@Composable
fun CapacitySelector(
    capacities: List<String>
) {
    Row {
        var selectedCapacity by remember { mutableStateOf(capacities.first()) }
        capacities.forEach {

            CapacitySwitchChip(capacity = it,
                selected = selectedCapacity == it,
                onSelectedChanged = { capacity ->
                    selectedCapacity = capacity
                })
            Spacer(modifier = Modifier.width(20.dp))


        }
    }

}

@Composable
fun CapacitySwitchChip(
    capacity: String,
    selected: Boolean,
    onSelectedChanged: (String) -> Unit
) {
    val color by animateColorAsState(
        targetValue = if (selected) EcommerceConceptAppTheme.colors.primaryColor else Color.White,
        animationSpec = tween(durationMillis = 250)
    )
    Surface(
        modifier = Modifier
            .clickable { onSelectedChanged(capacity) }
            .size(width = 72.dp, height = 31.dp),
        color = color,
        contentColor = if (selected) Color.White else Color.LightGray,
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "$capacity gb",
            fontSize = 13.sp,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center
        )


    }

}


@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun DetailsScreenTabsPreview() {
    EcommerceConceptAppTheme {


        val tabs = listOf(
            "Shop", "Details", "Features"
        )
        val pagerState = rememberPagerState()
        DetailsScreenTabs(
            tabs = tabs, pagerState = pagerState, phoneDetails = PhoneDetailDomainModel(
                "sd", "sd", listOf("128", "256"), listOf("#772D03", "#010035"), "3", listOf(
                    "https://avatars.mds.yandex.net/get-mpic/5235334/img_id5575010630545284324.png/orig",
                    "https://www.manualspdf.ru/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.jpg"
                ), true, 1500, 4.5, "256 GB", "8 GB", "Galaxy Note 20 Ultra"

            )
        )
    }
}