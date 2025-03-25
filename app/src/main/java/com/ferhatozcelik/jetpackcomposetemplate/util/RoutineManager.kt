package com.ferhatozcelik.jetpackcomposetemplate.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class RoutineManager @Inject constructor(
    private val repository: AppRepository
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getRoutines(): List<Routine> = withContext(Dispatchers.IO) {
        repository.getRoutines()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getRoutine(id: UUID): Routine? = withContext(Dispatchers.IO) {
        repository.getRoutineById(id)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun addOrUpdateRoutine(routine: Routine) = withContext(Dispatchers.IO) {
        repository.addOrUpdateRoutine(routine)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun deleteRoutine(routine: Routine) = withContext(Dispatchers.IO) {
        repository.deleteRoutine(routine)
    }
}
