package app.imuuzak.driving_management.di.module

import androidx.lifecycle.ViewModelProvider
import app.imuuzak.driving_management.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}