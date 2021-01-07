package com.harrisonbacordo.flatmate.ui.onboarding.existingornewflat

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
fun OnboardingExistingOrNewFlat(
    onNewFlatClicked: () -> Unit,
    onExistingFlatClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    OnboardingExistingOrNewFlatScreen(onNewFlatClicked, onExistingFlatClicked, onBackClicked)
}

@Composable
private fun OnboardingExistingOrNewFlatScreen(
    onNewFlatClicked: () -> Unit,
    onExistingFlatClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    OnboardingHeaderText("Are you creating a new flat or joining an existing flat?")
    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthOnboardingScreenOption(
            title = "Create a new flat",
            leadingIcon = R.drawable.ic_create_new_flat_24dp,
            modifier = Modifier.clickable(onClick = onNewFlatClicked)
        )
        AuthOnboardingScreenOption(
            title = "Join an existing flat",
            leadingIcon = R.drawable.ic_join_existing_flat_24dp,
            modifier = Modifier.clickable(onClick = onExistingFlatClicked)
        )
        Button(onClick = onBackClicked) {
            Text(text = "Back")
        }
    }
}

@Preview(name = "Onboarding Existing or New Flat Screen")
@Composable
fun PreviewOnboardingExistingOrNewFlatScreen() {
    FlatmateOnboardingTheme {
        Scaffold {
            OnboardingExistingOrNewFlatScreen({}, {}, {})
        }
    }
}