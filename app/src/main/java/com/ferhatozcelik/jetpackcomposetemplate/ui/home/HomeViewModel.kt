package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import com.ferhatozcelik.jetpackcomposetemplate.data.local.AppDatabase
import com.ferhatozcelik.jetpackcomposetemplate.util.RoutineManager
import com.ferhatozcelik.jetpackcomposetemplate.util.notifications.RoutineAlarmScheduler
import com.ferhatozcelik.jetpackcomposetemplate.util.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val routineManager: RoutineManager,
    private val routineAlarmScheduler: RoutineAlarmScheduler,
) : ViewModel() {

	val uiImportExportLiveData = SingleLiveData<Pair<AppDatabase, Int>>()
    private val _routines: MutableState<List<Routine>> = mutableStateOf(emptyList())
    val routines: State<List<Routine>> = _routines

    init {
        loadRoutines()
    }

    private fun loadRoutines() {
        viewModelScope.launch {
            _routines.value = routineManager.getRoutines()
            _routines.value.forEach {
                // Android API docs seem to say that there can't be duplicates anyway
                // routineAlarmScheduler.cancel(it)
                routineAlarmScheduler.schedule(it)
            }
        }
    }

    fun deleteRoutine(routine: Routine) {
        viewModelScope.launch {
            routineManager.deleteRoutine(routine)
            routineAlarmScheduler.cancel(routine)
            _routines.value = _routines.value.filter { it.id != routine.id }
        }
    }

    fun importClicked() {
    	uiImportExportLiveData.value = database to 1
    }

    fun exportClicked() {
    	uiImportExportLiveData.value = database to 2
    }
}
