package be.ehb.cinema.data

import androidx.lifecycle.LiveData

class FavoriteRepository(private val favoriteDAO: FavoriteDAO) {

    val allFavorites: LiveData<List<Favorite>> = favoriteDAO.allFavorites()

    suspend fun addFavorite(favorite:Favorite){
        favoriteDAO.addFavorite(favorite)
    }

    fun deleteFavorite(favorite_id_movie: String){
        return favoriteDAO.deleteFavorite(favorite_id_movie)
    }

    fun check(favorite_id_movie: String): Boolean{
        return favoriteDAO.check(favorite_id_movie)
    }
}