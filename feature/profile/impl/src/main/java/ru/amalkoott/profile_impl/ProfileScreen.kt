package ru.amalkoott.profile_impl

import androidx.compose.runtime.Composable
import ru.amalkoott.common.components.TempMessage

@Composable
fun ProfileScreen(name: String){
    TempMessage(name)
}