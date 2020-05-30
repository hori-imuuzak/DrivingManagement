package app.imuuzak.driving_management.di.module

import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.di.ViewModelKey
import app.imuuzak.driving_management.ui.circuit.viewmodel.CreateCircuitViewModel
import app.imuuzak.driving_management.ui.home.viewmodel.RecordViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RecordViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecordViewModel::class)
    abstract fun bindRecordViewModelModule(vm: RecordViewModel): ViewModel
}