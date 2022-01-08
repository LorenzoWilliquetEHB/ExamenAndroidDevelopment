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

// MoviesUpcomingFragment is een frament die de films weergeeft die nog moeten uitkomen

class MoviesUpcomingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view_movies_upcoming).apply {
            layoutManager = LinearLayoutManager(activity)
            getUpcomingMovieData {
                    movies : List<Movie> ->
                    adapter = MovieAdapter(movies)
            }
        }
    }

    // Methode die de films ophaalt die binnenkort gespeeld worden ( is 1 pagina van films)
    private fun getUpcomingMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getUpcomingMovieList().enqueue(object : Callback<MovieResponse> {

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
        })
    }

}