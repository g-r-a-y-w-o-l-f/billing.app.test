package info.sph.billing.android.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import info.karambol.play.ui.theme.BillingTheme

/**
 * Created by Serhii Polishchuk on 27.12.24
 */

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    var navNum by remember { mutableStateOf(0) }
    Row(
        modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
        .padding(vertical = 5.dp, horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (navNum == 0) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = info.sph.billing.R.drawable.ic100_settings),
                        contentDescription = "Settings",
                        tint = BillingTheme.colors.textField,
                        modifier = Modifier.size(25.dp)
                    )
                }
            } else {
                IconButton(onClick = { navNum = 0 }) {
                    Icon(
                        painter = painterResource(id = info.sph.billing.R.drawable.ic100_settings),
                        contentDescription = "Settings",
                        tint = BillingTheme.colors.textField,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            if (navNum == 1) {

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = info.sph.billing.R.drawable.ic100_bank_cards),
                        contentDescription = "Debit Cards",
                        tint = BillingTheme.colors.textField,
                        modifier = Modifier.size(25.dp)
                    )
                }
            } else {
                IconButton(onClick = { navNum = 1 }) {
                    Icon(
                        painter = painterResource(id = info.sph.billing.R.drawable.ic100_bank_cards),
                        contentDescription = "Debit Cards",
                        tint = BillingTheme.colors.textField,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (navNum == 2) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = info.sph.billing.R.drawable.ic100_invoice),
                        contentDescription = "Statement",
                        tint = BillingTheme.colors.textField,
                        modifier = Modifier.size(25.dp)
                    )
                }
            } else {
                IconButton(onClick = { navNum = 2 }) {
                    Icon(
                        painter = painterResource(id = info.sph.billing.R.drawable.ic100_invoice),
                        contentDescription = "Statement",
                        tint = BillingTheme.colors.textField,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            if (navNum == 3) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = info.sph.billing.R.drawable.ic100_home),
                        contentDescription = "Dashboard",
                        tint = BillingTheme.colors.textField,
                        modifier = Modifier.size(25.dp)
                    )
                }
            } else {
                IconButton(onClick = { navNum = 3 }) {
                    Icon(
                        painter = painterResource(id = info.sph.billing.R.drawable.ic100_home),
                        contentDescription = "Dashboard",
                        tint = BillingTheme.colors.textField,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    }
}