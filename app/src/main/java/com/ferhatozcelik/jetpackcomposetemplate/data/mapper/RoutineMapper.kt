// chemin : com.ferhatozcelik.jetpackcomposetemplate.data.mapper

package com.ferhatozcelik.jetpackcomposetemplate.data.mapper

import com.ferhatozcelik.jetpackcomposetemplate.data.entity.RoutineEntity
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Category
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Priority
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import java.time.LocalDateTime
import java.time.Period

fun Routine.toEntity() = RoutineEntity(
    id = id,
    name = name,
    description = description,
    category = category.name,
    priority = priority.name,
    periodDays = period?.days,
    startTime = startTime.toString(),
    endTime = endTime?.toString(),
    zone = zone
)

fun RoutineEntity.toModel() = Routine(
    id = id,
    name = name,
    description = description,
    category = Category.valueOf(category),
    priority = Priority.valueOf(priority),
    period = periodDays?.let { Period.ofDays(it) },
    startTime = LocalDateTime.parse(startTime),
    endTime = endTime?.let { LocalDateTime.parse(it) }
)
