package info.sph.billing.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import info.karambol.play.ui.theme.BillingAppTheme
import info.sph.billing.android.ui.composable.ScreenAddFunds
import info.sph.billing.android.ui.utils.Routs
import info.sph.billing.android.viewModels.ViewModelAddFunds
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private lateinit var viewModel: ViewModelAddFunds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BillingAppTheme {




                viewModel = getViewModel()
                viewModel.loadingCurrencyType()

                val currentBaseCurrency =  remember { viewModel.baseCurrency.asStateFlow().value }
                val baseCollectCurrensies = remember { viewModel.baseCollectCurrencies.asStateFlow().value }
                val collectCurrencyByBase = remember { viewModel.pairCurrencies.asStateFlow().value }
                val currentReceiveCurrency = remember { viewModel.receiveCurrency.asStateFlow().value }
                val currenciesInfo = remember { viewModel.currenciesInfo.asStateFlow().value }

                val currentValueForSend =  remember { viewModel.currentValueForSend.asStateFlow().value }
                val currentValueForReceive =  remember { viewModel.currentValueForReceive.asStateFlow().value }

                navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routs.toStartScreen,
                ) {
                    composable(Routs.toStartScreen) {
                        ScreenAddFunds(
                            navController,
                            currentBaseCurrency,
                            baseCollectCurrensies,
                            currentReceiveCurrency,
                            collectCurrencyByBase,
                            currenciesInfo,
                            currentValueForSend = currentValueForSend,
                            currentValueForReceive = currentValueForReceive,
                            listPurpose = listOf<String>("Purpose A", "Purpose B", "Purpose C", "Purpose D"),

                            onReselectCurrencySend = {viewModel.changeBaseCurrency(it)},
                            onReselectCurrencyReceive = {viewModel.changeReceiveCurrency(it)},

                            onReadCountForReceive = {},
                            onReadCountForSend = {},
                            onReadCountForScanDoc = {},
                            onTransfer = {},

                        )
//                            GreetingView("Click Me")
//                            ScreenStart(navController)
                    }
                    composable(Routs.toAddFunds) {
//                        ScreenAddFunds(
//                            navController,
//                            listOf<Pair<String, String>>(Pair("A1", "ILS"), Pair("A3", "USD"), Pair("A5", "EUR"), Pair("A7", "UAH")),
////                            Pair("A1", "A2"),
//                            listOf<Pair<String, String>>(Pair("A1", "ILS"), Pair("A3", "USD"), Pair("A5", "EUR"), Pair("A7", "UAH")),
//                            listOf<String>("Purpose A", "Purpose B", "Purpose C", "Purpose D"),
////                            Pair("B1", "B2"),
//                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    var showContent by remember { mutableStateOf(false) }
        Column(Modifier
            .background(Color.Red)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }

            AnimatedVisibility(showContent) {
                val greeting = remember { text }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    Image(painterResource(R.mipmap.), null)
                    Text("Compose: $greeting")
                }
            }
        }
}

@Preview (showSystemUi = true)
@Composable
fun DefaultPreview() {
    BillingAppTheme {
        GreetingView("Hello, Android!")
    }
}
