package com.smileidentity.kmp.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.smileidentity.kmp.PlatformDependency
import com.smileidentity.kmp.SmileID
import com.smileidentity.kmp.models.KmpAuthenticationRequest
import com.smileidentity.kmp.models.KmpConfig
import com.smileidentity.kmp.models.KmpEnhancedKycRequest
import com.smileidentity.kmp.models.KmpJobType
import com.smileidentity.kmp.models.KmpPartnerParams
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // todo - will delete this
            LaunchedEffect(Unit) {
                val userId = UUID.randomUUID().toString()
                val jobId = UUID.randomUUID().toString()
                val authRequest = KmpAuthenticationRequest(
                    jobType = KmpJobType.EnhancedKyc,
                    userId = userId,
                    jobId = jobId,
                )

                val authResponse = SmileID.authenticate(authRequest)

                SmileID.doEnhancedKycAsync(
                    request = KmpEnhancedKycRequest(
                        country = "GH",
                        idType = "DRIVERS_LICENSE",
                        idNumber = "B0000000",
                        callbackUrl = "https://somedummyurl.com/demo",
                        partnerParams = KmpPartnerParams(
                            jobType = KmpJobType.EnhancedKyc,
                            jobId = jobId,
                            userId = userId,
                            extras = mapOf(
                                "name" to "Dummy Name",
                                "work" to "SmileID",
                            )
                        ),
                        timestamp = authResponse.timestamp,
                        signature = authResponse.signature
                    )
                )
            }

            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}