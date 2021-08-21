package com.harrisonbacordo.flatmate.ui.composables.field.formModalPicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun FormModalPicker(
    iconResId: Int,
    label: String,
    isError: Boolean = false,
    onOptionClicked: () -> Unit,
) {
    Row(
        Modifier
            .clickable { onOptionClicked() }
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(painterResource(id = iconResId), contentDescription = null, tint = if (isError) MaterialTheme.colors.error else Color.Unspecified)
        Spacer(Modifier.padding(horizontal = 8.dp))
        Text(label, color = if (isError) MaterialTheme.colors.error else Color.Unspecified)
    }
}