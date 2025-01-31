package ru.amalkoott.database.mapper

import ru.amalkoott.database.entities.Vacancy as DbVacancy
import ru.amalkoott.model.Vacancy as DomainVacancy

object VacancyMapper {
    fun toDomain(dbVacancy: DbVacancy): DomainVacancy {
        return DomainVacancy(
            id = dbVacancy.id,
            lookingNumber = dbVacancy.lookingNumber,
            title = dbVacancy.title,
            town = dbVacancy.town,
            street = dbVacancy.street,
            house = dbVacancy.house,
            company = dbVacancy.company,
            experiencePreview = dbVacancy.experiencePreview,
            experienceText = dbVacancy.experienceText,
            publishedDate = dbVacancy.publishedDate,
            isFavorite = dbVacancy.isFavorite,
            salary = dbVacancy.salary,
            schedules = dbVacancy.schedules,
            appliedNumber = dbVacancy.appliedNumber,
            description = dbVacancy.description,
            responsibilities = dbVacancy.responsibilities,
            questions = dbVacancy.questions
        )
    }
    fun toDomainList(dbVacancies: List<DbVacancy>): List<DomainVacancy> {
        return dbVacancies.map { toDomain(it) }
    }
    fun toDatabase(domainVacancy: DomainVacancy): DbVacancy {
        return DbVacancy(
            id = domainVacancy.id,
            lookingNumber = domainVacancy.lookingNumber,
            title = domainVacancy.title,
            town = domainVacancy.town,
            street = domainVacancy.street,
            house = domainVacancy.house,
            company = domainVacancy.company,
            experiencePreview = domainVacancy.experiencePreview,
            experienceText = domainVacancy.experienceText,
            publishedDate = domainVacancy.publishedDate,
            isFavorite = domainVacancy.isFavorite,
            salary = domainVacancy.salary,
            schedules = domainVacancy.schedules,
            appliedNumber = domainVacancy.appliedNumber,
            description = domainVacancy.description,
            responsibilities = domainVacancy.responsibilities,
            questions = domainVacancy.questions
        )
    }
}
