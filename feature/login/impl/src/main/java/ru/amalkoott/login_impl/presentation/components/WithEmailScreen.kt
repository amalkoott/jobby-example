package ru.amalkoott.login_impl.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.amalkoott.common.R

@Composable
fun WithEmailScreen(
    modifier: Modifier,
    argument: String,
    onClick: () -> Unit
){
    val toastPlaceholder = stringResource(R.string.WIPPlaceholder)
    val text = argument
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(
                start = dimensionResource(R.dimen.with_email_layout_padding_horizontal),
                top = dimensionResource(R.dimen.with_email_layout_padding_top),
                end = dimensionResource(R.dimen.with_email_layout_padding_horizontal)
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.with_email_element_padding_vertical)),
    ) {
        Text(
            text = stringResource(R.string.enterWithEmailTitle, text),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
        )
        Text(
            text = stringResource(R.string.enterWithEmailDescription),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
            fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.with_email_code_box_horizontal_padding)),
            modifier = Modifier
                .fillMaxWidth()
        ){
            for (i in 0..3) {
                Box(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.with_email_code_box_size))
                        .background(MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(dimensionResource(R.dimen.with_email_code_box_corner)))
                        .fillMaxWidth()
                        .clickable { Toast.makeText(context,toastPlaceholder, Toast.LENGTH_SHORT).show() }
                )
            }
        }
        Button(
            onClick = onClick ,
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSurface,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary,
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.with_email_enter_btn_corner)),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.enterEmailCodeBtn),
                fontSize = MaterialTheme.typography.displayLarge.fontSize,
                fontWeight = MaterialTheme.typography.displayLarge.fontWeight,
                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
            )
        }
    }

}