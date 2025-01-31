package ru.amalkoott.domain.usecase

import ru.amalkoott.domain.repository.AppRepository
import ru.amalkoott.model.Vacancy
import javax.inject.Inject

class FillDatabaseUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend fun expose(data: List<Vacancy>){
        appRepository.clearDatabase()
        appRepository.fillDatabase(data)
    }
}