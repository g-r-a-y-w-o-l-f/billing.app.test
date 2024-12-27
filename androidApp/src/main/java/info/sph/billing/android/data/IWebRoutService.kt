package info.sph.billing.android.data
import info.sph.billing.android.models.Curensis
import info.sph.billing.android.utils.StaticData

import retrofit2.http.GET
import retrofit2.http.Query


interface IWebRoutService {
    @GET(StaticData.urlLive)
    suspend fun getCurrencyType(
        @Query("access_key") apiAccessKey: String,
        @Query("currencies") currencies: String,
        @Query("source") source: String,
        @Query("format") format: Int,
        ) : Curensis
}