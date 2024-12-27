package info.sph.billing.android.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Curensis(
    @Json(name = "privacy")
    val privacy: String,
    @Json(name = "quotes")
    val quotes: Map<String, Double>,
    @Json(name = "source")
    val source: String,
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "terms")
    val terms: String,
    @Json(name = "timestamp")
    val timestamp: Int
)