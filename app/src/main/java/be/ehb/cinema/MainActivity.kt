package be.ehb.cinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import be.ehb.cinema.fragments.FavoriteFragment
import be.ehb.cinema.fragments.MoviesNowPlayingFragment
import be.ehb.cinema.fragments.MoviesUpcomingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

// MainActivity waarbij het huidige fragment == de films die nu gespeeld worden

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moviesNowPlayingFragment = MoviesNowPlayingFragment()
        val favoriteFragment = FavoriteFragment()
        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        makeCurrentFragment(moviesNowPlayingFragment)

        // Linken van de knoppen aan de fragments
        bottom_navigation.setOnNavigationItemSelectedListener{
            when (it.itemId){
                R.id.ic_films -> makeCurrentFragment(moviesNowPlayingFragment)
                R.id.ic_favorieten -> makeCurrentFragment(favoriteFragment)
            }
            true
        }
    }

    // Methode die het binnenkomend fragment als huidige fragment zet
    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
    }

    // Options menu aan de HeaderBar toevoegen
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options,menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Het geselecteerde item tonen (Nu gespeeld of Binnenkort)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val moviesNowPlayingFragment = MoviesNowPlayingFragment()
        val moviesUpcomingFragment = MoviesUpcomingFragment()

        when (item.itemId){
                R.id.options_nu_gespeeld -> makeCurrentFragment(moviesNowPlayingFragment)
                R.id.options_binnenkort -> makeCurrentFragment(moviesUpcomingFragment)
        }
        true
        return super.onOptionsItemSelected(item)
    }
}