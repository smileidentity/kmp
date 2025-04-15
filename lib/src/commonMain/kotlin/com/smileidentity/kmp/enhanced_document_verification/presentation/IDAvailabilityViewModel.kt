package com.smileidentity.kmp.enhanced_document_verification.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.smileidentity.kmp.config.BuildKonfig
import com.smileidentity.kmp.enhanced_document_verification.domain.CheckIDAvailabilityUseCase
import com.smileidentity.kmp.enhanced_document_verification.models.CheckIDAPIStatusRequest
import com.smileidentity.kmp.enhanced_document_verification.models.Country
import com.smileidentity.kmp.enhanced_document_verification.models.supportedEnhancedDocumentVerificationDocumentTypes
import com.smileidentity.kmp.utils.generateSignature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


/**
 * ViewModel responsible for managing the state and logic related to ID availability
 * in the Enhanced Document Verification feature.
 *
 * It handles UI state, processes user events, fetches supported ID types per country,
 * and determines their availability status from the Smile Identity API.
 */
class IDAvailabilityViewModel() : ViewModel() {

    private val checkIDAvailabilityUseCase = CheckIDAvailabilityUseCase()

    private val _uiState = MutableStateFlow(IDAvailabilityUiState())
    val uiState: StateFlow<IDAvailabilityUiState> = _uiState.asStateFlow()

    fun onEvent(event: IDAvailabilityEvent) {

        when (event) {
            is IDAvailabilityEvent.CountrySelected -> {

                _uiState.value = _uiState.value.copy(
                    selectedCountry = event.country,
                    isLoading = true,
                    isBottomSheetVisible = false,
                    selectedIdType = null,
                    idTypeStatuses = emptyMap()
                )
                fetchIDStatuses(event.country)
            }

            is IDAvailabilityEvent.IdTypeSelected -> {
                _uiState.value = _uiState.value.copy(selectedIdType = event.idType)
            }

            IDAvailabilityEvent.ProceedClicked -> {
                // No-op here; use state outside
            }

            IDAvailabilityEvent.DismissBottomSheet -> {
                _uiState.value = _uiState.value.copy(isBottomSheetVisible = false)
            }

            IDAvailabilityEvent.ShowBottomSheet -> {
                _uiState.value = _uiState.value.copy(isBottomSheetVisible = true)
            }
        }
    }

    private fun fetchIDStatuses(country: Country) {
        viewModelScope.launch {
            val idTypes =
                supportedEnhancedDocumentVerificationDocumentTypes[country]?.map { it.type }
                    ?: emptyList()

            val statusMap = mutableMapOf<String, String>()

            for (idType in idTypes) {

                val (signature, timestamp) = generateSignature()
                val request = CheckIDAPIStatusRequest(
                    partner_id = BuildKonfig.SMILE_ID_PARTNER_ID,
                    signature = signature,
                    timestamp = timestamp,
                    country = country.code,
                    id_type = idType,
                    environment = BuildKonfig.SMILE_ID_ENVIRONMENT.lowercase()
                )

                when (val result = checkIDAvailabilityUseCase.invoke(request)) {
                    is Either.Left -> {
                        statusMap[idType] = "error"
                    }

                    is Either.Right -> {
                        val status = result.value.last_known_status
                        statusMap[idType] = status
                    }
                }
            }

            _uiState.value = _uiState.value.copy(
                idTypeStatuses = statusMap.filterValues { it == "online" }, isLoading = false
            )
        }
    }
}
