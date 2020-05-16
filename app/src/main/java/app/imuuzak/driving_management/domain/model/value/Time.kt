package app.imuuzak.driving_management.domain.model.value

data class Time(
    // 時間
    var hour: Int = 0,
    // 分
    var minute: Int = 0
) {
    fun formatText() = "%02d:%02d".format(hour, minute)
}