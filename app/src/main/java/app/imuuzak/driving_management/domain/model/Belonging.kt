package app.imuuzak.driving_management.domain.model

import java.io.Serializable

/**
 * 持ち物
 */
data class Belonging(
    // 名前
    val name: String?,
    // 個数
    val count: Int
) : Serializable {
    init {
        if (name == null) {
            throw IllegalArgumentException("name")
        }
    }
}