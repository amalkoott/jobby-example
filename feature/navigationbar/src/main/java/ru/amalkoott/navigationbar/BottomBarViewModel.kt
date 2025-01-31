package ru.amalkoott.navigationbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.amalkoott.domain.usecase.FavoritesCountUseCase
import javax.inject.Inject


@HiltViewModel
class BottomBarViewModel @Inject constructor(
    private val getFavoritesCount : FavoritesCountUseCase
): ViewModel() {
    private val _favoritesCounter = MutableStateFlow<Int>(0)

    init {
        viewModelScope.launch {
            favoritesCount()
        }
    }
    private suspend fun favoritesCount(){
        getFavoritesCount.expose().collect { value ->
            _favoritesCounter.value = value
        }
    }
    fun getCounter(item: BottomTabs): StateFlow<Int>?{
        return when(item){
            BottomTabs.FAVORITES -> _favoritesCounter
            BottomTabs.SEARCH -> null
            else -> null
        }
    }
}