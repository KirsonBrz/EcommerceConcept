package com.kirson.ecommerceconcept.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.dp
import com.kirson.ecommerceconcept.core.ui.uikit.R
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String?,
    onNavigationIconClick: () -> Unit,
    navigationIcon: NavigationIcon = NavigationIcon.ArrowBack,
    trailingContent: (@Composable () -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        leftContent = {
            if (navigationIcon != NavigationIcon.None) {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(
                        imageVector = if (navigationIcon == NavigationIcon.Close) Icons.Default.Close else Icons.Default.ArrowBack,
                        contentDescription = "back or close icon"
                    )
                }
            }
        },
        centerContent = {
            if (title != null) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = title,
                    style = EcommerceConceptAppTheme.typography.title1,
                )
            }
        },
        rightContent = {
            trailingContent?.invoke()
        }
    )
}

@Composable
fun LogoTopAppBar(
    modifier: Modifier = Modifier,
    leftContent: @Composable () -> Unit = {},
    rightContent: @Composable () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        leftContent = leftContent,
        centerContent = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_check_24),
                    contentDescription = "logo image",
                )
            }
        },
        rightContent = rightContent,
    )
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    leftContent: @Composable () -> Unit,
    centerContent: @Composable () -> Unit,
    rightContent: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier
            .height(APP_BAR_HEIGHT_DP.dp)
            .fillMaxWidth(),
        content = {
            Box(contentAlignment = Alignment.Center) { leftContent() }
            Box(contentAlignment = Alignment.Center) { centerContent() }
            Box(contentAlignment = Alignment.Center) { rightContent() }
        }
    ) { measureables, constraints ->
        val leftPlaceable = measureables[0].measure(constraints.copy(minWidth = 0))
        val rightPlaceable = measureables[2].measure(constraints.copy(minWidth = 0))
        val maxSideSlotWidth = maxOf(leftPlaceable.width, rightPlaceable.width)
        val centerPlaceable = measureables[1].measure(
            constraints.copy(
                minWidth = constraints.maxWidth - maxSideSlotWidth * 2,
                maxWidth = constraints.maxWidth - maxSideSlotWidth * 2
            )
        )
        val maxChildHeight =
            maxOf(leftPlaceable.height, centerPlaceable.height, rightPlaceable.height)
        layout(width = constraints.maxWidth, maxChildHeight) {
            val height = constraints.constrainHeight(maxChildHeight)
            leftPlaceable.place(x = 0, y = height / 2 - leftPlaceable.height / 2)
            centerPlaceable.place(x = maxSideSlotWidth, y = height / 2 - centerPlaceable.height / 2)
            rightPlaceable.place(
                x = maxSideSlotWidth + centerPlaceable.width,
                y = height / 2 - rightPlaceable.height / 2
            )
        }
    }
}

enum class NavigationIcon {
    Close,
    ArrowBack,
    None,
}

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true, widthDp = 360)
@Composable
private fun TopAppBarPreview() {
    EcommerceConceptAppTheme {
        TopAppBar(
            leftContent = {
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "hello",
                    style = EcommerceConceptAppTheme.typography.caption1
                )
            },
            centerContent = {
                Text(
                    text = "Centered title",
                    textAlign = TextAlign.Center,
                    style = EcommerceConceptAppTheme.typography.title1,
                )
            },
            rightContent = {
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "hello longer",
                    style = EcommerceConceptAppTheme.typography.caption1
                )
            }
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true, widthDp = 360)
@Composable
private fun TopAppBarPreviewWithNavigation() {
    EcommerceConceptAppTheme {
        Column {
            TopAppBar(
                title = "Title",
                onNavigationIconClick = {},
                navigationIcon = NavigationIcon.Close
            )
            TopAppBar(
                title = "Title",
                onNavigationIconClick = {},
                navigationIcon = NavigationIcon.None
            )
            Spacer(modifier = Modifier.height(16.dp))
            TopAppBar(
                title = null,
                onNavigationIconClick = {},
                navigationIcon = NavigationIcon.ArrowBack
            )
            Spacer(modifier = Modifier.height(16.dp))
            TopAppBar(
                title = "Title",
                onNavigationIconClick = {},
                navigationIcon = NavigationIcon.Close,
                trailingContent = {}
            )
        }
    }
}

const val APP_BAR_HEIGHT_DP = 52