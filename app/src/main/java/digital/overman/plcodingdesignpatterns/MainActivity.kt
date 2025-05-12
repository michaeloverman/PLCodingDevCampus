
package digital.overman.plcodingdesignpatterns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import digital.overman.plcodingdesignpatterns.components.SingleRowSelector
import digital.overman.plcodingdesignpatterns.components.Thousands
import digital.overman.plcodingdesignpatterns.ui.theme.PLCodingDesignPatternsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PLCodingDesignPatternsTheme {
//                MoodRateScreenDayNight()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFEF7FF))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Thousands separator",
                    )
                    SingleRowSelector(
                        items = listOf(Thousands.PERIOD, Thousands.COMMA, Thousands.SPACE),
                        onItemSelected = {}
                    )
                }
            }
        }
    }
}