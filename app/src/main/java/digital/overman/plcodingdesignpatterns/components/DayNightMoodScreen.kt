package digital.overman.plcodingdesignpatterns.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import digital.overman.plcodingdesignpatterns.R
import digital.overman.plcodingdesignpatterns.ui.theme.PLCodingDesignPatternsTheme
import digital.overman.plcodingdesignpatterns.ui.theme.moodDarkBackground
import digital.overman.plcodingdesignpatterns.ui.theme.moodLightBackground

@Composable
fun MoodRaterScaffold(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) moodDarkBackground else moodLightBackground)
                .padding(padding)
        ) {
            content()
        }
    }
}
@Composable
fun MoodRateScreenDayNight() {
    MoodRaterScaffold(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),

        ) {

            TimeOfDayIcon(
                modifier = Modifier
                    .align(if (isSystemInDarkTheme()) Alignment.TopEnd else Alignment.TopStart)
                    .offset(y = 150.dp)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "How was your day?",
                    fontSize = 32.sp,
                    fontWeight = FontWeight(600),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 12.dp)
                )

                MoodRater(modifier = Modifier)
            }
        }
    }
}

@Composable
fun TimeOfDayIcon(
    modifier: Modifier = Modifier
) {
    val dark = isSystemInDarkTheme()
    Image(
        imageVector = ImageVector.vectorResource(
            id = if (dark) R.drawable.moodrater_moon else R.drawable.moodrater_sun
        ),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun MoodRater(
    modifier: Modifier = Modifier
) {
    var rating by remember { mutableIntStateOf(3) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape)
            .padding(horizontal = 32.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(5) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_star_24),
                contentDescription = null,
                tint = when {
                    it < rating -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.tertiary
                },
                modifier = Modifier
                    .size(48.dp)
                    .clickable(
                        interactionSource = null,
                        indication = null
                    ) {
                        rating = it + 1
                    }
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun MoodRateScreenDayNightPreview() {
    PLCodingDesignPatternsTheme {
        MoodRateScreenDayNight()
    }
}