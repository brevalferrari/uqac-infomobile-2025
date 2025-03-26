package com.ferhatozcelik.jetpackcomposetemplate.data.model

import java.time.LocalDateTime
import java.time.Period
import java.util.UUID

data class Routine(
    var id: UUID,
    var name: String,
    var description: String = "",
    var category: Category,
    var startTime: LocalDateTime,
    var endTime: LocalDateTime? = null,
    var period: Period? = null, // when null, do it once
    var priority: Priority,
    var zone: Zone? = null,
)
