package com.ferhatozcelik.jetpackcomposetemplate.data.dao
import androidx.room.*
import com.ferhatozcelik.jetpackcomposetemplate.data.entity.RoutineEntity
import java.util.UUID

@Dao
interface RoutineDao {

    @Query("SELECT * FROM routine_table")
    suspend fun getAll(): List<RoutineEntity>

    @Query("SELECT * FROM routine_table WHERE id = :id")
    suspend fun getRoutineById(id: UUID): RoutineEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutine(routine: RoutineEntity)

    @Delete
    suspend fun deleteRoutine(routine: RoutineEntity)
}