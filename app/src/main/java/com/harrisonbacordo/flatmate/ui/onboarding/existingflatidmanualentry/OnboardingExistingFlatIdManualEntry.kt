package com.harrisonbacordo.flatmate.ui.onboarding.existingflatidmanualentry

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.ui.composables.textfield.NameState
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextFieldState
import com.harrisonbacordo.flatmate.ui.composables.textfield.TextInput
import com.harrisonbacordo.flatmate.ui.onboarding.OnboardingHeaderText
import com.harrisonbacordo.flatmate.ui.theme.FlatmateOnboardingTheme

@Composable
fun OnboardingExistingFlatIdManualEntry(onFlatSuccessfullyJoined: () -> Unit, onBackClicked: () -> Unit) {
    val viewModel: OnboardingExistingFlatIdManualEntryViewModel = ViewModelProvider(ContextAmbient.current as ViewModelStoreOwner).get(OnboardingExistingFlatIdManualEntryViewModel::class.java)
    val flatIdState = remember { NameState() }
    OnboardingExistingFlatIdManualEntryScreen(
        flatIdState = flatIdState,
        errorMessage = "",
        onFlatIdSubmitted = { viewModel.executeJoinFlatFlow(flatIdState = flatIdState, onFlatSuccessfullyJoined = onFlatSuccessfullyJoined) },
        onBackClicked = onBackClicked
    )
}

@Composable
private fun OnboardingExistingFlatIdManualEntryScreen(
    flatIdState: TextFieldState = remember { NameState() },
    errorMessage: String,
    onFlatIdSubmitted: () -> Unit,
    onBackClicked: () -> Unit,
) {
    Column(
        Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnboardingHeaderText(text = "What's your flat's ID?")
        TextInput(
            value = flatIdState.text,
            hint = "Flat ID",
            onValueChange = flatIdState::updateText,
            Modifier.fillMaxWidth()
        )
        if (errorMessage.isNotEmpty()) Text(errorMessage, color = MaterialTheme.colors.error)
        Spacer(Modifier.padding(top = 8.dp))
        Button(onClick = onFlatIdSubmitted, Modifier.fillMaxWidth()) {
            Text(text = "Submit")
        }
        Button(onClick = onBackClicked, Modifier.fillMaxWidth()) {
            Text(text = "Back")
        }
    }
}

@Preview(name = "Onboarding Existing Flat ID Manual Entry")
@Composable
private fun PreviewOnboardingExistingFlatIdManualEntryScreen() {
    FlatmateOnboardingTheme {
        Scaffold {
            OnboardingExistingFlatIdManualEntryScreen(errorMessage = "Error", onFlatIdSubmitted = {}, onBackClicked = {})
        }
    }
}