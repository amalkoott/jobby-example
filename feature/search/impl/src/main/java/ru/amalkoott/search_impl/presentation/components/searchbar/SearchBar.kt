package ru.amalkoott.search_impl.presentation.components.searchbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.amalkoott.common.R

@Composable
fun SearchBar(
    strategy: SearchBarStrategy,
    onActionClick: () -> Unit,
    onFilterClick: () -> Unit
) {
    strategy.display(onActionClick,onFilterClick)
}

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: Int?,
    modifier: Modifier = Modifier,
    textColor: Color,
    contentColor: Color,
    toolColor: Color,
    backgroundColor: Color,
    placeholder: String = "",
    placeholderColor: Color,
    padding: Dp,
    navigateAction: () -> Unit,
    extraAction: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(
                horizontal = dimensionResource(R.dimen.search_bar_horizontal_padding)
            )){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.search_bar_element_padding)),
            modifier = Modifier
                .fillMaxHeight()
                .background(backgroundColor, RoundedCornerShape(dimensionResource(R.dimen.search_bar_corner)))
                .padding(
                    horizontal = padding + 4.dp,
                    vertical = padding
                )
                .weight(1f)) {

            if(leadingIcon != null){
                Icon(
                    painter = painterResource(leadingIcon),"",
                    Modifier.size(dimensionResource(R.dimen.ic_back_navigation_size)).clickable { navigateAction() },
                    tint = contentColor)
            }

            val textModifier = Modifier
                .padding(
                    start = if (leadingIcon != null) dimensionResource(R.dimen.search_bar_placeholder_padding) else 0.dp,
                    end = dimensionResource(R.dimen.search_bar_placeholder_padding),
                    top = dimensionResource(R.dimen.search_bar_placeholder_padding),
                    bottom = dimensionResource(R.dimen.search_bar_placeholder_padding)
                )

            Box{
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(
                        color = textColor
                    ),
                    modifier = textModifier.fillMaxWidth(),
                    maxLines = 1,)
                if (value === "") {
                    Text(
                        text = placeholder,
                        color = placeholderColor,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = textModifier.fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Box(modifier = Modifier
            .size(
                dimensionResource(R.dimen.ic_filter_box_size)
            )
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(dimensionResource(R.dimen.ic_filter_box_corner))
            ),
            contentAlignment = Alignment.Center){
            Icon(
                painter = painterResource(R.drawable.ic_launcher_filter_foreground),
                contentDescription = "filters",
                modifier = Modifier.size(dimensionResource(R.dimen.ic_filter_size)),
                tint = toolColor)
        }
    }
}