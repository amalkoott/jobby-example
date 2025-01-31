package ru.amalkoott.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun getVacancyText(count: Int): String{
    val t = stringResource(R.string.noVacanciesText)
    return if(count < 1) t else "$count ${getVacancyWord(count)}"
}

private fun getVacancyWord(count: Int): String {
    return when {
        count % 10 == 1 && count % 100 != 11 -> "вакансия"
        count % 10 in 2..4 && count % 100 !in 12..14 -> "вакансии"
        else -> "вакансий"
    }
}

fun getPeopleText(count: Int): String{
    val count = count
    return "$count ${getPeopleWord(count)}"
}
private fun getPeopleWord(count: Int): String {
    return when {
        count % 10 == 1 && count % 100 != 11 -> "человек"
        count % 10 in 2..4 && count % 100 !in 12..14 -> "человека"
        else -> "человек"
    }
}

fun formatDate(dateString: String): String {
    val date = LocalDate.parse(dateString)
    return date.format(DateTimeFormatter.ofPattern("d MMMM", Locale.forLanguageTag("ru")))
}