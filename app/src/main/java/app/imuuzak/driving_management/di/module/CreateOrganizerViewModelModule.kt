package app.imuuzak.driving_management.di.module

import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.di.ViewModelKey
import app.imuuzak.driving_management.ui.organizer.viewmodel.CreateOrganizerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CreateOrganizerViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CreateOrganizerViewModel::class)
    abstract fun bindCreateTrackEventViewModel(vm: CreateOrganizerViewModel): ViewModel
}