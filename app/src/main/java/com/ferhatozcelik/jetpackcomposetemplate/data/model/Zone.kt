package com.ferhatozcelik.jetpackcomposetemplate.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Zone(
    var latitude: Double,
    var longitude: Double,
    var radius: Double
) : Parcelable
