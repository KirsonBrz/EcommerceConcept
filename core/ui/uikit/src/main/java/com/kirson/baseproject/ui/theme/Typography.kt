package com.kirson.baseproject.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kirson.baseproject.core.ui.uikit.R

private val Roboto = FontFamily(
  Font(R.font.roboto_regular, FontWeight.Normal),
  Font(R.font.roboto_medium, FontWeight.Medium),
  Font(R.font.roboto_bold, FontWeight.Bold),
)

@Immutable
data class AppTypography internal constructor(
  val h2: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 32.sp,
  ),
  val h4: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
  ),
  val title1: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp,
  ),
  val title2: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
  ),
  val title3: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp,
  ),
  val subtitle1: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Bold,
    fontSize = 15.sp,
  ),
  val subtitle2: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp,
  ),
  val subtitle3: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
  ),
  val button1: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
  ),
  val button2: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp,
  ),
  val text1: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 17.sp,
  ),
  val text2: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 17.sp,
  ),
  val text3: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
  ),
  val text4: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
  ),
  val caption1: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 13.sp,
  ),
  val caption2: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
  ),
  val caption3: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
  ),
  val caption4: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 11.sp,
  ),
)

/**
 * Used for fallback only and shouldn't be used generally, use [AppTypography] instead.
 */
val MaterialTypography = Typography()

internal val LocalAppTypography = staticCompositionLocalOf { AppTypography() }
