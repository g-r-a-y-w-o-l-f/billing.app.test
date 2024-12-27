package info.sph.billing.android.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

/**
 * Created by Serhii Polishchuk on 20.12.24
 */

@Composable
fun ScreenStart(
    navHostController: NavHostController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {},
        bottomBar = {

        }
    ){  valuePadding ->
        Box (modifier = Modifier.padding(valuePadding)) {




        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ScreenStartPreview() {
    ScreenStart(NavHostController((LocalContext.current)))
}