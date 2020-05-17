package app.imuuzak.driving_management

import android.app.Application
import app.imuuzak.driving_management.di.component.AppComponent
import app.imuuzak.driving_management.di.component.DaggerAppComponent
import app.imuuzak.driving_management.di.module.AppDatabaseModule

class DrivingManagementApp: Application() {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        createComponent()
    }

    private fun createComponent() {
        component = DaggerAppComponent
            .factory()
            .create(AppDatabaseModule(this))
    }
    
    fun getComponent() = component
}