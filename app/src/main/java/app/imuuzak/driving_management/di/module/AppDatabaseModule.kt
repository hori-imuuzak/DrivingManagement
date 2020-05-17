package app.imuuzak.driving_management.di.module

import android.content.Context
import androidx.room.Room
import app.imuuzak.driving_management.infrastructure.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "${context.applicationInfo.packageName}.db"
        ).build()
    }
}