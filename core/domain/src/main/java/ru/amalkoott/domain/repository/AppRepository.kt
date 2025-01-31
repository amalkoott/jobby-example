package ru.amalkoott.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.amalkoott.model.Vacancy

interface AppRepository {
    suspend fun loadAllVacancy(): Flow<List<Vacancy>>
    suspend fun loadFavorites(): Flow<List<Vacancy>>

    suspend fun countFavorites(): Flow<Int>

    suspend fun addVacancy(vacancy: Vacancy) : Long?

    suspend fun updateVacancy(vacancy: Vacancy)

    suspend fun clearDatabase()
    suspend fun fillDatabase(data : List<Vacancy>)
}