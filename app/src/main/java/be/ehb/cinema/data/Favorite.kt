package be.ehb.cinema.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity voor favorieten
@Entity(tableName = "favorite_table")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val id_movie : String ?,
    val title : String ?,
    val poster : String ?,
    val overview : String ?,
    val release_date : String ?,
)