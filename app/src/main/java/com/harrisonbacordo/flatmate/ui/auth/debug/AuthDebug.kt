package com.harrisonbacordo.flatmate.ui.auth.debug

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.AlphaTextField
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.EmailState
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.EmailTextField
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.NameState
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.NumericTextField
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.PasswordState
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.PasswordTextField
import com.harrisonbacordo.flatmate.ui.theme.typography

@Composable
fun AuthDebug() {
    AuthDebugScreen()
}

@Composable
fun AuthDebugScreen() {
    val alphaTextFieldState = remember { NameState() }
    val numericTextFieldState = remember { NameState() }
    val emailTextFieldState = remember { EmailState() }
    val passwordTextFieldState = remember { PasswordState() }
    LazyColumn(
        Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 32.dp),
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(text = "Typography", style = typography.h4, textAlign = TextAlign.Center)
            Text("h1", style = typography.h1)
            Text("h2", style = typography.h2)
            Text("h3", style = typography.h3)
            Text("h4", style = typography.h4)
            Text("h5", style = typography.h5)
            Text("h6", style = typography.h6)
            Text("body1", style = typography.body1)
            Text("body2", style = typography.body2)
            Text("button", style = typography.button)
            Text("caption", style = typography.caption)
            Text("overline", style = typography.overline)
            Text("subtitle1", style = typography.subtitle1)
            Text("subtitle2", style = typography.subtitle2)
            Spacer(modifier = Modifier.padding(top = 32.dp))
            Text(text = "TextFields", style = typography.h4, )
            AlphaTextField(value = alphaTextFieldState.text, hint = "Alpha Text Field", onValueChange = alphaTextFieldState::updateText)
            NumericTextField(value = numericTextFieldState.text, hint = "Numeric Text Field", onValueChange = numericTextFieldState::updateText)
            EmailTextField(value = emailTextFieldState.text, onValueChange = emailTextFieldState::updateText)
            PasswordTextField(value = numericTextFieldState.text, onValueChange = passwordTextFieldState::updateText)
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(name = "Auth Debug Screen Preview")
@Composable
private fun AuthDebugScreenPreview() {
    AuthDebugScreen()
}