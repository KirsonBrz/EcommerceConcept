package com.kirson.ecommerceconcept.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme
import com.kirson.ecommerceconcept.utils.CategoryItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SelectCategoryTabs(tabs: List<CategoryItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        edgePadding = 5.dp,
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = EcommerceConceptAppTheme.colors.backgroundPrimary,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = EcommerceConceptAppTheme.colors.primaryColor,
                height = 0.dp
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                modifier = Modifier
                    .height(140.dp)
                    .padding(top = 35.dp),
                icon = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(71.dp)
                            .background(
                                color = if (pagerState.currentPage == index) EcommerceConceptAppTheme.colors.primaryColor else Color.White,
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            modifier = Modifier.size(35.dp),
                            tint = if (pagerState.currentPage == index) Color.White else Color.LightGray,
                            painter = painterResource(id = tab.imageId),
                            contentDescription = ""
                        )
                    }

                },
                text = {
                    Text(
                        text = tab.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                        color = if (pagerState.currentPage == index) EcommerceConceptAppTheme.colors.primaryColor else EcommerceConceptAppTheme.colors.secondaryColor,
                        textAlign = TextAlign.Center
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
        // TODO: page content
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun SelectCategoryTabsPreview() {
    EcommerceConceptAppTheme {


        val tabs = listOf(
            CategoryItem.Phones,
            CategoryItem.Computer,
            CategoryItem.Health,
            CategoryItem.Books,
            CategoryItem.Tools,
            CategoryItem.TV,
        )
        val pagerState = rememberPagerState()
        SelectCategoryTabs(tabs = tabs, pagerState = pagerState)
    }
}