package ru.amalkoott.model


data class Vacancy(
    val id: String,
    val lookingNumber: Int?,
    val title: String?,
    val town: String?,
    val street: String?,
    val house: String?,
    val company: String?,
    val experiencePreview: String?,
    val experienceText: String?,
    val publishedDate: String?,
    var isFavorite: Boolean,
    val salary: Map<String,String>?,
    val schedules: List<String>?,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String?,
    val questions: List<String>,
)