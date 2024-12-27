package info.sph.billing.android.domain.repository

import info.sph.billing.android.data.model.ResponseHandler
import info.sph.billing.android.models.Curensis
import info.sph.billing.android.models.ParamsCurensis
import kotlinx.coroutines.flow.Flow

/**
 * Created by Serhii Polishchuk on 22.12.24
 */

interface IRepository {
    suspend fun getCurrency(data: ParamsCurensis): Flow<ResponseHandler<Curensis>>
}