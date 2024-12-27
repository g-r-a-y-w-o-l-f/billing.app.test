package com.android.post.domain.model


private const val BAD_REQUEST_ERROR_MESSAGE = "Bad Request!"
private const val FORBIDDEN_ERROR_MESSAGE = "Forbidden!"
const val UNKNOWN_ERROR_MESSAGE = "Unknown!"

data class ApiError(val message: String?, val code: Int?, var errorStatus: ErrorStatus) {

    constructor(message: String?, errorStatus: ErrorStatus) : this(message, null, errorStatus)

    fun getErrorMessage(): String {
        return when (errorStatus) {
            ErrorStatus.BAD_REQUEST -> BAD_REQUEST_ERROR_MESSAGE
            ErrorStatus.FORBIDDEN -> FORBIDDEN_ERROR_MESSAGE
            ErrorStatus.UNKNOWN -> UNKNOWN_ERROR_MESSAGE
        }
    }

    enum class ErrorStatus {
        BAD_REQUEST,
        FORBIDDEN,
        UNKNOWN
    }
}