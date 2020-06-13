package app.imuuzak.driving_management.domain.model.value

enum class MaintenanceStatusType {
    VERY_GOOD {
        override fun value() = 1
    },
    GOOD {
        override fun value() = 2
    },
    DANGER {
        override fun value() = 3
    },
    NG {
        override fun value() = 4
    },
    NO_CHECK {
        override fun value() = 99
    };

    abstract fun value(): Int
}