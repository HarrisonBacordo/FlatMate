package com.harrisonbacordo.flatmate.ui.home.chores

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.data.models.Interval
import com.harrisonbacordo.flatmate.data.models.User
import com.harrisonbacordo.flatmate.network.FlatmateNetworkResult
import com.harrisonbacordo.flatmate.ui.composables.field.formModalPicker.FormModalPicker
import com.harrisonbacordo.flatmate.ui.composables.field.textfield.AlphaTextField
import com.harrisonbacordo.flatmate.ui.theme.FlatMateHomeTheme

@Composable
fun HomeNewChore(initiateSave: Boolean, onSaveComplete: (FlatmateNetworkResult) -> Unit) {
    val viewModel: HomeNewChoreViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(HomeNewChoreViewModel::class.java)
    if (initiateSave) {
        viewModel.onChoreSaved(onSaveComplete)
    }
    val viewState by viewModel.state.collectAsState()
    NewChoreScreen(
        viewState,
        onIntervalClicked = viewModel::onIntervalClicked,
        onIntervalSelected = viewModel::onIntervalSelected,
        onIntervalDialogDismissRequest = viewModel::dismissDialog,
        onFlatmatesClicked = viewModel::onFlatmatesClicked,
        onFlatmateSelected = viewModel::onFlatmateSelected,
        onFlatmateDialogDismissRequest = viewModel::dismissDialog,
    )
}

@Composable
private fun NewChoreScreen(
    state: HomeNewChoreState,
    onIntervalClicked: () -> Unit,
    onIntervalSelected: (Interval) -> Unit,
    onIntervalDialogDismissRequest: () -> Unit,
    onFlatmatesClicked: () -> Unit,
    onFlatmateSelected: (User) -> Unit,
    onFlatmateDialogDismissRequest: () -> Unit,
) {
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        AlphaTextField(
            value = state.choreFieldsState.choreName.text,
            hint = "Chore Name",
            onValueChange = state.choreFieldsState.choreName::updateText,
            errorMessage = state.choreFieldsState.choreName.getError()
        )
        FormModalPicker(iconResId = R.drawable.ic_interval, label = state.choreFieldsState.choreInterval.name, isError = false) {
            onIntervalClicked()
        }
        FormModalPicker(iconResId = R.drawable.ic_person, label = state.choreFieldsState.assignedFlatmate?.fullName ?: "Assign flatmate") {
            onFlatmatesClicked()
        }
    }
    if (state.currentDialog == NewChoreDialog.Interval) {
        IntervalDialog(
            selected = state.choreFieldsState.choreInterval,
            onIntervalSelected = onIntervalSelected,
            onIntervalDialogDismissRequest = onIntervalDialogDismissRequest,
        )
    }
    if (state.currentDialog == NewChoreDialog.Flatmates && !state.flatmates.isNullOrEmpty()) {
        FlatmatesDialog(
            selectedFlatmate = state.choreFieldsState.assignedFlatmate,
            flatmates = state.flatmates,
            onFlatmateSelected = onFlatmateSelected,
            onFlatmatesDialogDismissRequest = onFlatmateDialogDismissRequest,
        )
    }
}

@Composable
private fun IntervalDialog(
    selected: Interval,
    onIntervalSelected: (Interval) -> Unit,
    onIntervalDialogDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onIntervalDialogDismissRequest) {
        Surface(Modifier.fillMaxWidth(.9f), shape = MaterialTheme.shapes.medium) {
            Column(Modifier.padding(vertical = 24.dp)) {
                Interval.values().forEach {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { onIntervalSelected(it) }
                            .padding(vertical = 12.dp, horizontal = 24.dp)
                    ) {
                        RadioButton(selected = it == selected, onClick = {})
                        Spacer(Modifier.padding(horizontal = 4.dp))
                        Text(it.name)
                    }
                }
            }
        }
    }
}

@Composable
private fun FlatmatesDialog(
    selectedFlatmate: User? = null,
    flatmates: List<User>,
    onFlatmateSelected: (User) -> Unit,
    onFlatmatesDialogDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onFlatmatesDialogDismissRequest) {
        Surface(Modifier.fillMaxWidth(.9f), shape = MaterialTheme.shapes.medium) {
            Column(Modifier.padding(vertical = 24.dp)) {
                flatmates.forEach {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { onFlatmateSelected(it) }
                            .padding(vertical = 12.dp, horizontal = 24.dp)
                    ) {
                        RadioButton(selected = it == selectedFlatmate, onClick = {})
                        Spacer(Modifier.padding(horizontal = 4.dp))
                        Text(it.fullName)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun NewChorePreview() {
    FlatMateHomeTheme {
        NewChoreScreen(state = HomeNewChoreState(), {}, {}, {}, {}, {}, {})
    }
}

@Preview
@Composable
private fun InterviewDialogPreview() {
    FlatMateHomeTheme {
        IntervalDialog(selected = Interval.Fortnightly, onIntervalSelected = {}, onIntervalDialogDismissRequest = {})
    }
}