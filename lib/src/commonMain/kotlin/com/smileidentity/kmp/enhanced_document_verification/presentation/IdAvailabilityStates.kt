package com.smileidentity.kmp.enhanced_document_verification.presentation

import com.smileidentity.kmp.enhanced_document_verification.models.Country

data class IDAvailabilityUiState(
    val selectedCountry: Country? = null,
    val selectedIdType: String? = null,
    val idTypeStatuses: Map<String, String> = emptyMap(),
    val isBottomSheetVisible: Boolean = false,
    val isLoading: Boolean = false
)


sealed class IDAvailabilityEvent {
    data class CountrySelected(val country: Country) : IDAvailabilityEvent()
    data class IdTypeSelected(val idType: String) : IDAvailabilityEvent()
    object ProceedClicked : IDAvailabilityEvent()
    object DismissBottomSheet : IDAvailabilityEvent()
    object ShowBottomSheet : IDAvailabilityEvent()
}