package digital.overman.plcodingdesignpatterns.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import digital.overman.plcodingdesignpatterns.ui.theme.PLCodingDesignPatternsTheme

@Composable
fun SingleRowSelector(
    title: String,
    items: List<String>,
    selectedIndex: Int = 0,
    onItemSelected: ((Int) -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    selectorColor: Color = Color.White,
) {
    var selected by remember { mutableIntStateOf(selectedIndex) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(4.dp)
            ,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { i, item ->
                Text(
                    text = item,
                    color = if (i == selected) contentColorFor(selectorColor) else contentColorFor(backgroundColor),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            selected = i
                            onItemSelected?.invoke(i)
                        }
                        .background(
                            color = if (i == selected) selectorColor else backgroundColor,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .wrapContentWidth()
                        .padding(12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SingleRowSelectorPreview() {
    PLCodingDesignPatternsTheme {
        SingleRowSelector(
            title = "Thousands separator",
            items = listOf(
                "1,000", "1.000", "1 000"
            ),
            onItemSelected = {}
        )
    }
}