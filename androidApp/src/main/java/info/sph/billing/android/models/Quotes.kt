package info.sph.billing.android.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Quotes(
    val quotes: Map<String, Double>
)
//
//const val EUR = "EUR"
//const val USD = "EUR"
//const val ILS = "EUR"
//const val GBP = "EUR"
//
//val withBaseUSD = listOf(EUR, ILS, GBP)
//val withBaseEUR = listOf(USD, ILS, GBP)
//val withBaseILS = listOf(EUR, USD, GBP)
//val withBaseGBP = listOf(EUR, ILS, USD)