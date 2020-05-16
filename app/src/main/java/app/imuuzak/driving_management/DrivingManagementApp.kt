package app.imuuzak.driving_management

import android.app.Application
import app.imuuzak.driving_management.di.component.AppComponent
import app.imuuzak.driving_management.di.component.DaggerAppComponent

class DrivingManagementApp: Application() {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        createComponent()
    }

    private fun createComponent() {
        component = DaggerAppComponent.builder().build()
    }
    
    fun getComponent() = component
}