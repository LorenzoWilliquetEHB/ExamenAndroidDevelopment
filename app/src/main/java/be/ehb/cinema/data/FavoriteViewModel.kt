package be.ehb.cinema.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Is het communicatie punt tussen de Repository en de UI

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    val allFavorite: LiveData<List<Favorite>>
    private val repository: FavoriteRepository

    init {
        val favoriteDao = FavoriteDatabase.getDatabase(application).favoriteDao()
        repository = FavoriteRepository(favoriteDao)
        allFavorite = repository.allFavorites
    }

    fun addFavorite(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite_id_movie: String){
        return repository.deleteFavorite(favorite_id_movie)
    }

    fun check(favorite_id_movie: String): Boolean {
      return repository.check(favorite_id_movie)
    }
}