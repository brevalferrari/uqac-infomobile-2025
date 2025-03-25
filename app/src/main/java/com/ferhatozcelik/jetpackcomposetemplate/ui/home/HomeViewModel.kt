package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import com.ferhatozcelik.jetpackcomposetemplate.util.RoutineManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val routineManager: RoutineManager
) : ViewModel() {

    private val _routines: MutableState<List<Routine>> = mutableStateOf(emptyList())
    val routines: State<List<Routine>> = _routines

    init {
        loadRoutines()
    }

    private fun loadRoutines() {
        viewModelScope.launch {
            _routines.value = routineManager.getRoutines()
        }
    }

    fun deleteRoutine(routine: Routine) {
        viewModelScope.launch {
            routineManager.deleteRoutine(routine)
            _routines.value = _routines.value.filter { it.id != routine.id }
        }
    }
}
