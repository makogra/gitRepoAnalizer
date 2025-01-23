package com.example.demokmp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demokmp.ui.AppColors

//@Composable
//fun MyApplicationTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    val colors = if (darkTheme) {
//        darkColorScheme(
//            primary = Color(0xFFBB86FC),
//            secondary = Color(0xFF03DAC5),
//            tertiary = Color(0xFF3700B3)
//        )
//    } else {
//        lightColorScheme(
//            primary = Color(0xFF6200EE),
//            secondary = Color(0xFF03DAC5),
//            tertiary = Color(0xFF3700B3)
//        )
//    }
//    val typography = Typography(
//        bodyMedium = TextStyle(
//            fontFamily = FontFamily.Default,
//            fontWeight = FontWeight.Normal,
//            fontSize = 16.sp
//        )
//    )
//    val shapes = Shapes(
//        small = RoundedCornerShape(4.dp),
//        medium = RoundedCornerShape(4.dp),
//        large = RoundedCornerShape(0.dp)
//    )
//
//    MaterialTheme(
//        colorScheme = colors,
//        typography = typography,
//        shapes = shapes,
//        content = content
//    )
//}


@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = getColorScheme(darkTheme)
    val typography = getTypography()
    val shapes = getShapes()
    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
@Composable
fun getColorScheme(darkTheme: Boolean): ColorScheme {
    return if (darkTheme) {
        darkColorScheme(
            primary = AppColors.DarkPrimary,
            secondary = AppColors.DarkSecondary,
            tertiary = AppColors.DarkTertiary,
            background = AppColors.DarkBackground,
            error = AppColors.DarkError,
            onPrimary = Color.Black,
            onSecondary = Color.Black,
            onTertiary = Color.Black,
            onBackground = Color.White,
            onError = Color.White
        )
    } else {
        lightColorScheme(
            primary = AppColors.LightPrimary,
            secondary = AppColors.LightSecondary,
            tertiary = AppColors.LightTertiary,
            background = AppColors.LightBackground,
            error = AppColors.LightError,
            onPrimary = Color.White,
            onSecondary = Color.White,
            onTertiary = Color.Black,
            onBackground = Color.Black,
            onError = Color.White
        )
    }
}

@Composable
fun getTypography(): Typography {
    return Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
}
@Composable
fun getShapes(): Shapes {
    return Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )
}