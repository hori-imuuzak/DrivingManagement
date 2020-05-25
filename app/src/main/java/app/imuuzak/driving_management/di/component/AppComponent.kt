package app.imuuzak.driving_management.di.component

import app.imuuzak.driving_management.di.module.*
import app.imuuzak.driving_management.ui.circuit.activity.CreateCircuitActivity
import app.imuuzak.driving_management.ui.home.fragment.ScheduleFragment
import app.imuuzak.driving_management.ui.organizer.activity.CreateOrganizerActivity
import app.imuuzak.driving_management.ui.schedule.activity.CreateTrackEventActivity
import app.imuuzak.driving_management.ui.schedule.fragment.ScheduleDetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelFactoryModule::class,
        ScheduleViewModelModule::class,
        ScheduleDetailViewModelModule::class,
        CreateTrackEventViewModelModule::class,
        CreateCircuitViewModelModule::class,
        CreateOrganizerViewModelModule::class,
        AppDatabaseModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            appDatabaseModule: AppDatabaseModule,
            applicationModule: ApplicationModule
        ): AppComponent
    }

    fun inject(fragment: ScheduleFragment)
    fun inject(fragment: ScheduleDetailFragment)
    fun inject(activity: CreateTrackEventActivity)
    fun inject(activity: CreateCircuitActivity)
    fun inject(activity: CreateOrganizerActivity)
}