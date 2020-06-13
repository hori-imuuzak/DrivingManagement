package app.imuuzak.driving_management.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.imuuzak.driving_management.domain.model.Car
import app.imuuzak.driving_management.domain.model.value.Maintenance
import app.imuuzak.driving_management.domain.model.value.MaintenanceStatusType
import java.util.*

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "engine_oil_status") val engineOilStatus: Int,
    @ColumnInfo(name = "engine_oil_odo") val engineOilOdoMeter: Int,
    @ColumnInfo(name = "engine_oil_date") val engineOilLastCheckDate: Date,

    @ColumnInfo(name = "transmission_oil_status") val transmissionOilStatus: Int,
    @ColumnInfo(name = "transmission_oil_odo") val transmissionOilOdoMeter: Int,
    @ColumnInfo(name = "transmission_oil_date") val transmissionOilLastCheckDate: Date,

    @ColumnInfo(name = "diff_oil_status") val diffOilStatus: Int,
    @ColumnInfo(name = "diff_oil_odo") val diffOilOdoMeter: Int,
    @ColumnInfo(name = "diff_oil_date") val diffOilLastCheckDate: Date,

    @ColumnInfo(name = "under_oil_status") val underOilStatus: Int,
    @ColumnInfo(name = "under_oil_odo") val underOilOdoMeter: Int,
    @ColumnInfo(name = "under_oil_date") val underOilLastCheckDate: Date,

    @ColumnInfo(name = "brake_pad_status") val brakePadStatus: Int,
    @ColumnInfo(name = "brake_pad_odo") val brakePadOdoMeter: Int,
    @ColumnInfo(name = "brake_pad_date") val brakePadLastCheckDate: Date,

    @ColumnInfo(name = "brake_rotor_status") val brakeRotorStatus: Int,
    @ColumnInfo(name = "brake_rotor_odo") val brakeRotorOdoMeter: Int,
    @ColumnInfo(name = "brake_rotor_date") val brakeRotorLastCheckDate: Date,

    @ColumnInfo(name = "brake_fluid_status") val brakeFluidStatus: Int,
    @ColumnInfo(name = "brake_fluid_odo") val brakeFluidOdoMeter: Int,
    @ColumnInfo(name = "brake_fluid_date") val brakeFluidLastCheckDate: Date,

    @ColumnInfo(name = "tire_status") val tireStatus: Int,
    @ColumnInfo(name = "tire_odo") val tireOdoMeter: Int,
    @ColumnInfo(name = "tire_date") val tireLastCheckDate: Date,

    @ColumnInfo(name = "tire_air_status") val tireAirStatus: Int,
    @ColumnInfo(name = "tire_air_odo") val tireAirOdoMeter: Int,
    @ColumnInfo(name = "tire_air_date") val tireAirLastCheckDate: Date,

    @ColumnInfo(name = "wheel_nut_status") val wheelNutStatus: Int,
    @ColumnInfo(name = "wheel_nut_odo") val wheelNutOdoMeter: Int,
    @ColumnInfo(name = "wheel_nut_date") val wheelNutLastCheckDate: Date,

    @ColumnInfo(name = "coolant_status") val coolantStatus: Int,
    @ColumnInfo(name = "coolant_odo") val coolantOdoMeter: Int,
    @ColumnInfo(name = "coolant_date") val coolantLastCheckDate: Date,

    @ColumnInfo(name = "some_anomaly_status") val someAnomalyStatus: Int,
    @ColumnInfo(name = "some_anomaly_odo") val someAnomalyOdoMeter: Int,
    @ColumnInfo(name = "some_anomaly_date") val someAnomalyLastCheckDate: Date
) {
    fun toCar(): Car {
        return Car(
            name = name,
            engineOil = Maintenance(
                MaintenanceStatusType.fromValue(engineOilStatus),
                engineOilOdoMeter,
                engineOilLastCheckDate
            ),
            transmissionOil = Maintenance(
                MaintenanceStatusType.fromValue(transmissionOilStatus),
                transmissionOilOdoMeter,
                transmissionOilLastCheckDate
            ),
            diffOil = Maintenance(
                MaintenanceStatusType.fromValue(diffOilStatus),
                diffOilOdoMeter,
                diffOilLastCheckDate
            ),
            underOilCheck = Maintenance(
                MaintenanceStatusType.fromValue(underOilStatus),
                underOilOdoMeter,
                underOilLastCheckDate
            ),
            brakePad = Maintenance(
                MaintenanceStatusType.fromValue(brakePadStatus),
                brakePadOdoMeter,
                brakePadLastCheckDate
            ),
            brakeRotor = Maintenance(
                MaintenanceStatusType.fromValue(brakeRotorStatus),
                brakeRotorOdoMeter,
                brakeRotorLastCheckDate
            ),
            brakeFluid = Maintenance(
                MaintenanceStatusType.fromValue(brakeFluidStatus),
                brakeFluidOdoMeter,
                brakeFluidLastCheckDate
            ),
            tire = Maintenance(
                MaintenanceStatusType.fromValue(tireStatus),
                tireOdoMeter,
                tireLastCheckDate
            ),
            tireAir = Maintenance(
                MaintenanceStatusType.fromValue(tireAirStatus),
                tireAirOdoMeter,
                tireAirLastCheckDate
            ),
            wheelNut = Maintenance(
                MaintenanceStatusType.fromValue(wheelNutStatus),
                wheelNutOdoMeter,
                wheelNutLastCheckDate
            ),
            coolant = Maintenance(
                MaintenanceStatusType.fromValue(coolantStatus),
                coolantOdoMeter,
                coolantLastCheckDate
            ),
            someAnomaly = Maintenance(
                MaintenanceStatusType.fromValue(someAnomalyStatus),
                someAnomalyOdoMeter,
                someAnomalyLastCheckDate
            )
        )
    }

    companion object {
        fun fromCar(car: Car): CarEntity {
            return CarEntity(
                0,
                name = car.name,

                engineOilStatus = car.engineOil.status.value(),
                engineOilOdoMeter = car.engineOil.odometer,
                engineOilLastCheckDate = car.engineOil.lastCheckDate,

                transmissionOilStatus = car.transmissionOil.status.value(),
                transmissionOilOdoMeter = car.transmissionOil.odometer,
                transmissionOilLastCheckDate = car.transmissionOil.lastCheckDate,

                diffOilStatus = car.diffOil.status.value(),
                diffOilOdoMeter = car.diffOil.odometer,
                diffOilLastCheckDate = car.diffOil.lastCheckDate,

                underOilStatus = car.underOilCheck.status.value(),
                underOilOdoMeter = car.underOilCheck.odometer,
                underOilLastCheckDate = car.underOilCheck.lastCheckDate,

                brakePadStatus = car.brakePad.status.value(),
                brakePadOdoMeter = car.brakePad.odometer,
                brakePadLastCheckDate = car.brakePad.lastCheckDate,

                brakeRotorStatus = car.brakeRotor.status.value(),
                brakeRotorOdoMeter = car.brakeRotor.odometer,
                brakeRotorLastCheckDate = car.brakeRotor.lastCheckDate,

                brakeFluidStatus = car.brakeFluid.status.value(),
                brakeFluidOdoMeter = car.brakeFluid.odometer,
                brakeFluidLastCheckDate = car.brakeFluid.lastCheckDate,

                tireStatus = car.tire.status.value(),
                tireOdoMeter = car.tire.odometer,
                tireLastCheckDate = car.tire.lastCheckDate,

                tireAirStatus = car.tireAir.status.value(),
                tireAirOdoMeter = car.tireAir.odometer,
                tireAirLastCheckDate = car.tireAir.lastCheckDate,

                wheelNutStatus = car.wheelNut.status.value(),
                wheelNutOdoMeter = car.wheelNut.odometer,
                wheelNutLastCheckDate = car.wheelNut.lastCheckDate,

                coolantStatus = car.coolant.status.value(),
                coolantOdoMeter = car.coolant.odometer,
                coolantLastCheckDate = car.coolant.lastCheckDate,

                someAnomalyStatus = car.someAnomaly.status.value(),
                someAnomalyOdoMeter = car.someAnomaly.odometer,
                someAnomalyLastCheckDate = car.someAnomaly.lastCheckDate
            )
        }
    }
}