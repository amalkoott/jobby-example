package ru.amalkoott.login_impl.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.amalkoott.login_impl.presentation.components.EnterTextField
import ru.amalkoott.common.R


@Composable
fun LoginScreen(
    modifier: Modifier,
    onWithEmail: (String) -> Unit,
    onWithPassword: () -> Unit
){
    Column(
        modifier = modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val toastPlaceholder = stringResource(R.string.WIPPlaceholder)
        val context = LocalContext.current
        val emailPlaceholder = stringResource(R.string.loginEmailPlaceholder)
        val text = remember { mutableStateOf(emailPlaceholder) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = dimensionResource(R.dimen.login_layout_padding_top),
                    bottom = dimensionResource(R.dimen.login_layout_padding_bottom),
                    start = dimensionResource(R.dimen.login_layout_padding_start),
                    end = dimensionResource(R.dimen.login_layout_padding_end)
                ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.loginIntro),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = MaterialTheme.colorScheme.onBackground
            )
            Column(
                modifier = Modifier
                    .padding(
                        top = dimensionResource(R.dimen.login_space_between_intro)
                    )
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.login_card_corner)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = dimensionResource(R.dimen.login_card_padding_bottom)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                top = dimensionResource(R.dimen.login_card_content_padding_vertical),
                                bottom = dimensionResource(R.dimen.login_card_content_padding_vertical),
                                start = dimensionResource(R.dimen.login_card_content_padding_horizontal),
                                end = dimensionResource(R.dimen.login_card_content_padding_horizontal)
                            )
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(R.string.loginTitle),
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            fontFamily = MaterialTheme.typography.titleMedium.fontFamily
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            EnterTextField(
                                value = text.value,
                                onValueChange = { text.value = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = dimensionResource(R.dimen.login_card_enterfield_padding_top)
                                    )
                                    .height(
                                        dimensionResource(R.dimen.login_card_enterfield_height)
                                    ),
                                textColor = MaterialTheme.colorScheme.onSecondary,
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                placeholder = "",
                                placeholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(
                                        top = dimensionResource(R.dimen.login_card_btn_padding_top)
                                    )
                                    .fillMaxWidth()
                            ) {
                                Button(
                                    onClick = { if (text.value !== "") onWithEmail(text.value) },
                                    colors = if(text.value !== "") ButtonColors(
                                        containerColor = MaterialTheme.colorScheme.secondary,
                                        contentColor = MaterialTheme.colorScheme.onSurface,
                                        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                        disabledContentColor = MaterialTheme.colorScheme.onSecondary,
                                    ) else ButtonColors(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                        disabledContentColor = MaterialTheme.colorScheme.onSecondary,
                                    ),
                                    shape = RoundedCornerShape(dimensionResource(R.dimen.login_card_enter_btn_corner)),
                                    modifier = Modifier
                                        .weight(1f)
                                ) {
                                    Text(
                                        modifier = Modifier,
                                        text = stringResource(R.string.enterWithEmailBtn),
                                        fontSize = MaterialTheme.typography.displaySmall.fontSize,
                                        fontWeight = MaterialTheme.typography.displaySmall.fontWeight,
                                        fontFamily = MaterialTheme.typography.displaySmall.fontFamily,
                                    )
                                }

                                Text(
                                    modifier = Modifier
                                        .padding(
                                            start = dimensionResource(R.dimen.login_card_password_btn_padding_start)
                                        )
                                        .clickable {
                                            Toast.makeText(context, toastPlaceholder, Toast.LENGTH_SHORT).show()
                                        }
                                    ,
                                    text = stringResource(R.string.enterWithPasswordBtn),
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                                    fontWeight = MaterialTheme.typography.displaySmall.fontWeight,
                                    fontFamily = MaterialTheme.typography.displaySmall.fontFamily,
                                )
                            }
                        }
                    }
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    shape = RoundedCornerShape(
                        dimensionResource(R.dimen.login_card_corner)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Column(
                        modifier = Modifier
                            .padding(
                                top = dimensionResource(R.dimen.login_card_content_padding_vertical),
                                bottom = dimensionResource(R.dimen.login_card_content_padding_vertical),
                                start = dimensionResource(R.dimen.login_card_content_padding_horizontal),
                                end = dimensionResource(R.dimen.login_card_content_padding_horizontal)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(
                                    bottom = dimensionResource(R.dimen.login_card_title_padding_bottom)
                                )
                                .fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        bottom = dimensionResource(R.dimen.login_card_title_padding_bottom)
                                    ),
                                text = stringResource(R.string.findPeopleBtn),
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                            )
                            Text(
                                modifier = Modifier,
                                text = stringResource(R.string.employerIntro),
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            )
                        }
                        Button(
                            onClick = { Toast.makeText(context, toastPlaceholder, Toast.LENGTH_SHORT).show() },
                            colors = ButtonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.onTertiary,
                                disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                            ),
                            shape = RoundedCornerShape(
                                dimensionResource(R.dimen.login_card_employer_btn_corner)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()) {
                            Text(
                                modifier = Modifier,
                                text = stringResource(R.string.employerBtn),
                                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                                fontWeight = MaterialTheme.typography.displaySmall.fontWeight,
                                fontFamily = MaterialTheme.typography.displaySmall.fontFamily,
                            )
                        }
                    }
                }
            }
        }
    }
}
