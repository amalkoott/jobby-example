package ru.amalkoott.network

import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import ru.amalkoott.domain.repository.AppRemoteRepository
import ru.amalkoott.model.Offer
import ru.amalkoott.model.Vacancy
import ru.amalkoott.network.mapper.DataMapper
import javax.inject.Inject

class ServerRequestRepository @Inject constructor(private val apiService: ApiService): AppRemoteRepository {
    override suspend fun get(): Pair<Flow<List<Offer>>, Flow<List<Vacancy>>> = withContext(Dispatchers.IO){
        val fetchedData: JsonObject
        try {
            val response = apiService.get()
            if(!response.isSuccessful){
                return@withContext Pair(MutableStateFlow(emptyList()), MutableStateFlow(emptyList()))
            }
            fetchedData = response.body()!!.asJsonObject

        }
        catch (e: Exception){
            return@withContext Pair(MutableStateFlow(emptyList()),MutableStateFlow(emptyList()))
        }
        val offers = fetchedData.asJsonObject.get("offers").asJsonArray
        val vacancies = fetchedData.asJsonObject.get("vacancies").asJsonArray

        return@withContext Pair(DataMapper.offerMap(offers),DataMapper.vacancyMap(vacancies))
    }
}
