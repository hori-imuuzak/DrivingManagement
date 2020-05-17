package app.imuuzak.driving_management.di.component

import android.content.Context
import app.imuuzak.driving_management.di.module.*
import app.imuuzak.driving_management.ui.circuit.activity.CreateCircuitActivity
import app.imuuzak.driving_management.ui.organizer.activity.CreateOrganizerActivity
import app.imuuzak.driving_management.ui.schedule.activity.CreateTrackEventActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ViewModelFactoryModule::class,
    CreateTrackEventViewModelModule::class,
    CreateCircuitViewModelModule::class,
    CreateOrganizerViewModelModule::class,
    AppDatabaseModule::class,
    RepositoryModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(appDatabaseModule: AppDatabaseModule): AppComponent
    }

    fun inject(activity: CreateTrackEventActivity)
    fun inject(activity: CreateCircuitActivity)
    fun inject(activity: CreateOrganizerActivity)
}