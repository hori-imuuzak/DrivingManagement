package app.imuuzak.driving_management.di.module

import app.imuuzak.driving_management.domain.repository.CircuitRepository
import app.imuuzak.driving_management.domain.repository.OrganizerRepository
import app.imuuzak.driving_management.domain.repository.TrackEventRepository
import app.imuuzak.driving_management.infrastructure.database.AppDatabase
import app.imuuzak.driving_management.infrastructure.repository.CircuitRepositoryImpl
import app.imuuzak.driving_management.infrastructure.repository.OrganizerRepositoryImpl
import app.imuuzak.driving_management.infrastructure.repository.TrackEventRepositoryImpl
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
}