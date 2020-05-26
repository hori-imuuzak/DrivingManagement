package app.imuuzak.driving_management.domain.model.value

enum class WeatherType {
    NONE {
        override fun value() = -1
    },
    CLEAR {
        override fun value() = 10
    },
    SUNNY {
        override fun value() = 20
    },
    CLOUDY {
        override fun value() = 30
    },
    RAINY {
        override fun value() = 40
    },
    FOG {
        override fun value() = 50
    },
    STORM {
        override fun value() = 60
    },
    SNOW {
        override fun value() = 70
    };

    abstract fun value(): Int

    companion object {
        fun fromValue(value: Int): WeatherType {
            return when (value) {
                CLEAR.value() -> CLEAR
                SUNNY.value() -> SUNNY
                CLOUDY.value() -> CLOUDY
                RAINY.value() -> RAINY
                FOG.value() -> FOG
                STORM.value() -> STORM
                SNOW.value() -> SNOW
                else -> NONE
            }
        }
    }
}