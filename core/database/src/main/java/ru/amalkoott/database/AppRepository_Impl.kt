package ru.amalkoott.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.amalkoott.database.mapper.VacancyMapper
import ru.amalkoott.model.Vacancy as DomainVacancy
import ru.amalkoott.domain.repository.AppRepository
import javax.inject.Inject

class AppRepository_Impl @Inject constructor(
    private val appDao: AppDao
): AppRepository {

    override suspend fun loadAllVacancy(): Flow<List<DomainVacancy>> = withContext(Dispatchers.IO){
        return@withContext appDao.allVacancy()
            .map { dbVacancies ->
                VacancyMapper.toDomainList(dbVacancies)
            }
    }
    override suspend fun loadFavorites(): Flow<List<DomainVacancy>> = withContext(Dispatchers.IO){
        return@withContext appDao.allFavorites()
            .map { dbVacancies ->
                VacancyMapper.toDomainList(dbVacancies)
            }
    }

    override suspend fun countFavorites(): Flow<Int> = withContext(Dispatchers.IO){
        return@withContext appDao.countFavorites()
    }

    override suspend fun addVacancy(vacancy: DomainVacancy): Long = withContext(Dispatchers.IO) {
        appDao.insertVacancy(VacancyMapper.toDatabase(vacancy))
    }

    override suspend fun updateVacancy(vacancy: DomainVacancy) = withContext(Dispatchers.IO) {
        appDao.updateVacancy(VacancyMapper.toDatabase(vacancy))
    }

    override suspend fun clearDatabase() = withContext(Dispatchers.IO){
        appDao.deleteVacancy()
    }

    override suspend fun fillDatabase(data : List<DomainVacancy>) {
        data.forEach{
            appDao.insertVacancy(VacancyMapper.toDatabase(it))
        }
    }

}