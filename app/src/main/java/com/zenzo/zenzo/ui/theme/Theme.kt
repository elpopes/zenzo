package com.zenzo.zenzo.ui.theme

//import android.app.Activity
//import androidx.compose.foundation.isSystemInDarkTheme
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.darkColors
//import androidx.compose.material.lightColors
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.SideEffect
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.platform.LocalView
//import androidx.core.view.WindowCompat
//
//private val DarkColorPalette = darkColors(
//    primary = Color.Black, // Change primary color to black
//    secondary = Color.White // Change secondary color to white
//)
//
//private val LightColorPalette = lightColors(
//    primary = Color.Black, // Change primary color to black
//    secondary = Color.White // Change secondary color to white
//)

//@Composable
//fun ZenzoTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
//
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colors.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
//
//    MaterialTheme(
//        colors = colors,
//        typography = Typography,
//        content = content
//    )
//}
