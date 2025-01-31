package ru.amalkoott.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.amalkoott.domain.repository.AppRemoteRepository
import ru.amalkoott.model.Offer
import javax.inject.Inject

class FetchOffersUseCase @Inject constructor(
    private val appApi: AppRemoteRepository,
    private val fillDatabaseUseCase: FillDatabaseUseCase
) {
    suspend fun expose(): Flow<List<Offer>> {
        val data = appApi.get()
        data?.let {
            /*
            data.second.collect{
                fillDatabaseUseCase.expose(it)
            }
            */
            return data.first
        } ?: return flow {  }
    }
}

