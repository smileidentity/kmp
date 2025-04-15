package com.smileidentity.kmp.enhanced_document_verification.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.smileidentity.kmp.enhanced_document_verification.models.Country
import org.jetbrains.compose.resources.stringResource
import smileid.lib.generated.resources.Res
import smileid.lib.generated.resources.si_country_sel_bottom_sheet_title


/**
 * Displays a modal bottom sheet allowing the user to select a country from a list.
 *
 * This composable renders a bottom sheet with a scrollable list of countries. Each country is shown
 * as a selectable surface that highlights the selected country using a primary color tint.
 * The selected country is indicated visually, and tapping an item will invoke [onCountrySelected].
 *
 * @param countries The list of countries to display in the bottom sheet.
 * @param selectedCountry The currently selected country; used to highlight the selected item.
 * @param onCountrySelected Callback invoked when the user selects a country.
 * @param onDismiss Callback invoked when the bottom sheet is dismissed.
 *
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelectionBottomSheet(
    countries: List<Country>,
    selectedCountry: Country?,
    onCountrySelected: (Country) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp,
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {

        Text(
            text = stringResource(
                Res.string.si_country_sel_bottom_sheet_title
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium


        )
        LazyColumn(modifier = Modifier.padding(16.dp)) {

            items(countries) { country ->
                val isSelected = country.code == selectedCountry?.code
                Surface(
                    color = if (isSelected) MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.1f
                    )
                    else MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth().clickable { onCountrySelected(country) }) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = country.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
