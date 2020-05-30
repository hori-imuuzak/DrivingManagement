package app.imuuzak.driving_management.di.module

import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.di.ViewModelKey
import app.imuuzak.driving_management.ui.circuit.viewmodel.CreateCircuitViewModel
import app.imuuzak.driving_management.ui.home.viewmodel.RecordViewModel
import app.imuuzak.driving_management.ui.record.viewmodel.RecordListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RecordListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecordListViewModel::class)
    abstract fun bindRecordListViewModel(vm: RecordListViewModel): ViewModel
}