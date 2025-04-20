package com.ferhatozcelik.jetpackcomposetemplate.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.ferhatozcelik.jetpackcomposetemplate.data.dao.RoutineDao
import com.ferhatozcelik.jetpackcomposetemplate.data.mapper.toModel
import com.ferhatozcelik.jetpackcomposetemplate.data.mapper.toEntity
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val routineDao: RoutineDao
) {

    suspend fun getRoutines(): List<Routine> {
        return routineDao.getAll().map { it.toModel() }
    }

    suspend fun getRoutineById(id: UUID): Routine? {
        return routineDao.getRoutineById(id)?.toModel()
    }

    suspend fun addOrUpdateRoutine(routine: Routine) {
        routineDao.insertRoutine(routine.toEntity())
    }

    suspend fun deleteRoutine(routine: Routine) {
        routineDao.deleteRoutine(routine.toEntity())
    }
}
