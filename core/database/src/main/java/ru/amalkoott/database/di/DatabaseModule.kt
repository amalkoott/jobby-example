package ru.amalkoott.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.amalkoott.domain.repository.AppRepository
import ru.amalkoott.database.AppDao
import ru.amalkoott.database.AppDatabase
import ru.amalkoott.database.AppRepository_Impl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAppDao(db: AppDatabase): AppDao {
        return db.appDao()
    }

    @Singleton
    @Provides
    fun provideAppRepository(dao: AppDao): AppRepository {
        return AppRepository_Impl(dao)
    }
}