package com.kirson.baseproject.component

import androidx.compose.animation.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirson.baseproject.entity.CategoryModel
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme

@Composable
fun SelectCategoryList(
    categories: List<CategoryModel>,
    selectedCategory: CategoryModel,
    onSelectedChanged: (CategoryModel) -> Unit
) {

    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 21.dp, bottom = 5.dp)
        ) {
            Text(
                text = "Select Category",
                fontSize = 25.sp,
                fontWeight = FontWeight.W700,
                color = BaseProjectAppTheme.colors.backgroundSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterStart)
            )


            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                Text(
                    text = "view all",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                    color = BaseProjectAppTheme.colors.mainColor,
                    textAlign = TextAlign.Center

                )
            }
        }
        LazyRow {
            items(categories) {
                CategoryChip(
                    category = it,
                    isSelected = selectedCategory == it,
                    onSelectionChanged = onSelectedChanged
                )
            }
        }
    }
}

@Composable
fun CategoryChip(
    category: CategoryModel,
    isSelected: Boolean,
    onSelectionChanged: (CategoryModel) -> Unit
) {
    // Start out white and animate to orange based on `isSelected`
    val colorIcon = remember { Animatable(Color.Gray) }
    val colorText = remember { Animatable(Color.Gray) }
    LaunchedEffect(isSelected) {
        colorIcon.animateTo(if (isSelected) Color(0xFFFF6E4E) else Color.White)
        colorText.animateTo(if (isSelected) Color(0xFFFF6E4E) else Color.Black)
    }

    Column {
        Surface(
            modifier = Modifier
                .padding(9.dp)
                .size(60.dp),
            shadowElevation = 8.dp,
            shape = CircleShape,
            color = colorIcon.value
        ) {
            Column(
                modifier = Modifier.toggleable(value = isSelected,
                    onValueChange = {
                        onSelectionChanged(category)
                    })
            ) {
                Icon(
                    painter = painterResource(category.imageId),
                    contentDescription = null,
                    tint = if (isSelected) Color.White else Color.LightGray,
                    modifier = Modifier
                        .padding(15.dp)
                        .size(35.dp)

                )

            }
        }
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodyMedium,
            color = colorText.value,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }


}
