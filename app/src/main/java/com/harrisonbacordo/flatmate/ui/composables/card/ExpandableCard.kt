package com.harrisonbacordo.flatmate.ui.composables.card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harrisonbacordo.flatmate.R
import com.harrisonbacordo.flatmate.data.mocks.Mocks
import com.harrisonbacordo.flatmate.data.models.ExpandableCardModel
import com.harrisonbacordo.flatmate.ui.theme.FlatMateHomeTheme
import java.util.*

const val EXPAND_ANIMATION_DURATION = 300
const val COLLAPSE_ANIMATION_DURATION = 300
const val FADE_IN_ANIMATION_DURATION = 350
const val FADE_OUT_ANIMATION_DURATION = 300

@ExperimentalAnimationApi
@Composable
fun ExpandableCard(
    expandableCardModel: ExpandableCardModel,
    onExpandClicked: (id: UUID) -> Unit,
    expandedContent: @Composable (() -> Unit)
) {
    val transitionState = remember {
        MutableTransitionState(expandableCardModel.expanded).apply {
            targetState = !expandableCardModel.expanded
        }
    }
    val transition = updateTransition(targetState = transitionState)
    val cardBackgroundColor by transition.animateColor({ tween(durationMillis = EXPAND_ANIMATION_DURATION) }) {
        if (expandableCardModel.expanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    }
    val cardContentColor by transition.animateColor({ tween(durationMillis = EXPAND_ANIMATION_DURATION) }) {
        if (expandableCardModel.expanded) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface
    }
    val cardPaddingHorizontal by transition.animateDp({ tween(durationMillis = EXPAND_ANIMATION_DURATION) }) {
        if (expandableCardModel.expanded) 48.dp else 24.dp
    }
    val cardElevation by transition.animateDp({ tween(durationMillis = EXPAND_ANIMATION_DURATION) }) {
        if (expandableCardModel.expanded) 24.dp else 4.dp
    }
    val cardRoundedCorners by transition.animateDp({ tween(durationMillis = EXPAND_ANIMATION_DURATION) }) {
        if (expandableCardModel.expanded) 0.dp else 16.dp
    }
    val arrowRotationDegree by transition.animateFloat({ tween(durationMillis = EXPAND_ANIMATION_DURATION) }) {
        if (expandableCardModel.expanded) 180f else 0f
    }

    Card(
        backgroundColor = cardBackgroundColor,
        contentColor = cardContentColor,
        elevation = cardElevation,
        shape = RoundedCornerShape(cardRoundedCorners),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = cardPaddingHorizontal, vertical = 8.dp)
            .clickable { onExpandClicked(expandableCardModel.id) }
    ) {
        Column {
            Box {
                CardLeadingIcon(drawableId = R.drawable.ic_arrow_down_24dp, degrees = arrowRotationDegree)
                CardTitle(expandableCardModel.title)
            }
            ExpandableContent(visible = expandableCardModel.expanded, initialVisibility = expandableCardModel.expanded, expandedContent = expandedContent)
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun ExpandableContent(
    visible: Boolean = true,
    initialVisibility: Boolean = true,
    expandedContent: @Composable (() -> Unit)
) {
    val enterFadeIn = remember {
        fadeIn(
            animationSpec = tween(durationMillis = FADE_IN_ANIMATION_DURATION)
        )
    }
    val enterExpand = remember {
        expandVertically(animationSpec = tween(EXPAND_ANIMATION_DURATION))
    }
    val exitFadeOut = remember {
        fadeOut(
            animationSpec = tween(durationMillis = FADE_OUT_ANIMATION_DURATION)
        )
    }
    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(durationMillis = COLLAPSE_ANIMATION_DURATION))
    }
    AnimatedVisibility(
        visible = visible,
        initiallyVisible = initialVisibility,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        expandedContent()
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
private fun PreviewExpandableCard() {
    FlatMateHomeTheme {
        ExpandableCard(Mocks.GroceryListCardMock, {}) {
            Text("Expanded content")
        }
    }

}