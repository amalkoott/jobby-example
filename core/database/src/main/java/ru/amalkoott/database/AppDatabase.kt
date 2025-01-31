package ru.amalkoott.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.amalkoott.database.entities.Vacancy
import ru.amalkoott.database.typeconverter.ListConverter
import ru.amalkoott.database.typeconverter.MapConverter

@Database(
    entities = [Vacancy::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListConverter::class, MapConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun appDao(): AppDao
}