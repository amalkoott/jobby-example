package ru.amalkoott.domain.usecase

import ru.amalkoott.domain.repository.AppRepository
import ru.amalkoott.model.Vacancy
import javax.inject.Inject

class UpdateVacancyUseCase @Inject constructor(
    private val appRepo: AppRepository
) {
    suspend fun expose(vacancy: Vacancy){
        appRepo.updateVacancy(vacancy)
    }
}

