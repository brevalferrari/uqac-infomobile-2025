package com.ferhatozcelik.jetpackcomposetemplate.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Zone(
    val latitude: Double,
    val longitude: Double,
    val radius: Double
) : Parcelable
