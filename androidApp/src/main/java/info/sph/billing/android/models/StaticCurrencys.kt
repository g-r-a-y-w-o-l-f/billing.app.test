package info.sph.billing.android.models

import info.sph.billing.android.R

/**
 * Created by Serhii Polishchuk on 23.12.24
 */

enum class ECurrecy(val value: String, val imageIcon: Int, val symbol: String) {
    EUR("EUR", info.sph.billing.R.drawable.ic96_flag_europe, symbol = "\u20ac"),
    USD("USD", info.sph.billing.R.drawable.ic96_flag_usa, symbol = "\u0024"),
    ILS("ILS", info.sph.billing.R.drawable.ic96_flag_israel, symbol = "\u20AA"),
    GBP("GBP", info.sph.billing.R.drawable.ic96_flag_great_britain, symbol = "\u00A3")
}

object pairCurrencys{
    val withBaseUSD = listOf(ECurrecy.EUR, ECurrecy.ILS, ECurrecy.GBP)
    val withBaseEUR = listOf(ECurrecy.USD, ECurrecy.ILS, ECurrecy.GBP)
    val withBaseILS = listOf(ECurrecy.EUR, ECurrecy.USD, ECurrecy.GBP)
    val withBaseGBP = listOf(ECurrecy.EUR, ECurrecy.ILS, ECurrecy.USD)

    fun baseEurToString(): String {
        val text = StringBuilder()
        withBaseEUR.run {
            forEachIndexed { index, s ->
                text.append(s)
                if (index < this.size - 1) text.append(",")
            }
        }
        return text.toString()
    }

    fun baseUsdToString(): String {
        val text = StringBuilder()
        withBaseUSD.run {
            forEachIndexed { index, s ->
                text.append(s)
                if (index < this.size - 1) text.append(",")
            }
        }
        return text.toString()
    }

    fun baseILSToString(): String {
        val text = StringBuilder()
        withBaseILS.run {
            forEachIndexed { index, s ->
                text.append(s)
                if (index < this.size - 1) text.append(",")
            }
        }
        return text.toString()
    }

    fun baseGbpToString(): String {
        val text = StringBuilder()
        withBaseGBP.run {
            forEachIndexed { index, s ->
                text.append(s)
                if (index < this.size - 1) text.append(",")
            }
        }
        return text.toString()
    }

    fun baseEurToECurrency(): List<ECurrecy> {
        val eCurrency = mutableListOf<ECurrecy>()
        withBaseEUR.run {
            forEachIndexed { index, eCurrecy ->
                eCurrency.add(eCurrecy)
            }
        }
        return eCurrency
    }

    fun baseUsdToECurrency(): List<ECurrecy> {
        val eCurrency = mutableListOf<ECurrecy>()
        withBaseUSD.run {
            forEachIndexed { index, eCurrecy ->
                eCurrency.add(eCurrecy)
            }
        }
        return eCurrency
    }

    fun baseILSToECurrency(): List<ECurrecy> {
        val eCurrency = mutableListOf<ECurrecy>()
        withBaseILS.run {
            forEachIndexed { index, eCurrecy ->
                eCurrency.add(eCurrecy)
            }
        }
        return eCurrency
    }

    fun baseGbpToECurrency(): List<ECurrecy> {
        val eCurrency = mutableListOf<ECurrecy>()
        withBaseGBP.run {
            forEachIndexed { index, eCurrecy ->
                eCurrency.add(eCurrecy)
            }
        }
        return eCurrency
    }

    fun getPairByBase(baseCurrency: String) = when(baseCurrency){
        ECurrecy.EUR.value -> baseEurToString()
        ECurrecy.USD.value -> baseUsdToString()
        ECurrecy.ILS.value -> baseILSToString()
        ECurrecy.GBP.value -> baseGbpToString()
        else -> baseEurToString()
    }

    fun getPairECurrencyByBase(baseCurrency: String): List<ECurrecy> {
        return when(baseCurrency) {
            ECurrecy.EUR.value -> baseEurToECurrency()
            ECurrecy.USD.value -> baseUsdToECurrency()
            ECurrecy.ILS.value -> baseILSToECurrency()
            ECurrecy.GBP.value -> baseGbpToECurrency()
            else -> baseEurToECurrency()
        }
    }


    fun getBaseCurrencies() = listOf(
        ECurrecy.EUR,
        ECurrecy.USD,
        ECurrecy.ILS,
        ECurrecy.GBP
    )
}


