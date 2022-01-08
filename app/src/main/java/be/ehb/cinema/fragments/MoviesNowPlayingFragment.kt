package be.ehb.cinema.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.ehb.cinema.MovieAdapter
import be.ehb.cinema.R
import be.ehb.cinema.models.Movie
import be.ehb.cinema.models.MovieResponse
import be.ehb.cinema.services.MovieApiInterface
import be.ehb.cinema.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// MoviesNowPlayingFragment is een fragment die de films weergeeft die nu gespeeld worden in de cinema

class MoviesNowPlayingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(activity)
            getNowPlayingMovieData {
                movies : List<Movie> ->
                adapter = MovieAdapter(movies)
            }
        }
    }

    // Methode die de films ophaalt die nu gespeeld worden ( is 1 pagina van films)
    private fun getNowPlayingMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getNowPlayingMovieList().enqueue(object : Callback<MovieResponse>{

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
        })
    }
}