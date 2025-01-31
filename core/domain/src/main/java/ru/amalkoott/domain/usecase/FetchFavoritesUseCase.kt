package ru.amalkoott.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.amalkoott.domain.repository.AppRepository
import ru.amalkoott.model.Vacancy
import javax.inject.Inject

class FetchFavoritesUseCase @Inject constructor(
    private val appRepo: AppRepository
) {
    suspend fun expose(): Flow<List<Vacancy>> {
        return appRepo.loadFavorites()
    }
}

