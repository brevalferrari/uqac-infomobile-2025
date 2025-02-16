package com.ferhatozcelik.jetpackcomposetemplate.data.model

import java.time.LocalDateTime
import java.time.Period
import java.util.UUID

data class Routine(
    var id: UUID,
    var name: String,
    var description: String? = null,
    var category: Category,
    var startTime: LocalDateTime? = null, // when null, start now
    var endTime: LocalDateTime? = null, // when null, stop never
    var period: Period? = null, // when null, do it once
    var priority: Priority
)
