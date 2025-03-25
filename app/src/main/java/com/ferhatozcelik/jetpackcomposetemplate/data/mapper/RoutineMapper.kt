// chemin : com.ferhatozcelik.jetpackcomposetemplate.data.mapper

package com.ferhatozcelik.jetpackcomposetemplate.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.ferhatozcelik.jetpackcomposetemplate.data.entity.RoutineEntity
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Category
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Priority
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import java.time.LocalDateTime
import java.time.Period
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
fun Routine.toEntity() = RoutineEntity(
    id = id.toString(),
    name = name,
    description = description,
    category = category.name,
    priority = priority.name,
    periodDays = period?.days,
    startTime = startTime.toString(),
    endTime = endTime?.toString()
)

@RequiresApi(Build.VERSION_CODES.O)
fun RoutineEntity.toModel() = Routine(
    id = UUID.fromString(id),
    name = name,
    description = description,
    category = Category.valueOf(category),
    priority = Priority.valueOf(priority),
    period = periodDays?.let { Period.ofDays(it) },
    startTime = LocalDateTime.parse(startTime),
    endTime = endTime?.let { LocalDateTime.parse(it) }
)
