package com.harrisonbacordo.flatmate.ui.composables.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun CardTitle(title: String?) {
    title?.let {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textAlign = TextAlign.Center
        )
    } ?: run {
    // TODO text field here
    }

}

@Composable
internal fun CardLeadingIcon(drawableId: Int, degrees: Float = 0f) {
    Icon(painter = painterResource(id = drawableId), contentDescription = "", modifier = Modifier.rotate(degrees).padding(16.dp))
}