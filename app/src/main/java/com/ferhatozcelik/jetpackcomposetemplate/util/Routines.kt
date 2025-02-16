package com.ferhatozcelik.jetpackcomposetemplate.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Category
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Priority
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import java.time.LocalDateTime
import java.time.Period
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O) // Period.ofDays() et LocalDateTime.parse() sont trop récentes on dirait
private val routines: MutableList<Routine> = mutableListOf(
    Routine(
        UUID.randomUUID(),
        "Brosser les dents",
        category = Category.Health,
        priority = Priority.High,
        period = Period.ofDays(1),
        startTime = LocalDateTime.parse("2018-12-30T19:34:50.63")
    ),
    Routine(
        UUID.randomUUID(),
        "Faire les courses",
        category = Category.Health,
        priority = Priority.Medium,
        endTime = LocalDateTime.MAX,
        description = """
            - PQ
            - papier cuisson
            - papier alu
            - papier A4
        """.trimIndent()
    ),
    Routine(
        UUID.randomUUID(),
        "Séance de Méditation",
        description = "pour baisser mon karma",
        priority = Priority.Low,
        category = Category.Work
    )
)

@RequiresApi(Build.VERSION_CODES.O)
fun getRoutines(): List<Routine> {
    return routines
}

@RequiresApi(Build.VERSION_CODES.O)
fun addOrUpdateRoutine(routine: Routine) {
    routines.find { it.id == routine.id }?.let {
        routines.remove(it)
    }
    routines.add(routine)
}

@RequiresApi(Build.VERSION_CODES.O)
fun deleteRoutineFromList(routine: Routine) {
    routines.remove(routine)
}
