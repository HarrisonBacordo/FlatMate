/*
 * Designed and developed by 2020 FlatMate (Harrison Bacordo)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.harrisonbacordo.flatmate.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.harrisonbacordo.flatmate.R
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun AuthOnboardingScreenOption(title: String, leadingIcon: Int, modifier: Modifier = Modifier, contentColor: Color = Color.White) {
//    FIXME content color is screwy for facebookBlue and white, need to resolve this
    Surface(modifier.fillMaxWidth().padding(vertical = 24.dp, horizontal = 16.dp), contentColor = contentColor) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                CoilImage(data = leadingIcon, Modifier.width(24.dp).height(24.dp), colorFilter = ColorFilter.tint(contentColor))
                Text(title, modifier = Modifier.padding(start = 16.dp), color = contentColor)
            }
            CoilImage(data = R.drawable.ic_arrow_right_24dp, Modifier.width(24.dp).height(24.dp).padding(end = 8.dp), colorFilter = ColorFilter.tint(contentColor))
        }
    }
}
