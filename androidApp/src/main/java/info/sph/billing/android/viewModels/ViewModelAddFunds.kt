package info.sph.billing.android.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.sph.billing.android.data.model.ResponseHandler

import info.sph.billing.android.domain.repository.IRepository
import info.sph.billing.android.models.Curensis
import info.sph.billing.android.models.ECurrecy
import info.sph.billing.android.models.ParamsCurensis
import info.sph.billing.android.models.Quotes
import info.sph.billing.android.models.pairCurrencys.getBaseCurrencies
import info.sph.billing.android.models.pairCurrencys.getPairByBase
import info.sph.billing.android.models.pairCurrencys.getPairECurrencyByBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Serhii Polishchuk on 21.12.24
 */

class ViewModelAddFunds(private val repository: IRepository): ViewModel() {

    private val _baseCurrency = MutableStateFlow<ECurrecy>(ECurrecy.EUR)
    val baseCurrency = _baseCurrency

    private val _baseCollectCurrencies = MutableStateFlow<List<ECurrecy>>(getBaseCurrencies())
    val baseCollectCurrencies = _baseCollectCurrencies

    private val _currenciesInfo = MutableStateFlow<Curensis?>(null)
    val currenciesInfo = _currenciesInfo

    private val _pairCurrencies = MutableStateFlow(listOf<ECurrecy>())
    val pairCurrencies = _pairCurrencies

    private val _receiveCurrency = MutableStateFlow<ECurrecy>(ECurrecy.EUR)
    val receiveCurrency = _receiveCurrency

    private val _currentValueForSend = MutableStateFlow("0")
    val currentValueForSend = _currentValueForSend
    private val _currentValueForReceive = MutableStateFlow("0.0")
    val currentValueForReceive = _currentValueForReceive

    init {
        _pairCurrencies.value = getPairECurrencyByBase(baseCurrency.value.value)
        _receiveCurrency.value = pairCurrencies.value[0]
    }

    fun loadingCurrencyType(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCurrency(ParamsCurensis(source = baseCurrency.value.value,
                currencies = getPairByBase(baseCurrency.value.value))).collect { response ->
                withContext(Dispatchers.Main){
                    when(response){
                        is ResponseHandler.Success -> {
                            _currenciesInfo.value = response.data
                            _pairCurrencies.value = getPairECurrencyByBase(baseCurrency.value.value)
                        }
                        is ResponseHandler.Loading -> {}
                        is ResponseHandler.Failure -> {}
                        is ResponseHandler.DoNothing -> Unit
                    }
                }
            }
        }
    }

    fun changeBaseCurrency(newCurrency: ECurrecy){
        _baseCurrency.value = newCurrency
        loadingCurrencyType()
    }

    fun changeReceiveCurrency(newCurrency: ECurrecy){
        _receiveCurrency.value = newCurrency
    }
}