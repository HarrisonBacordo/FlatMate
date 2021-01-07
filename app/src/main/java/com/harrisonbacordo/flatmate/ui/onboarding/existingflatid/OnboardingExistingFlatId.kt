package com.harrisonbacordo.flatmate.ui.onboarding.existingflatid

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.ui.onboarding.OnboardingHeaderText
import com.harrisonbacordo.flatmate.ui.theme.AuthOnboardingScreenOption
import com.harrisonbacordo.flatmate.ui.theme.FlatmateOnboardingTheme

@Composable
fun OnboardingExistingFlatId(onQrCodeClicked: () -> Unit, onManualEntryClicked: () -> Unit, onBackClicked: () -> Unit) {
    OnboardingExistingFlatIdScreen(onQrCodeClicked, onManualEntryClicked, onBackClicked)
}

@Composable
private fun OnboardingExistingFlatIdScreen(
    onQrCodeClicked: () -> Unit,
    onManualEntryClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    OnboardingHeaderText("How would you like to join your flat?")
    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthOnboardingScreenOption(
            title = "Scan flat QR code",
            leadingIcon = R.drawable.ic_qr_code,
            modifier = Modifier.clickable(onClick = onQrCodeClicked)
        )
        AuthOnboardingScreenOption(
            title = "Enter flat ID manually",
            leadingIcon = R.drawable.ic_text_input_24dp,
            modifier = Modifier.clickable(onClick = onManualEntryClicked)
        )
        Button(onClick = onBackClicked) {
            Text(text = "Back")
        }
    }
}

@Preview(name = "Onboarding Existing Flat Id Screen")
@Composable
private fun PreviewOnboardingExistingFlatIdScreen() {
    FlatmateOnboardingTheme {
        Scaffold {
            OnboardingExistingFlatIdScreen({}, {}, {})
        }
    }
}