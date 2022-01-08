package be.ehb.cinema.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// Data klasse Movie

@Parcelize
data class Movie (

    @SerializedName("id")
    val id : String ?,

    @SerializedName("title")
    val title : String ?,

    @SerializedName("poster_path")
    val poster : String ?,

    @SerializedName("overview")
    val overview : String ?,

    @SerializedName("release_date")
    val release_date : String ?,

        ) : Parcelable{
            constructor() : this("","","","","")
        }