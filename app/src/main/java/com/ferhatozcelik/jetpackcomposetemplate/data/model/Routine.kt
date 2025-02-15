package com.ferhatozcelik.jetpackcomposetemplate.data.model

import java.time.Period
import java.util.Date

data class Routine (
    var name: String,
    var description: String?,
    var category: Category,
    var startTime: Date?, // when null, start now
    var endTime: Date?, // when null, stop never
    var period: Period?, // when null, do it once
    var priority: Priority
)
