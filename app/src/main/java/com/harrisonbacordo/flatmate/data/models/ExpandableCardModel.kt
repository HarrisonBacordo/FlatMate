package com.harrisonbacordo.flatmate.data.models

import java.util.*
import javax.annotation.concurrent.Immutable


@Immutable
interface ExpandableCardModel {
    var id: UUID
    val title: String
    val expanded: Boolean
}