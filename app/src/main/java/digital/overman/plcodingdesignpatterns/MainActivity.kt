
package digital.overman.plcodingdesignpatterns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import digital.overman.plcodingdesignpatterns.components.MoodRateScreenDayNight
import digital.overman.plcodingdesignpatterns.ui.theme.PLCodingDesignPatternsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PLCodingDesignPatternsTheme {
                MoodRateScreenDayNight()
            }
        }
    }
}