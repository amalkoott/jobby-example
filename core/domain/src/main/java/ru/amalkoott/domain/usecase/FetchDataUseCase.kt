package ru.amalkoott.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.amalkoott.domain.repository.AppRemoteRepository
import ru.amalkoott.model.Offer
import ru.amalkoott.model.Vacancy
import javax.inject.Inject

class FetchDataUseCase @Inject constructor(
    private val appApi: AppRemoteRepository,
) {
    suspend fun expose(): Pair<Flow<List<Offer>>, Flow<List<Vacancy>>>? {
        return appApi.get()
    }
}