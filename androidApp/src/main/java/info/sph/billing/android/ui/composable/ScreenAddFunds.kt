package info.sph.billing.android.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.rememberPermissionState
import info.karambol.play.ui.theme.BillingTheme
import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import info.sph.billing.android.models.Curensis
import info.sph.billing.android.models.ECurrecy
import info.sph.billing.android.models.pairCurrencys.getPairECurrencyByBase

/**
 * Created by Serhii Polishchuk on 20.12.24
 */

internal const val  READ_STORAGE_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ScreenAddFunds(
    navHostController: NavHostController,
    currentBaseCurrency: ECurrecy,
    listBaseCurrency: List<ECurrecy>,
    currentReceiveCurrency: ECurrecy,
    recipientCurrency: List<ECurrecy>,
    currenciesInfo: Curensis?,
    currentValueForSend: String,
    currentValueForReceive: String,
    listPurpose: List<String>,

    onReselectCurrencySend: (ECurrecy) -> Unit,
    onReselectCurrencyReceive: (ECurrecy) -> Unit,

    onReadCountForSend: (String) -> Unit,
    onReadCountForReceive: (String) -> Unit,
    onReadCountForScanDoc: () -> Unit,
    onTransfer: () -> Unit,
) {

    var infoCurrencies by remember { mutableStateOf(currenciesInfo) }
    var selectedSend by remember { mutableStateOf(currentBaseCurrency) }
    var expandedSend by remember { mutableStateOf(false) } // initial value
    var selectedRecipient by remember { mutableStateOf(currentReceiveCurrency) }
    var expandedRecipient by remember { mutableStateOf(false) } // initial value

    var expressPaySelected by remember { mutableStateOf(true) }

    var purposeName     by remember { mutableStateOf("Purpose") }
    var purposeValue     by remember { mutableStateOf("") }
    var purposeExpanded by remember { mutableStateOf(false) }
    var purposeCheck    by remember { mutableStateOf(false) }

    // checkbox control
    var beneficiaryCheck by remember { mutableStateOf(true) }
    var userTermsCheck  by remember { mutableStateOf(true) }
    var countValueForSend by remember { mutableStateOf(currentValueForSend) }
    var countValueForReceive by remember { mutableStateOf(currentValueForReceive) }

    val storagePermissionState = rememberPermissionState(READ_STORAGE_PERMISSION)
    val toastText = remember { mutableStateOf("") }

    val result = remember { mutableStateOf<Uri?>(null) }
    val resultUries = remember { mutableStateOf<MutableList<Uri>>(mutableListOf()) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
        it?.let { uri ->
            result.value = uri
            resultUries.value.add(uri)
        }
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box (
                modifier = Modifier
                    .background(BillingTheme.colors.primary)
                    .fillMaxWidth()
                    .height(BillingTheme.spacing.space38),
            ) {
                IconButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(BillingTheme.colors.primary)
                        .aspectRatio(1f),
                    onClick = {}) {
                    Icon(
                        modifier = Modifier
                            .height(BillingTheme.spacing.space12)
                            .aspectRatio(1f),
                        painter = painterResource(info.sph.billing.R.drawable.ic_arrow_back_24),
                        tint = BillingTheme.colors.staticBlack,
                        contentDescription = "arrow back button"
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(info.sph.billing.R.string.section_add_name),
                    style = BillingTheme.typography.titleXs,
                    color = BillingTheme.colors.textLabel,
                    textAlign = TextAlign.Center
                )
            }
        },
        bottomBar = {
            BottomAppBar{
                BottomBar()
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .size(88.dp)
                    .offset(y = 70.dp),
                shape = CircleShape,
                containerColor = Color.Blue,
                onClick = {

                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon", tint = Color.White)
            }
        }
    ){  contentPadding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .verticalScroll(rememberScrollState())
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = BillingTheme.spacing.space08),
                text = stringResource(info.sph.billing.R.string.section_screen_add_question),
                style = BillingTheme.typography.body,
                color = BillingTheme.colors.textLabel,
                textAlign = TextAlign.Center
            )

            Surface (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BillingTheme.spacing.space16),
                shape = BillingTheme.shapes.large,
                border = BorderStroke(width = 1.dp, BillingTheme.colors.tertiary)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = BillingTheme.spacing.space08,
                            horizontal = BillingTheme.spacing.space12
                        )
                ) {
                    // Send
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = BillingTheme.spacing.space08),
                        text = "You send",
                        style = BillingTheme.typography.body,
                        color = BillingTheme.colors.textField,
                        textAlign = TextAlign.Start
                    )

                    Spacer(Modifier.height(BillingTheme.spacing.space08))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = BillingTheme.shapes.medium,
                        border = BorderStroke(width = 1.dp, BillingTheme.colors.secondary)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = BillingTheme.spacing.space08)
                                .height(BillingTheme.spacing.space48),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            BasicTextField(
                                value = countValueForSend,
                                onValueChange = { countValueForSend = it },
                                modifier = Modifier
                                    .padding(start = BillingTheme.spacing.space08)
                                    .weight(1f),
                                textStyle = BillingTheme.typography.titleXxl,
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                            )

                            Box(
                                Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .padding(vertical = BillingTheme.spacing.space04)
                                    .background(BillingTheme.colors.tertiary)
                            )

                            Row(
                                modifier = Modifier
                                    .padding(BillingTheme.spacing.space04)
                                    .weight(.3f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(
                                    modifier = Modifier
                                        .height(BillingTheme.spacing.space24)
                                        .aspectRatio(1f),
                                    painter = painterResource(selectedSend.imageIcon),
                                    contentDescription = "arrow back button"
                                )

                                Spacer(Modifier.width(BillingTheme.spacing.space08))

                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = selectedSend.value,
                                    style = BillingTheme.typography.body,
                                    color = BillingTheme.colors.textLabel,
                                    textAlign = TextAlign.Start
                                )
                            }

                            Box(modifier = Modifier) {
                                DropdownMenu(
                                    expanded = expandedSend,
                                    onDismissRequest = { expandedSend = false }
                                ) {
                                    listBaseCurrency.forEach { option ->
                                        DropdownMenuItem(
                                            text = { Text(option.value) },
                                            onClick = {
                                                selectedSend = option
                                                expandedSend = !expandedSend
                                            }
                                        )
                                    }
                                }

                                IconButton(
                                    modifier = Modifier
                                        .width(BillingTheme.spacing.space32)
                                        .aspectRatio(1f)
                                        .padding(BillingTheme.spacing.space04),
                                    onClick = { expandedSend = !expandedSend }) {
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "More options"
                                    )
                                }
                            }
                        }

                    }
                    //Balances
                    Text(
                        modifier = Modifier
                            .padding(top = BillingTheme.spacing.space16)
                            .align(Alignment.CenterHorizontally),
                        text = "Balance: ${selectedSend.symbol}2,500.00",
                        style = BillingTheme.typography.body,
                        color = BillingTheme.colors.textLabel,
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = BillingTheme.spacing.space04)
                            .align(Alignment.CenterHorizontally),
                        text = "Available Balance: ${selectedSend.symbol}1,700.50",
                        style = BillingTheme.typography.body,
                        color = BillingTheme.colors.textLabel,
                    )

                    //============
                    // Recipient
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = BillingTheme.spacing.space08),
                        text = "Recipient gets"/*stringResource(info.sph.billing.R.string.section_screen_add_question)*/,
                        style = BillingTheme.typography.body,
                        color = BillingTheme.colors.textField,
                        textAlign = TextAlign.Start
                    )

                    Spacer(Modifier.height(BillingTheme.spacing.space08))
                    val interPair: List<ECurrecy> = getPairECurrencyByBase(currentBaseCurrency.value)
                    val exchange = infoCurrencies?.let { it.quotes["${selectedSend.value}${selectedRecipient.value}"] } ?: 1.0
                    countValueForReceive = if(countValueForSend.length>0) (countValueForSend.toDouble() * exchange).toString() else "0"
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = BillingTheme.shapes.medium,
                        border = BorderStroke(width = 1.dp, BillingTheme.colors.secondary)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = BillingTheme.spacing.space08)
                                .height(BillingTheme.spacing.space48),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // value

                            Text(
                                text = countValueForReceive,
                                modifier = Modifier
                                    .padding(start = BillingTheme.spacing.space08)
                                    .weight(1f),
                                style = BillingTheme.typography.titleXxl,
                            )

                            Box(
                                Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .padding(vertical = BillingTheme.spacing.space04)
                                    .background(BillingTheme.colors.tertiary)
                            )

                            Row(
                                modifier = Modifier
                                    .padding(BillingTheme.spacing.space04)
                                    .weight(.3f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(
                                    modifier = Modifier
                                        .height(BillingTheme.spacing.space24)
                                        .aspectRatio(1f),
                                    painter = painterResource(selectedRecipient.imageIcon),
                                    contentDescription = "arrow back button"
                                )

                                Spacer(Modifier.width(BillingTheme.spacing.space08))

                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = selectedRecipient.toString(),
                                    style = BillingTheme.typography.body,
                                    color = BillingTheme.colors.textLabel,
                                    textAlign = TextAlign.Start
                                )
                            }

                            Box(modifier = Modifier) {
                                DropdownMenu(
                                    expanded = expandedRecipient,
                                    onDismissRequest = { expandedRecipient = false }
                                ) {
                                    interPair.forEach { option ->
                                        DropdownMenuItem(
                                            trailingIcon = {
                                                Image(
                                                    modifier = Modifier
                                                        .height(18.dp)
                                                        .aspectRatio(1f),
                                                    painter = painterResource(option.imageIcon),
                                                    contentDescription = "Icon show"
                                                )
                                            },
                                            text = { Text(
                                                        text = option.value,
                                                        style = BillingTheme.typography.body,
                                                        color = BillingTheme.colors.textLabel,
                                                        textAlign = TextAlign.Start )},
                                            onClick = {
                                                selectedRecipient = option
                                                expandedRecipient = !expandedRecipient
                                            }
                                        )
                                    }
                                }

                                IconButton(
                                    modifier = Modifier
                                        .width(BillingTheme.spacing.space32)
                                        .aspectRatio(1f)
                                        .padding(BillingTheme.spacing.space04),
                                    onClick = { expandedRecipient = !expandedRecipient }) {
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "More options"
                                    )
                                }
                            }
                        }

                    }
                    // Currency Exchange Course
                    Text(
                        modifier = Modifier
                            .padding(top = BillingTheme.spacing.space16)
                            .align(Alignment.CenterHorizontally),
                        text = "Estimated exchange rate",
                        style = BillingTheme.typography.body,
                        color = BillingTheme.colors.textLabel,
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = BillingTheme.spacing.space04)
                            .align(Alignment.CenterHorizontally),
                        text = "1 ${selectedSend.symbol} = $exchange ${selectedRecipient.symbol} (1 ${selectedRecipient.symbol} = ${1/exchange} ${selectedSend.symbol})",
                        style = BillingTheme.typography.body,
                        color = BillingTheme.colors.textLabel,
                    )


                    // More text
                    Text(
                        modifier = Modifier
                            .padding(top = BillingTheme.spacing.space16)
                            .align(Alignment.CenterHorizontally),
                        text = "Exchange rates are real time and mid-market. The values provided are estimates and may change at the time of actual execution.",
                        style = BillingTheme.typography.small,
                        color = BillingTheme.colors.textLabel,
                        textAlign = TextAlign.Justify
                    )

                    // Transfer options.....
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = BillingTheme.spacing.space08),
                        text = "Transfer options",
                        style = BillingTheme.typography.body,
                        color = BillingTheme.colors.textLabel,
                        textAlign = TextAlign.Start
                    )

                    Spacer(Modifier.height(BillingTheme.spacing.space08))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(BillingTheme.spacing.space38)
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .clickable { expressPaySelected = false },
                            shape = BillingTheme.shapes.medium,
                            border = if (expressPaySelected) BorderStroke(
                                width = 1.dp,
                                BillingTheme.colors.tertiary
                            ) else BorderStroke(width = 1.dp, BillingTheme.colors.secondary),
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Regular",
                                    style = BillingTheme.typography.titleXs,
                                    color = BillingTheme.colors.textLabel,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                        Spacer(Modifier.width(BillingTheme.spacing.space08))
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                                .clickable { expressPaySelected = true },
                            shape = BillingTheme.shapes.medium,
                            border = if (expressPaySelected) BorderStroke(
                                width = 1.dp,
                                BillingTheme.colors.secondary
                            ) else BorderStroke(width = 1.dp, BillingTheme.colors.tertiary),
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Express (+€50)",
                                    style = BillingTheme.typography.titleXs,
                                    color = BillingTheme.colors.textLabel,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    // Purposes

                    Spacer(Modifier.height(20.dp))

                    Surface(
                        modifier = Modifier
                            .height(42.dp)
                            .fillMaxWidth()
                            .align(Alignment.Start),
                        shape = BillingTheme.shapes.medium,
                        border = BorderStroke(width = 1.dp, BillingTheme.colors.tertiary)
                    ) {

                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp),
                                text = purposeName,
                                style = BillingTheme.typography.titleXs,
                                color = BillingTheme.colors.textLabel,
                                textAlign = TextAlign.Start
                            )
                        }

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            DropdownMenu(
                                expanded = purposeExpanded,
                                onDismissRequest = { purposeExpanded = false }
                            ) {
                                listPurpose.forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text ( text = option,
                                            style = BillingTheme.typography.body,
                                            color = BillingTheme.colors.textLabel ) },
                                        onClick = {
                                            purposeName = option
                                            purposeExpanded = !purposeExpanded
                                        }
                                    )
                                }
                            }

                            IconButton(
                                modifier = Modifier
                                    .width(BillingTheme.spacing.space32)
                                    .aspectRatio(1f)
                                    .padding(BillingTheme.spacing.space04),
                                onClick = { purposeExpanded = !purposeExpanded }) {
                                Icon(
                                    Icons.Default.KeyboardArrowDown,
                                    tint = BillingTheme.colors.staticCyan,
                                    contentDescription = "More options"
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = purposeValue,
                        onValueChange = { purposeValue = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 42.dp),
                        enabled = true,
                        readOnly = false,
                        textStyle = BillingTheme.typography.body,
                        label = { Text("Payment Purpose",
                            style = BillingTheme.typography.body,
                            color = BillingTheme.colors.textLabel ) },
                        placeholder = null,
                        leadingIcon = null,
                        trailingIcon = null,
                        prefix = null,
                        suffix = null,
                        supportingText = null,
                        isError = false,
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions.Default,
                        keyboardActions = KeyboardActions.Default,
                        singleLine = false,
                        minLines = 1,
                        maxLines = 3,
                        shape = BillingTheme.shapes.medium,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = BillingTheme.colors.textLabel,
                            unfocusedTextColor = BillingTheme.colors.tertiary,
                            focusedBorderColor = BillingTheme.colors.tertiary,
                            unfocusedBorderColor = BillingTheme.colors.tertiary,
                            containerColor = Color.Transparent,
                            disabledBorderColor = BillingTheme.colors.tertiary,
                            errorBorderColor = BillingTheme.colors.tertiary
                        )
                    )
                    // Supported document

                    Spacer(Modifier.height(16.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = BillingTheme.spacing.space08),
                        text = "Supported document",
                        style = BillingTheme.typography.body,
                        color = BillingTheme.colors.textField,
                        textAlign = TextAlign.Start
                    )

                    Spacer(Modifier.height(8.dp))

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                            .clickable {
                                if (resultUries.value.size < 9) {
                                    launcher.launch(arrayOf("application/pdf"))
                                } else {
                                    toastText.value = "Limit upload files 10"
                                }
                            },
                        shape = BillingTheme.shapes.medium,
                        border = BorderStroke(width = 1.dp, BillingTheme.colors.staticGreen)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = BillingTheme.spacing.space08),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                modifier = Modifier
                                    .height(BillingTheme.spacing.space48)
                                    .aspectRatio(1f),
                                imageVector = Icons.Default.MailOutline,
                                tint = BillingTheme.colors.staticGreen,
                                contentDescription = "arrow back button"
                            )
                            Text(
                                modifier = Modifier
                                    .padding(top = BillingTheme.spacing.space02),
                                text = "Upload Document".uppercase(),
                                style = BillingTheme.typography.small,
                                color = BillingTheme.colors.textLabel,
                                textAlign = TextAlign.Start
                            )

                            result.value
                            resultUries.value.let { content ->
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    content.forEach {
                                        it.path?.run {
                                            Text(
                                                modifier = Modifier.padding(top = BillingTheme.spacing.space02),
                                                text = this,
                                                style = BillingTheme.typography.body,
                                                color = BillingTheme.colors.textField,
                                                textAlign = TextAlign.Start
                                            )
                                        }
                                    }
                                }
                            }

                            Text(
                                modifier = Modifier
                                    .padding(top = BillingTheme.spacing.space02),
                                text = "File types allowed: JPEG, JPG, PNG, PDF, DOC, DOCX.",
                                style = BillingTheme.typography.body,
                                color = BillingTheme.colors.textField,
                                textAlign = TextAlign.Start
                            )

                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = BillingTheme.spacing.space08, bottom = 16.dp),
                        text = "* Up to 10 files allowed.",
                        style = BillingTheme.typography.body,
                        color = BillingTheme.colors.textField,
                        textAlign = TextAlign.Start
                    )
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = BillingTheme.spacing.space08),
                    text = "Save Beneficiary",
                    style = BillingTheme.typography.body,
                    color = BillingTheme.colors.textField,
                    textAlign = TextAlign.Start
                )
                Checkbox(
                    modifier = Modifier
                        .height(24.dp)
                        .aspectRatio(1f),
                    checked = beneficiaryCheck,
                    onCheckedChange = { beneficiaryCheck = !beneficiaryCheck },
                    colors = CheckboxDefaults.colors(
                        checkedColor = BillingTheme.colors.staticGreen,
                        uncheckedColor = BillingTheme.colors.staticGreen,
                        checkmarkColor = BillingTheme.colors.primary,
                        disabledCheckedColor = BillingTheme.colors.textLabel,
                        disabledUncheckedColor = BillingTheme.colors.textLabel,
                        disabledIndeterminateColor = BillingTheme.colors.textLabel)
                )
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(BillingTheme.spacing.space12))
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = BillingTheme.spacing.space16)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(BillingTheme.colors.tertiary)
                )
            }
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(BillingTheme.spacing.space12))

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = BillingTheme.spacing.space08),
                    text = "By selecting the checkbox below, I acknowledge and agree that Wittix UAB and/or its subsidiaries (\"WTX\") operate sol... Read more",
                    style = BillingTheme.typography.body,
                    color = BillingTheme.colors.textField,
                    textAlign = TextAlign.Justify
                )
                Checkbox(
                    modifier = Modifier
                        .height(24.dp)
                        .aspectRatio(1f),
                    checked = userTermsCheck,
                    onCheckedChange = { userTermsCheck = !userTermsCheck },
                    colors = CheckboxDefaults.colors(
                        checkedColor = BillingTheme.colors.staticGreen,
                        uncheckedColor = BillingTheme.colors.staticGreen,
                        checkmarkColor = BillingTheme.colors.primary,
                        disabledCheckedColor = BillingTheme.colors.textLabel,
                        disabledUncheckedColor = BillingTheme.colors.textLabel,
                        disabledIndeterminateColor = BillingTheme.colors.textLabel)
                )
            }
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(BillingTheme.spacing.space18))

            Button ( onClick = { onTransfer },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BillingTheme.spacing.space48)
                    .padding(horizontal = 16.dp),
                shape = BillingTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor =  Color(0xff34AA54))
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = BillingTheme.spacing.space08),
                    text = "TRANSEED REVIEW",
                    style = BillingTheme.typography.body,
                    color = BillingTheme.colors.primary,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(BillingTheme.spacing.space48))

            if (toastText.value.length > 0){
                Toast.makeText(LocalContext.current.applicationContext, toastText.value, Toast.LENGTH_SHORT).show()
                toastText.value = ""
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScreenAddFundsPreview(){
//    ScreenAddFunds(
//        NavHostController(LocalContext.current),
//        ECurrecy.ILS,
//        listOf<ECurrecy>(ECurrecy.ILS, ECurrecy.EUR, ECurrecy.USD, ECurrecy.GBP),
//        listOf<ECurrecy>(ECurrecy.USD, ECurrecy.ILS, ECurrecy.GBP),
//        currenciesInfo = Curensis(privacy = "",
//            quotes = mapOf(Pair("EURUSD", 1.034584), Pair("EURILS", 10.034584), Pair("EURGBP", 1.534584)),
//            source = "EUR",
//            success = true,
//            terms = "More more text",
//            timestamp = 1000 * 60 * 24 * 365),
//        listOf<String>("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8"),
//    )
}