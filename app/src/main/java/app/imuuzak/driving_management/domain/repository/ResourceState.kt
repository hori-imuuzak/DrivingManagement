package app.imuuzak.driving_management.domain.repository

data class ResourceState<out T>(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): ResourceState<T> {
            return ResourceState(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String?, data: T? = null): ResourceState<T> {
            return ResourceState(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): ResourceState<T> {
            return ResourceState(Status.LOADING, data, null)
        }
    }
}