package com.harrisonbacordo.flatmate.ui.onboarding.username

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
fun OnboardingUserName(onNextClicked: () -> Unit) {
    val viewModel: OnboardingUserNameViewModel = ViewModelProvider(ContextAmbient.current as ViewModelStoreOwner).get(OnboardingUserNameViewModel::class.java)
    val firstNameState = remember { NameState() }
    val lastNameState = remember { NameState() }
    OnboardingUserNameScreen(
        firstNameState = firstNameState,
        lastNameState = lastNameState,
        errorMessage = viewModel.errorMessage,
        onNextClicked = { viewModel.executeSaveUserNameFlow(firstNameState, lastNameState, onNextClicked) },
    )
}

@Composable
private fun OnboardingUserNameScreen(
    firstNameState: TextFieldState = remember { NameState() },
    lastNameState: TextFieldState = remember { NameState() },
    errorMessage: String,
    onNextClicked: () -> Unit,
) {
    Column(
        Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnboardingHeaderText(text = "Welcome! What's your name?")
        TextInput(
            value = firstNameState.text,
            hint = "First Name",
            onValueChange = firstNameState::updateText,
            Modifier.fillMaxWidth()
        )
        TextInput(
            value = lastNameState.text,
            hint = "Last Name",
            onValueChange = lastNameState::updateText,
            Modifier.fillMaxWidth()
        )
        if (errorMessage.isNotEmpty()) Text(errorMessage, color = MaterialTheme.colors.error)
        Spacer(Modifier.padding(top = 8.dp))
        Button(onClick = onNextClicked, Modifier.fillMaxWidth()) {
            Text(text = "Next")
        }
    }
}

@Preview(name = "Onboarding User Name Screen")
@Composable
private fun PreviewOnboardingUserNameScreen() {
    FlatmateOnboardingTheme {
        Scaffold {
            OnboardingUserNameScreen(errorMessage = "Error", onNextClicked = {})
        }
    }
}