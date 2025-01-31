package ru.amalkoott.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.amalkoott.domain.repository.AppRepository
import javax.inject.Inject

class FavoritesCountUseCase @Inject constructor(
    private val appRepository: AppRepository
){
    suspend fun expose() : Flow<Int> {
        return appRepository.countFavorites()
    }
}