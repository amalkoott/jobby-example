package ru.amalkoott.search_impl.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.amalkoott.common.components.strategy.DefaultVacancyCard
import ru.amalkoott.common.components.strategy.NowLookingVacancyCard
import ru.amalkoott.common.components.strategy.VacancyCardDisplayStrategy
import ru.amalkoott.common.getVacancyText
import ru.amalkoott.domain.usecase.FetchDataUseCase
import ru.amalkoott.domain.usecase.FetchOffersUseCase
import ru.amalkoott.domain.usecase.LoadVacancyUseCase
import ru.amalkoott.domain.usecase.FillDatabaseUseCase
import ru.amalkoott.domain.usecase.UpdateVacancyUseCase
import ru.amalkoott.model.Offer
import ru.amalkoott.model.Vacancy
import ru.amalkoott.common.R
import ru.amalkoott.search_impl.presentation.components.offer.DefaultOffer
import ru.amalkoott.search_impl.presentation.components.offer.OfferStrategy
import ru.amalkoott.search_impl.presentation.components.offer.OfferWithButton
import ru.amalkoott.search_impl.presentation.components.searchbar.DefaultSearchBar
import ru.amalkoott.search_impl.presentation.components.searchbar.SearchBarStrategy
import ru.amalkoott.search_impl.presentation.components.searchbar.SearchBarWithExpanded
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val loadVacancyUseCase: LoadVacancyUseCase,
    private val updateVacancyUseCase: UpdateVacancyUseCase,
    private val fetchDataUseCase: FetchDataUseCase,
    private val fillDatabaseUseCase: FillDatabaseUseCase
): ViewModel() {
    private val _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    private var _isVacancyExpanded = mutableStateOf(false)
    private val _selectedVacancy = MutableStateFlow<Vacancy?>(null)
    private val _isOfferLoading = MutableStateFlow(true)
    private val _isVacanciesLoading = MutableStateFlow(true)

    val vacancies: Flow<List<Vacancy>> get() = _vacancies
    val offers: Flow<List<Offer>> get() = _offers
    val isVacancyExpanded: State<Boolean> get() = _isVacancyExpanded
    val isOfferLoading: StateFlow<Boolean> = _isOfferLoading
    val isVacanciesLoading: StateFlow<Boolean> = _isVacanciesLoading

    init {
        viewModelScope.launch {
            _isOfferLoading.value = true
            _isVacanciesLoading.value = true

            flow { emit(fetchDataUseCase.expose()) }.collect{
                it?.let {
                    val offersDeferred = async { fetchOffers(it.first) }
                    val vacancyDataDeferred = async { fetchVacancies(it.second) }

                    offersDeferred.await()
                    vacancyDataDeferred.await()
                }
            }
        }


    }
    private suspend fun fetchOffers(offers: Flow<List<Offer>>){
        offers.collect{
            _offers.value = it
            _isOfferLoading.value = false
        }
    }

    private suspend fun fetchVacancies(vacancies: Flow<List<Vacancy>>){
        viewModelScope.launch {
            vacancies.collect{
                val vacancyDataDeferred = async { saveVacancies(it) }
                vacancyDataDeferred.await()

                val vacanciesLoad = async {
                    loadVacancies()
                }
                vacanciesLoad.await()
            }
        }
    }
    private suspend fun saveVacancies(vacancies: List<Vacancy>){
        fillDatabaseUseCase.expose(vacancies)
    }
    private suspend fun loadVacancies(){
        loadVacancyUseCase.expose().collect{
            _vacancies.value = it
            _isVacanciesLoading.value = false
        }
    }
    fun getDisplayStrategy(offer : Offer): OfferStrategy {
        return when {
            offer.buttonText != "null" -> OfferWithButton()
            offer.title != null && offer.link != null -> DefaultOffer()
            else -> DefaultOffer()
        }
    }
    fun getDisplayStrategy(vacancy: Vacancy): VacancyCardDisplayStrategy {
        return if(vacancy.lookingNumber != null) NowLookingVacancyCard() else DefaultVacancyCard()
    }
    fun getDisplayStrategy(expanded: Boolean): SearchBarStrategy {
        return if (expanded) SearchBarWithExpanded() else DefaultSearchBar()
    }

    fun toggleExpand(){
        _isVacancyExpanded.value = !_isVacancyExpanded.value
    }
    fun toggleFavorite(vacancy: Vacancy){
        viewModelScope.launch {
            updateVacancyUseCase.expose(vacancy.copy(isFavorite = !vacancy.isFavorite))
        }
    }
    fun openVacancy(vacancy: Vacancy){
        _selectedVacancy.value = vacancy
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun getShowMoreText(): String{
        val count = _vacancies.value.size
        return if (count < 1) stringResource(R.string.noVacanciesText) else "${stringResource(R.string.showMoreBtnText)} ${getVacancyText(count - 3)}"
    }
}