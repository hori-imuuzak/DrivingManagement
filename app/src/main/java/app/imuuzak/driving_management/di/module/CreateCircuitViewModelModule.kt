package app.imuuzak.driving_management.di.module

import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.di.ViewModelKey
import app.imuuzak.driving_management.ui.circuit.viewmodel.CreateCircuitViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CreateCircuitViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CreateCircuitViewModel::class)
    abstract fun bindCreateTrackEventViewModel(vm: CreateCircuitViewModel): ViewModel
}