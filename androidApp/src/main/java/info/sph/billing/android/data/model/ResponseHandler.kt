package info.sph.billing.android.data.model

import com.android.post.domain.model.ApiError

/**
* Created by Serhii Polishchuk on 24.12.24
*/
sealed class ResponseHandler<out T> {
    data class Success<out T>(val data: T) : ResponseHandler<T>()
    data class Failure(val error: ApiError.ErrorStatus? = ApiError.ErrorStatus.UNKNOWN,
                       val extra: String? = "",
                       val errorResponse: ApiError.ErrorStatus? = null) : ResponseHandler<Nothing>()
    data class Loading(val state: Boolean) : ResponseHandler<Nothing>()
    object DoNothing : ResponseHandler<Nothing>()
}