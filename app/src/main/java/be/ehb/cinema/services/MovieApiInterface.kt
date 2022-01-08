package be.ehb.cinema.services

import be.ehb.cinema.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

// Interface methods voor request uit te voeren

interface MovieApiInterface {

    // Voor de films op te halen die nu gespeeld worden
    @GET("/3/movie/now_playing?api_key=e30e0f83e4fb9b94aad9eee3e8ec3efc")
    fun getNowPlayingMovieList() : Call<MovieResponse>

    // Voor de films op te halen die binnenkort gespeeld worden
    @GET("/3/movie/upcoming?api_key=e30e0f83e4fb9b94aad9eee3e8ec3efc")
    fun getUpcomingMovieList() : Call<MovieResponse>

}