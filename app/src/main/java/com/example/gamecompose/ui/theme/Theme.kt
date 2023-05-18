import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.gamecompose.ui.theme.AppShapes
import com.example.gamecompose.ui.theme.Black1
import com.example.gamecompose.ui.theme.Gray300
import com.example.gamecompose.ui.theme.KaiseioptiTypography
import com.example.gamecompose.ui.theme.RedErrorDark
import com.example.gamecompose.ui.theme.RedErrorLight
import com.example.gamecompose.ui.theme.White

//package com.example.gamecompose.ui.theme
//
//import android.app.Activity
//import android.os.Build
//import androidx.compose.foundation.isSystemInDarkTheme
//import androidx.compose.material.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.SideEffect
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalView
//import androidx.core.view.WindowCompat
//
//private val DarkColorScheme = darkColorScheme(
//    //primary = Purple80,
//   // secondary = PurpleGrey80,
//    tertiary = Pink80,
//    primary = Color(0xFF546ee5),
//   // primaryVariant = Color(0xFF8c9cff),
//    onPrimary = White,
//    secondary = Color(0xFF46807E),
//   // secondaryVariant = Color(0xFF75b0ad),
//    error = RedErrorLight,
//    background = Color.Black,
//    onBackground = Color.White,
//    surface = Black1,
//    onSurface = Color.White,
//)
//
//private val LightColorScheme = lightColorScheme(
//    //primary = Purple40,
//    //secondary = PurpleGrey40,
//    tertiary = Pink40,
//    primary = Color(0xFF546ee5),
//    //primaryVariant = Color(0xFF8c9cff),
//    onPrimary = White,
//    secondary = Color(0xFF46807E),
//   // secondaryVariant = Color(0xFF75b0ad),
//    onSecondary = Black1,
//    error = RedErrorDark,
//    onError = RedErrorLight,
//    background = Color(0xFF070d2d),
//    onBackground = White,
//    surface = Color(0xFF161A37),
//    onSurface = Gray300,
//
//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)
//
//@Composable
//fun GameComposeTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//   val colorScheme = when {
//      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//         val context = LocalContext.current
//         if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//      }
//
//      darkTheme -> DarkColorScheme
//      else -> LightColorScheme
//   }
//   val view = LocalView.current
//   if (!view.isInEditMode) {
//      SideEffect {
//         val window = (view.context as Activity).window
//         window.statusBarColor = colorScheme.primary.toArgb()
//         WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//      }
//   }
//
//   MaterialTheme(
//       colorScheme = colorScheme,
//       typography = Typography,
//       content = content
//   )
//}

@Composable
fun GameComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
   MaterialTheme(
       colors = if (darkTheme) DarkThemeColors else LightThemeColors,
       typography = KaiseioptiTypography,
       shapes = AppShapes,
   ) {
      Box(
          modifier = Modifier
             .fillMaxSize()
             .background(color = MaterialTheme.colors.surface)
      ) {
         content()
      }
   }
}

@SuppressLint("ConflictingOnColor")
private val LightThemeColors = lightColors(
    primary = Color(0xFF546ee5),
    primaryVariant = Color(0xFF8c9cff),
    onPrimary = White,
    secondary = Color(0xFF46807E),
    secondaryVariant = Color(0xFF75b0ad),
    onSecondary = Black1,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Color(0xFF070d2d),
    onBackground = White,
    surface = Color(0xFF161A37),
    onSurface = Gray300,
)

private val DarkThemeColors = darkColors(
    primary = Color(0xFF546ee5),
    primaryVariant = Color(0xFF8c9cff),
    onPrimary = White,
    secondary = Color(0xFF46807E),
    secondaryVariant = Color(0xFF75b0ad),
    error = RedErrorLight,
    background = Color.Black,
    onBackground = Color.White,
    surface = Black1,
    onSurface = Color.White,
)