package ru.amalkoott.message_impl

import androidx.compose.runtime.Composable
import ru.amalkoott.common.components.TempMessage

@Composable
fun MessageScreen(name: String){
    TempMessage(name)
}