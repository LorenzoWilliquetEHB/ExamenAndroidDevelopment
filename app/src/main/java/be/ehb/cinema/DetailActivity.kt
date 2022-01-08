package be.ehb.cinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import be.ehb.cinema.data.Favorite
import be.ehb.cinema.data.FavoriteViewModel
import com.bumptech.glide.Glide

// DetailActivity geeft de details weer van een film

class DetailActivity : AppCompatActivity() {

    private lateinit var mFavoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

        // Extras die meegegeven zijn naar deze activity
        val bundle = intent.extras

        // Glide is een snelle en efficiënte open source media management en image loading framework
        // het handelt image loading/caching
        Glide.with(this).load(IMAGE_URL + bundle!!.getString("poster")).into(findViewById<ImageView>(R.id.movie_poster_detail))
        findViewById<TextView>(R.id.movie_title_detail).text = bundle!!.getString("title")
        findViewById<TextView>(R.id.movie_overview_detail).text = bundle!!.getString("overview")

        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        // Controleren of de movie in de favorite db staat
        val movieExistInDb = mFavoriteViewModel.check(bundle.getString("id").toString())
        val movieFavoriteDetail = findViewById<ImageView>(R.id.movie_favorite_detail)

        if (movieExistInDb){
            movieFavoriteDetail.setImageResource(R.drawable.ic_favorietrood)
        }
        else{
            movieFavoriteDetail.setImageResource(R.drawable.ic_favoriet)
        }

        // Als de waarde in de db zit dan gaan we die verwijderen anders gaan wie toevoegen aan de db
        findViewById<ImageView>(R.id.movie_favorite_detail).setOnClickListener {
            if (movieExistInDb){
                movieFavoriteDetail.setImageResource(R.drawable.ic_favoriet)
                deleteData()
            }
            else{
                movieFavoriteDetail.setImageResource(R.drawable.ic_favorietrood)
                insertDataToDatabase()
            }
        }
    }

    // Methode voor de movie in de favorieten op te slaan
    private fun insertDataToDatabase() {

        val bundle = intent.extras
        val id_movie = bundle!!.getString("id")
        val title_movie = bundle!!.getString("title")
        val poster_movie = bundle!!.getString("poster")
        val overview_movie = bundle!!.getString("overview")
        val release_date_movie = bundle!!.getString("release_date")

        val favorite = Favorite(0,id_movie,title_movie,poster_movie,overview_movie,release_date_movie)

        mFavoriteViewModel.addFavorite(favorite)
    }

    // Methode voor de movie te delete uit de favorieten lijst
    private fun deleteData(){

        val bundle = intent.extras
        val id_movie = bundle!!.getString("id")

        if (id_movie != null) {
            mFavoriteViewModel.deleteFavorite(id_movie)
        }
    }
}