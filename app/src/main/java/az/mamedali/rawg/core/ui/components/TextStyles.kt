package az.mamedali.rawg.core.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TextTitleLarge(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        text = text
    )
}

@Composable
fun TextTitleMedium(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
        text = text
    )
}

@Composable
fun TextTitleSmall(
    modifier: Modifier = Modifier,
    text: String,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.titleSmall,
        overflow = overflow,
        maxLines = maxLines,
        text = text
    )
}

@Composable
fun TextLabelLarge(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.labelLarge,
        text = text
    )
}

@Composable
fun TextLabelMedium(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.labelMedium,
        text = text
    )
}

@Composable
fun TextLabelSmall(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.labelSmall,
        text = text
    )
}

@Composable
fun TextHeadlineMedium(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium,
        text = text
    )
}

@Composable
fun TextHeadlineSmall(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall,
        text = text
    )
}

@Composable
fun TextBodySmall(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        text = text
    )
}

@Composable
fun TextBodyMedium(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        text = text
    )
}

@Composable
fun TextBodyLarge(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        text = text
    )
}