package app.imuuzak.driving_management.di.module

import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.di.ViewModelKey
import app.imuuzak.driving_management.ui.schedule.viewmodel.ScheduleDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ScheduleDetailViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ScheduleDetailViewModel::class)
    abstract fun bindScheduleDetailViewModel(vm: ScheduleDetailViewModel): ViewModel
}