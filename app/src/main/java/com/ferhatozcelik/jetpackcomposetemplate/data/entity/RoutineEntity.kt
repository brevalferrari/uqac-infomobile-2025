package com.ferhatozcelik.jetpackcomposetemplate.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity(tableName = "routine_table")
data class RoutineEntity (
    @PrimaryKey(autoGenerate = false) // UUID déjà unique, pas besoin d'auto-génération
    val id: UUID,
    val name: String,
    val description: String,
    val category: String,
    val priority: String,
    val periodDays: Int?,
    val startTime: String,
    val endTime: String?
) : Parcelable