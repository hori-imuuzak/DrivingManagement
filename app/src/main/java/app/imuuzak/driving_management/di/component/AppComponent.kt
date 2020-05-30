package app.imuuzak.driving_management.di.component

import app.imuuzak.driving_management.di.module.*
import app.imuuzak.driving_management.ui.circuit.activity.CreateCircuitActivity
import app.imuuzak.driving_management.ui.home.fragment.RecordFragment
import app.imuuzak.driving_management.ui.home.fragment.ScheduleFragment
import app.imuuzak.driving_management.ui.organizer.activity.CreateOrganizerActivity
import app.imuuzak.driving_management.ui.record.activity.CreateRecordActivity
import app.imuuzak.driving_management.ui.record.fragment.RecordListFragment
import app.imuuzak.driving_management.ui.schedule.activity.CreateTrackEventActivity
import app.imuuzak.driving_management.ui.schedule.fragment.ScheduleDetailFragment
import dagger.Component
import javax.inject.Singleton

// TODO Componentを分割する

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelFactoryModule::class,
        ScheduleViewModelModule::class,
        ScheduleDetailViewModelModule::class,
        RecordViewModelModule::class,
        RecordListViewModelModule::class,
        CreateTrackEventViewModelModule::class,
        CreateCircuitViewModelModule::class,
        CreateOrganizerViewModelModule::class,
        CreateRecordViewModelModule::class,
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
    fun inject(fragment: RecordFragment)
    fun inject(fragment: RecordListFragment)
    fun inject(activity: CreateTrackEventActivity)
    fun inject(activity: CreateCircuitActivity)
    fun inject(activity: CreateOrganizerActivity)
    fun inject(activity: CreateRecordActivity)
}