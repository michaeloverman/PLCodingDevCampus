package digital.overman.plcodingdesignpatterns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import digital.overman.plcodingdesignpatterns.components.DataBoxItem
import digital.overman.plcodingdesignpatterns.components.MarsWeather
import digital.overman.plcodingdesignpatterns.components.MarsWeatherData
import digital.overman.plcodingdesignpatterns.ui.theme.PLCodingDesignPatternsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                        )
                    )
                )
//                MoodRateScreenDayNight()
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color(0xFFFEF7FF))
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.Center,
//                ) {
//                    Text(
//                        text = "Thousands separator",
//                    )
//                    SingleRowSelector(
//                        items = listOf(Thousands.PERIOD, Thousands.COMMA, Thousands.SPACE),
//                        onItemSelected = {}
//                    )
//                }
            }
        }
    }
}