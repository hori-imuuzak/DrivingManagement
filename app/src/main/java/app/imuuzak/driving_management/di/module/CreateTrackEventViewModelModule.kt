package app.imuuzak.driving_management.di.module

import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.di.ViewModelKey
import app.imuuzak.driving_management.ui.schedule.viewmodel.CreateTrackEventViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CreateTrackEventViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CreateTrackEventViewModel::class)
    abstract fun bindCreateTrackEventViewModel(vm: CreateTrackEventViewModel): ViewModel
}