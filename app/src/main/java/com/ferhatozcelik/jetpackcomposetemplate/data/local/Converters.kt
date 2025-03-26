package com.ferhatozcelik.jetpackcomposetemplate.data.local

import androidx.room.TypeConverter
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Zone
import com.google.gson.Gson
import java.util.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date {
        return value?.let { Date(it) } ?: Date(System.currentTimeMillis())
    }

    @TypeConverter
    fun toTimestamp(value: Date?): Long {
        return value?.let { value.time } ?: System.currentTimeMillis()
    }

    @TypeConverter
    fun fromString(value: String?): Zone {
        return value?.let { Gson().fromJson<Zone>(it, Zone::class.java) } ?: Zone(0.0, 0.0, 1.0)
    }

    @TypeConverter
    fun toString(value: Zone?): String {
        return value?.let { Gson().toJson(it) } ?: toString(Zone(0.0, 0.0, 1.0))
    }
}
