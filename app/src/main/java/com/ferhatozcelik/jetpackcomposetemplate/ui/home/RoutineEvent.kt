package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine

sealed class RoutineEvent {
    data class Delete(val routine: Routine) : RoutineEvent()
}
