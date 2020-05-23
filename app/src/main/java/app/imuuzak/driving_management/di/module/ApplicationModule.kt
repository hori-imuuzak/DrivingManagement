package app.imuuzak.driving_management.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }
}