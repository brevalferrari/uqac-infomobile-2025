package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.ExampleRepository
import com.ferhatozcelik.jetpackcomposetemplate.util.deleteRoutineFromList
import com.ferhatozcelik.jetpackcomposetemplate.util.getRoutines
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(private val exampleRepository: ExampleRepository) :
    ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName

    private val _routines: MutableState<List<Routine>> = mutableStateOf(emptyList())
    var routines: State<List<Routine>> = _routines

    init {
        _routines.value = loadRoutines()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadRoutines(): List<Routine> {
        return getRoutines()
    }

    private fun deleteRoutine(routine: Routine) {
        _routines.value = _routines.value.filter { it != routine }
        deleteRoutineFromList(routine)
    }
}
