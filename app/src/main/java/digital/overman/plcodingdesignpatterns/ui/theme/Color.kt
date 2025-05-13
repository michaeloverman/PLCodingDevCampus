package digital.overman.plcodingdesignpatterns.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val activeStar = Color(0xFFFF9334)
val inactiveLightStar = Color(0xFFD7E4F7)
val inactiveDarkStar = Color(0xFF482D6D)
val moodDarkSurface = Color(0xFF2C144D)
val moodLightSurface = Color(0xFFFFFFFF)
val moodLightOnSurface = Color(0xFF14042B)
val moodDarkOnSurface = Color(0xFFE6E5FE)

val moodDarkBackground = Brush.verticalGradient(
    listOf(
        Color(0xFF210A41),
        Color(0xFF120327),
    )
)

val moodLightBackground = Brush.verticalGradient(
    listOf(
        Color(0xFFC6E6FE),
        Color(0xFF6689F9),
    )
)

val weatherCardPrimaryText = Color(0xFF14171E)
val weatherCardSecondaryText = Color(0xFF474F63)
val weatherCardOrangeText = Color(0xFFCD533C)
val weatherCardPurpleText = Color(0xFF9E83C5)