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
package com.harrisonbacordo.flatmate.ui.home.chores

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.data.models.Chore
import com.harrisonbacordo.flatmate.data.models.Interval

@Preview
@Composable
fun Chores() {
    ScrollableColumn(Modifier.fillMaxHeight()) {
        for (i in 0..10) {
            Chore(Chore("Clean kitchen", "Elon Musk", Interval.Fortnightly))
        }
    }
}

@Composable
private fun Chore(chore: Chore) {
    Card(Modifier.padding(bottom = 8.dp).fillMaxWidth().wrapContentHeight()) {
        Column(Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
            Row(Modifier.padding(bottom = 8.dp)) {
                Image(
                    imageResource(R.drawable.mock_profile_picture),
                    Modifier.preferredHeight(45.dp).wrapContentWidth()
                )
                Text(chore.title, Modifier.padding(start = 8.dp).align(Alignment.CenterVertically))
            }
            Text(chore.assignedFlatmateName)
            Text(chore.interval.name, Modifier.padding(bottom = 8.dp))
            Row(Modifier.fillMaxWidth()) {
                Button({}, Modifier.weight(1f).padding(end = 4.dp)) {
                    Text("Nudge")
                }
                Button({}, Modifier.weight(1f).padding(start = 4.dp)) {
                    Text("Complete")
                }
            }
        }
    }
}
