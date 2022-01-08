package be.ehb.cinema.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// Data klasse MovieResponse voor de results van die api voor films die nu gespeeld worden en die binnenkort gespeeld worden

@Parcelize
data class MovieResponse(
    @SerializedName("results")
    val movies : List<Movie>
) : Parcelable {
    constructor() : this(mutableListOf())
}