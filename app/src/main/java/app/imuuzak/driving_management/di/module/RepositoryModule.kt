package app.imuuzak.driving_management.di.module

import app.imuuzak.driving_management.domain.repository.*
import app.imuuzak.driving_management.infrastructure.database.AppDatabase
import app.imuuzak.driving_management.infrastructure.repository.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCircuitRepository(database: AppDatabase): CircuitRepository {
        return CircuitRepositoryImpl(database)
    }

    @Provides
    @Singleton
    fun provideOrganizerRepository(database: AppDatabase): OrganizerRepository {
        return OrganizerRepositoryImpl(database)
    }

    @Provides
    @Singleton
    fun provideTrackEventRepository(): TrackEventRepository {
        return TrackEventRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideRecordRepository(): RecordRepository {
        return RecordRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(database: AppDatabase): WeatherRepository {
        return WeatherRepositoryImpl(database)
    }
}