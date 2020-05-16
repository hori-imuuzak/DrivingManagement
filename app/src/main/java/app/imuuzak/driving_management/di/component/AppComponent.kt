package app.imuuzak.driving_management.di.component

import app.imuuzak.driving_management.di.module.CreateCircuitViewModelModule
import app.imuuzak.driving_management.di.module.RepositoryModule
import app.imuuzak.driving_management.di.module.CreateTrackEventViewModelModule
import app.imuuzak.driving_management.di.module.ViewModelFactoryModule
import app.imuuzak.driving_management.ui.circuit.activity.CreateCircuitActivity
import app.imuuzak.driving_management.ui.schedule.activity.CreateTrackEventActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ViewModelFactoryModule::class,
    CreateTrackEventViewModelModule::class,
    CreateCircuitViewModelModule::class,
    RepositoryModule::class
])
interface AppComponent {
    fun inject(activity: CreateCircuitActivity)
    fun inject(activity: CreateTrackEventActivity)
}