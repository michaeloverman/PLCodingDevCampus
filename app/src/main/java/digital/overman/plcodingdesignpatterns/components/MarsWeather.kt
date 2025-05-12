package digital.overman.plcodingdesignpatterns.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import digital.overman.plcodingdesignpatterns.R
import digital.overman.plcodingdesignpatterns.ui.theme.PLCodingDesignPatternsTheme
import digital.overman.plcodingdesignpatterns.ui.theme.moodDarkBackground
import digital.overman.plcodingdesignpatterns.ui.theme.weatherCardOrangeText
import digital.overman.plcodingdesignpatterns.ui.theme.weatherCardPrimaryText
import digital.overman.plcodingdesignpatterns.ui.theme.weatherCardSecondaryText

@Composable
fun MarsWeatherScaffold(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(moodDarkBackground)
                .padding(padding)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.mars_surface),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(413.dp),
                contentScale = ContentScale.Crop
            )
            content()
        }
    }
}

data class MarsWeatherData(
    val location: String,
    val description: String,
    @DrawableRes val descriptionIconResource: Int,
    val currentTemp: Int,
    val highTemp: Int,
    val lowTemp: Int,
    val windSpeedDirString: String,
    val pressureString: String,
    val uvRadiationString: String,
    val martianData: String
)

@Composable
fun MarsWeather(
    data: MarsWeatherData
) {
    MarsWeatherScaffold {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            MarsWeatherCard(data)
        }
    }
}

@Composable
fun MarsWeatherCard(
    data: MarsWeatherData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(310.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White
        )
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            IconLabel(
                title = data.location,
                icon = R.drawable.map_point,
                color = Color(0xFF9E83C5),
                iconAlpha = 0.3f
            )

            Spacer(modifier = Modifier.height(86.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    IconLabel(
                        title = data.description,
                        icon = data.descriptionIconResource,
                        color = weatherCardOrangeText
                    )

                    Row(
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = data.currentTemp.toString(),
                            fontSize = 64.sp,
                            color = weatherCardPrimaryText
                        )
                        Text(
                            text = "°C",
                            fontSize = 24.sp,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    MonospaceLabel(
                        text = "H:${data.highTemp}°C",
                        color = weatherCardSecondaryText
                    )
                    MonospaceLabel(
                        text = "L:${data.lowTemp}°C",
                        color = weatherCardSecondaryText
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    listOf(
                        Pair("Wind speed", data.windSpeedDirString),
                        Pair("Pressure", data.pressureString),
                        Pair("UV Radiation", data.uvRadiationString),
                        Pair("Martian date", data.martianData)
                    )
                ) { (label, data) ->
                    DataBox(label, data)
                }
            }
        }
    }
}

@Composable
fun DataBox(
    label: String,
    data: String
) {
    Column(
        modifier = Modifier
            .width(width = 135.dp)
            .background(Color(0xFFF9E8E5))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = label,
            color = weatherCardOrangeText,
            fontSize = 12.sp,
            fontFamily = FontFamily.Monospace
        )

        Text(
            text = data,
            color = weatherCardPrimaryText,
            fontSize = 16.sp
        )
    }
}

@Composable
fun IconLabel(
    title: String,
    @DrawableRes icon: Int,
    color: Color,
    iconAlpha: Float = 1f
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            painter = painterResource(icon),//ImageVector.vectorResource(icon),
            contentDescription = null,
            tint = color.copy(alpha = iconAlpha)
        )

        MonospaceLabel(
            text = title,
            color = color,
        )
    }
}

@Composable
fun MonospaceLabel(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Text(
        text = text,
        color = color,
        fontSize = 14.sp,
        fontFamily = FontFamily.Monospace
    )
}

@Preview
@Composable
private fun MarsWeatherPreview() {
    PLCodingDesignPatternsTheme {
        MarsWeather(
            MarsWeatherData(
                location = "Olympus Mons",
                description = "Dust Storm",
                descriptionIconResource = R.drawable.ic_action_name,
                currentTemp = -63,
                highTemp = -52,
                lowTemp = -73,
                windSpeedDirString = "27 km/h NW",
                pressureString = "600 Pa",
                uvRadiationString = "0.5 mSv/day",
                martianData = "914 Sol"
            )
        )
    }
}