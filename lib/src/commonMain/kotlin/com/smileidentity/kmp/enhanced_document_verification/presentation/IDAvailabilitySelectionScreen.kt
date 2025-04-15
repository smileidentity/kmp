package com.smileidentity.kmp.enhanced_document_verification.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.smileidentity.kmp.enhanced_document_verification.models.Country
import com.smileidentity.kmp.enhanced_document_verification.models.supportedEnhancedDocumentVerificationDocumentTypes
import com.smileidentity.kmp.presentation.BottomPinnedColumn
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import smileid.lib.generated.resources.Res
import smileid.lib.generated.resources.enhanced_doc_v
import smileid.lib.generated.resources.si_country_sel_back_title
import smileid.lib.generated.resources.si_country_sel_title
import smileid.lib.generated.resources.si_no_documents_available
import smileid.lib.generated.resources.si_proceed
import smileid.lib.generated.resources.si_select_country
import smileid.lib.generated.resources.si_status_unknown


/**
 * Composable screen for selecting a country and an available ID type for enhanced document verification.
 *
 * This screen uses a ViewModel to manage state and handles:
 * - Displaying a list of supported countries
 * - Showing available ID types for the selected country
 * - Indicating loading states and availability of document types
 * - Allowing the user to proceed once both a country and a valid ID type are selected
 *
 * The screen includes a country selector (which opens a bottom sheet), ID type radio button options,
 * and a button to proceed with the selected options.
 *
 * @param viewModel The [IDAvailabilityViewModel] that holds the UI state and business logic.
 * @param onProceed Callback triggered when the user selects a valid combination and taps "Proceed".
 * Takes the selected [Country.code] and ID type as parameters.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IDAvailabilitySelectionScreen(
    viewModel: IDAvailabilityViewModel = viewModel { IDAvailabilityViewModel() },
    onProceed: (countryCode: String, idType: String) -> Unit
) {

    val state by viewModel.uiState.collectAsState()

    BottomPinnedColumn(scrollableContent = {
        Image(
            painter = painterResource(Res.drawable.enhanced_doc_v),
            contentDescription = null,
            modifier = Modifier.size(150.dp).padding(top = 8.dp),
        )
        Text(
            text = stringResource(Res.string.si_country_sel_title),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(Res.string.si_country_sel_back_title),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp),
        )

        OutlinedTextField(
            value = state.selectedCountry?.name ?: stringResource(Res.string.si_select_country),
            onValueChange = {},
            shape = RoundedCornerShape(16.dp),
            readOnly = true,
            enabled = false,
            label = { Text(stringResource(Res.string.si_select_country)) },
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
                .clickable { viewModel.onEvent(IDAvailabilityEvent.ShowBottomSheet) },
            colors = outlinedTextFieldColors(
                unfocusedBorderColor = Color.Black,
                disabledLeadingIconColor = Color.Black,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black,
                disabledBorderColor = Color.Black,
                cursorColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
            ),
        )

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 10.dp)
            )
        }

        state.selectedCountry?.let { country ->
            val availableTypes =
                supportedEnhancedDocumentVerificationDocumentTypes[country]?.filter { state.idTypeStatuses[it.type] == "online" }
                    ?: emptyList()

            if (availableTypes.isEmpty() && !state.isLoading) {
                Text(
                    text = stringResource(Res.string.si_no_documents_available),
                    modifier = Modifier.padding(top = 16.dp)
                )
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    availableTypes.forEach { idType ->
                        val isSelected = state.selectedIdType == idType.type
                        Surface(
                            color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            else MaterialTheme.colorScheme.primaryContainer,
                            tonalElevation = 2.dp,
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier.fillMaxWidth().toggleable(
                                value = isSelected, onValueChange = {
                                    viewModel.onEvent(
                                        IDAvailabilityEvent.IdTypeSelected(idType.type)
                                    )
                                })
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = null,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(
                                    text = idType.label
                                        ?: stringResource(Res.string.si_status_unknown),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }

        if (state.isBottomSheetVisible) {
            CountrySelectionBottomSheet(
                countries = supportedEnhancedDocumentVerificationDocumentTypes.keys.map {
                Country(code = it.code, name = it.name)
            },
                selectedCountry = state.selectedCountry,
                onCountrySelected = { viewModel.onEvent(IDAvailabilityEvent.CountrySelected(it)) },
                onDismiss = { viewModel.onEvent(IDAvailabilityEvent.DismissBottomSheet) })
        }
    }, pinnedContent = {
        Button(
            onClick = {
                state.selectedCountry?.let { country ->
                    state.selectedIdType?.let { idType ->
                        onProceed(country.code, idType)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            enabled = state.selectedIdType != null
        ) {
            Text(text = stringResource(Res.string.si_proceed))
        }
    })
}
