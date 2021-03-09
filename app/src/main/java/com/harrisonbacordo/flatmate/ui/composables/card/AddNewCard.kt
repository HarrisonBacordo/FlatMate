package com.harrisonbacordo.flatmate.ui.composables.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.ui.theme.grey400

@Composable
fun AddNewGroceryCard(onClicked: () -> Unit) {
    Card(
        backgroundColor = grey400,
        elevation = 4.dp,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clickable { onClicked() }
    ) {
        Box {
            CardLeadingIcon(drawableId = R.drawable.ic_add_24dp)
            CardTitle("Add New...")
        }
    }
}

@Composable
fun AddNewChoreCard(onClicked: () -> Unit) {
    Card(
        backgroundColor = grey400,
        elevation = 4.dp,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClicked() }
    ) {
        Box {
            CardLeadingIcon(drawableId = R.drawable.ic_add_24dp)
            CardTitle("Add New...")
        }
    }
}