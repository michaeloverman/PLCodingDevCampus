package digital.overman.plcodingdesignpatterns.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import digital.overman.plcodingdesignpatterns.R
import digital.overman.plcodingdesignpatterns.ui.theme.PLCodingDesignPatternsTheme
import digital.overman.plcodingdesignpatterns.ui.theme.moodDarkBackground
import digital.overman.plcodingdesignpatterns.ui.theme.weatherCardOrangeText
import digital.overman.plcodingdesignpatterns.ui.theme.weatherCardPrimaryText
import digital.overman.plcodingdesignpatterns.ui.theme.weatherCardPurpleText
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
    val dataBoxList: List<DataBoxItem>,
)

@Composable
fun MarsWeather(
    data: MarsWeatherData
) {
    MarsWeatherScaffold {
        MarsWeatherCard(
            data = data,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}

class CutTopRightCornerShape(
    private val cutCornerSize: Dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cornerCut = with(density) { cutCornerSize.toPx() }
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width - cornerCut, 0f)
            lineTo(size.width, cornerCut)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun MarsWeatherCard(
    data: MarsWeatherData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(310.dp),
        shape = CutTopRightCornerShape(24.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            IconLabel(
                title = data.location,
                icon = R.drawable.map_point,
                color = weatherCardPurpleText,
                iconAlpha = 0.3f
            )

            Spacer(modifier = Modifier.height(86.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
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

                Column(modifier = Modifier.padding(bottom = 10.dp)) {
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
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(data.dataBoxList) { DataBox(it) }
            }
        }
    }
}

data class DataBoxItem(
    @StringRes val label: Int,
    val data: String
)

@Composable
fun DataBox(
    data: DataBoxItem
) {
    Column(
        modifier = Modifier
            .width(width = 135.dp)
            .background(Color(0xFFF9E8E5))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = stringResource(data.label),
            color = weatherCardOrangeText,
            fontSize = 12.sp,
            fontFamily = FontFamily.Monospace
        )

        Text(
            text = data.data,
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
                dataBoxList = listOf(
                    DataBoxItem(R.string.wind_speed_label, "27 km/h NW"),
                    DataBoxItem(R.string.pressure_label, "600 Pa"),
                    DataBoxItem(R.string.label_uv_radiation, "0.5 mSv/day"),
                    DataBoxItem(R.string.martian_date_label, "914 Sol")
                ),
            )
        )
    }
}