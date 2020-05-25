package app.imuuzak.driving_management.domain.model.value

import java.io.Serializable

data class Pagination(
    val page: Int,
    val perPage: Int
) : Serializable {
    init {
        if (page < 0) {
            throw IllegalArgumentException("page")
        }

        if (perPage < 0) {
            throw IllegalArgumentException("perPage")
        }
    }
}