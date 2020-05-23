package app.imuuzak.driving_management.di.module

import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.di.ViewModelKey
import app.imuuzak.driving_management.ui.home.viewmodel.ScheduleViewModel
import app.imuuzak.driving_management.ui.schedule.viewmodel.CreateTrackEventViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ScheduleViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ScheduleViewModel::class)
    abstract fun bindScheduleViewModel(vm: ScheduleViewModel): ViewModel
}