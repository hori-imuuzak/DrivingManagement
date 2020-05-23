package app.imuuzak.driving_management.domain.model.value

data class Pagination(
    val page: Int,
    val perPage: Int
) {
    init {
        if (page < 0) {
            throw IllegalArgumentException("page")
        }

        if (perPage < 0) {
            throw IllegalArgumentException("perPage")
        }
    }
}