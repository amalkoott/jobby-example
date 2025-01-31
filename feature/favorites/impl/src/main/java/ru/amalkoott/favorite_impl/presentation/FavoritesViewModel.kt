package ru.amalkoott.favorite_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.amalkoott.common.components.strategy.DefaultVacancyCard
import ru.amalkoott.common.components.strategy.NowLookingVacancyCard
import ru.amalkoott.common.components.strategy.VacancyCardDisplayStrategy
import ru.amalkoott.domain.usecase.FetchFavoritesUseCase
import ru.amalkoott.domain.usecase.UpdateVacancyUseCase
import ru.amalkoott.model.Vacancy
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor (
    private val updateVacancyUseCase: UpdateVacancyUseCase,
    private val fetchFavoritesUseCase: FetchFavoritesUseCase
): ViewModel() {
    private val _favorites = MutableStateFlow<List<Vacancy>>(emptyList())

    private val _favoritesCounter = MutableStateFlow<Int>(0)
    private val _selectedVacancy = MutableStateFlow<Vacancy?>(null)
    private val _isOfferLoading = MutableStateFlow(true)

    val favorites: Flow<List<Vacancy>> get() = _favorites

    init {
        viewModelScope.launch {
            _isOfferLoading.value = true
            val otherDataDeferred = async { loadOtherData() }

            otherDataDeferred.await()
        }
    }

    private suspend fun loadOtherData() = withContext(Dispatchers.IO) {
        val favoritesDeferred = async { loadFavorites() }
        favoritesDeferred.await()
    }


    private suspend fun loadFavorites() {
        fetchFavoritesUseCase.expose().collect { favoritesList ->
            withContext(Dispatchers.Main) {
                _favorites.value = favoritesList
                _favoritesCounter.value = favoritesList.size
            }
        }
    }
    fun getDisplayStrategy(vacancy: Vacancy): VacancyCardDisplayStrategy {
        return if(vacancy.lookingNumber != null) NowLookingVacancyCard() else DefaultVacancyCard()
    }

    fun onRemoveFavorite(vacancy: Vacancy){
        viewModelScope.launch {
            updateVacancyUseCase.expose(vacancy.copy(isFavorite = !vacancy.isFavorite))
        }
    }

    fun openVacancy(vacancy: Vacancy){
        _selectedVacancy.value = vacancy
    }
}