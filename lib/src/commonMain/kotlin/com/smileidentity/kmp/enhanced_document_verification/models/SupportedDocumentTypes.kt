package com.smileidentity.kmp.enhanced_document_verification.models

import com.smileidentity.kmp.models.DocumentType

data class Country(val code: String, val name: String)

val supportedCountries =
  listOf(
    Country("KE", "Kenya"),
    Country("GH", "Ghana"),
    Country("NG", "Nigeria"),
    Country("ZA", "South Africa"),
  )

// temporary until i find a better way fo handling the document types

val supportedEnhancedDocumentVerificationDocumentTypes: Map<Country, List<DocumentType>> =
  mapOf(
    supportedCountries[0] to
      listOf(
        DocumentType("ALIEN_CARD", "Alien Card"),
        DocumentType("NATIONAL_ID", "National ID"),
        DocumentType("PASSPORT", "Passport"),
      ),
    supportedCountries[1] to
      listOf(
        DocumentType("PASSPORT", "Passport"),
        DocumentType("SOCIAL_ID", "SSNIT"),
        DocumentType("VOTER_ID", "New Voter's ID"),
      ),
    supportedCountries[2] to listOf(DocumentType("VOTER_ID", "Voter's ID")),
    supportedCountries[3] to listOf(DocumentType("IDENTITY_CARD", "National ID and Greenbook")),
  )
