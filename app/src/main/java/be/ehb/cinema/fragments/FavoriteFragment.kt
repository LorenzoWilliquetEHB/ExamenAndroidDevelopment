package be.ehb.cinema.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.ehb.cinema.FavoriteAdapter
import be.ehb.cinema.R
import be.ehb.cinema.data.FavoriteViewModel


// FavoriteFragment is een fragment die favorieten films weergeeft die van de db Room komt

class FavoriteFragment : Fragment() {

    private lateinit var mFavoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        val adapter = FavoriteAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_movies_favorites)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // Observed of er eventueel data is aangepast in de db
        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        mFavoriteViewModel.allFavorite.observe(viewLifecycleOwner, Observer { favorite ->
            adapter.setData(favorite)
        })

        // Inflate the layout for this fragment
        return view
    }
}