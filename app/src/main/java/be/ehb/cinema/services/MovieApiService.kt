package be.ehb.cinema.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Gebruikmaken van Retrofit voor requests uit te voeren naar de themoviedb api

// Info over Retrofit
// https://square.github.io/retrofit/

class MovieApiService {

    // De standaard url van de api request gaan definiëren
    companion object{
        private const val BASE_URL = "https://api.themoviedb.org"
        private var retrofit : Retrofit? = null

        fun getInstance() : Retrofit{
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}