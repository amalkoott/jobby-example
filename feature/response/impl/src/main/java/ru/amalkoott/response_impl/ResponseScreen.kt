package ru.amalkoott.response_impl

import androidx.compose.runtime.Composable
import ru.amalkoott.common.components.TempMessage

@Composable
fun ResponseScreen(name: String){
    TempMessage(name)
}