package info.sph.billing.android.models

import info.sph.billing.android.utils.StaticData

/**
 * Created by Serhii Polishchuk on 23.12.24
 */
data class ParamsCurensis(
    val apiAccessKey: String = StaticData.apiAccessKey,
    val currencies: String = pairCurrencys.baseEurToString(),
    val source: String = ECurrecy.EUR.value,
    val format: Int = 1 // this params - Format JSON
)
