package ru.amalkoott.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.amalkoott.model.Vacancy
import javax.inject.Inject

class FetchVacancyUseCase @Inject constructor() {
    suspend fun expose(): Flow<List<Vacancy>> {
        return flow {}
    }
}