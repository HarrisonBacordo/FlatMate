package com.harrisonbacordo.flatmate.ui.onboarding.newflatname

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
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
fun OnboardingNewFlatName(onFlatSuccessfullyCreated: () -> Unit, onBackClicked: () -> Unit) {
    val viewModel: OnboardingNewFlatNameViewModel = ViewModelProvider(ContextAmbient.current as ViewModelStoreOwner).get(OnboardingNewFlatNameViewModel::class.java)
    val nameState = remember { NameState() }
    OnboardingNewFlatNameScreen(
        nameState,
        { viewModel.executeCreateFlatFlow(nameState, onFlatSuccessfullyCreated) },
        onBackClicked,
    )
}

@Composable
private fun OnboardingNewFlatNameScreen(
    flatNameState: TextFieldState = remember { NameState() },
    onSubmitClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    Column(
        Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OnboardingHeaderText(text = "What is the name of your new flat?")
        TextInput(
            value = flatNameState.text,
            hint = "Flat name",
            onValueChange = flatNameState::updateText,
            Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Button(
            onClick = onSubmitClicked,
            Modifier.fillMaxWidth(),
        ) {
            Text(text = "Submit")
        }
        Button(onClick = onBackClicked) {
            Text(text = "Back")
        }
    }
}

@Preview(name = "Onboarding New Flat Name Screen")
@Composable
private fun PreviewOnboardingNewFlatScreen() {
    FlatmateOnboardingTheme {
        Scaffold {
            OnboardingNewFlatNameScreen(onSubmitClicked = {}, onBackClicked = {})
        }
    }
}