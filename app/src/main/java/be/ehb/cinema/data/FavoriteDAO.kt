package be.ehb.cinema.data

import androidx.lifecycle.LiveData
import androidx.room.*

// Bevat de methoden die gebruikt worden om toegang te hebben tot de db

@Dao
interface FavoriteDAO {

    // Toevoegen van een favoriet
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite:Favorite)

    // Alle favorieten ophalen
    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun allFavorites(): LiveData<List<Favorite>>

    // Om te controleren dat we geen dubbele data gaan toevoegen
    @Query("SELECT id_movie FROM favorite_table WHERE id_movie == :favorite_id_movie")
    fun check(favorite_id_movie: String): Boolean

    // Delete van een favoriet
    @Query("DELETE FROM favorite_table WHERE id_movie == :favorite_id_movie")
    fun deleteFavorite(favorite_id_movie: String)
}