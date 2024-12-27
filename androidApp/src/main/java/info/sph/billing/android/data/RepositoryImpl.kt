package info.sph.billing.android.data

import com.android.post.domain.model.ApiError
import info.sph.billing.android.data.model.ResponseHandler
import info.sph.billing.android.domain.repository.IRepository
import info.sph.billing.android.models.Curensis
import info.sph.billing.android.models.ParamsCurensis
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.ConnectException

/**
 * Created by Serhii Polishchuk on 22.12.24
 */

class RepositoryImpl(val apiService: IWebRoutService) : IRepository{
    override suspend fun getCurrency(params: ParamsCurensis): Flow<ResponseHandler<Curensis>> {
        return flow {
            emit(ResponseHandler.Loading(true))
            try {
                val requestResult = apiService.getCurrencyType(
                    params.apiAccessKey,
                    params.currencies,
                    params.source,
                    params.format
                )
                emit(ResponseHandler.Loading(false))
                emit(ResponseHandler.Success(requestResult))
            } catch (ex: HttpException) {
                emit(ResponseHandler.Loading(false))
                emit(ResponseHandler.Failure(
                    error = ApiError.ErrorStatus.UNKNOWN,
                    extra = ex.message,
                    errorResponse = ApiError.ErrorStatus.UNKNOWN))
            } catch (ex: ConnectException){
                emit(ResponseHandler.Loading(false))
                emit(ResponseHandler.Failure(error = ApiError.ErrorStatus.UNKNOWN,
                    extra = ex.message,
                    errorResponse = ApiError.ErrorStatus.UNKNOWN))
            }
        }
    }
}