package digital.overman.plcodingdesignpatterns.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import digital.overman.plcodingdesignpatterns.ui.theme.PLCodingDesignPatternsTheme

@Composable
fun <T> SingleRowSelector(
    items: List<T>,
    selectedIndex: Int = 0,
    onItemSelected: ((T) -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    selectorColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(16.dp),
    selectorPadding: Dp = 4.dp,
    itemPadding: Dp = 12.dp
) {
    var selected by remember { mutableIntStateOf(selectedIndex) }
    val density = LocalDensity.current
    var rowWidth by remember { mutableIntStateOf(0) }
    var rowHeight by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = backgroundColor,
                shape = shape
            )
            .padding(selectorPadding)
            .onSizeChanged {
                rowWidth = it.width
                rowHeight = it.height
            }
    ) {
        if (rowWidth > 0) {
            val selectorWidth = rowWidth / items.size
            val selectorDp = with(density) { selectorWidth.toDp() }
            val selectorHeight = with(density) { rowHeight.toDp() }
            val offsetX by animateIntAsState(
                targetValue = selected * selectorWidth,
                label = "offsetX"
            )

            Box(
                modifier = Modifier
                    .offset { IntOffset(x = offsetX, y = 0) }
                    .size(width = selectorDp, height = selectorHeight)
                    .background(selectorColor, shape)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { i, item ->
                val textColor by animateColorAsState(
                    targetValue = if (i == selected) Color(0xFF1B1B1C)
//                    targetValue = if (i == selected) contentColorFor(selectorColor)
                    else MaterialTheme.colorScheme.onSurface,
                    label = "textColor"
                )

                Text(
                    text = item.toString(),
                    color = textColor,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(interactionSource = null, indication = null) {
                            selected = i
                            onItemSelected?.invoke(item)
                        }
                        .wrapContentWidth()
                        .padding(itemPadding)
                )
            }
        }
    }
}

enum class Thousands(private val item: String) {
    COMMA("1,000"), PERIOD("1.000"), SPACE("1 000");

    override fun toString(): String {
        return item
    }
}

@Preview(showBackground = true)
@Composable
private fun SingleRowSelectorPreview() {
    PLCodingDesignPatternsTheme {
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