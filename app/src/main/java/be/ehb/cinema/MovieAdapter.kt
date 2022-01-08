package be.ehb.cinema

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.ehb.cinema.models.Movie
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_filmitem,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        val currentItem = movies[position]
        holder.itemView.findViewById<TextView>(R.id.movie_title).text = currentItem.title
        holder.itemView.findViewById<TextView>(R.id.movie_release_date).text = currentItem.release_date
        Glide.with(holder.itemView).load(IMAGE_URL + currentItem.poster).into(holder.itemView.findViewById(R.id.movie_poster))
        holder.itemView.setOnClickListener {
            //Intent to detail
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString("id",currentItem.id)
            bundle.putString("title",currentItem.title)
            bundle.putString("poster",currentItem.poster)
            bundle.putString("overview",currentItem.overview)
            bundle.putString("release_date",currentItem.release_date)
            intent.putExtras(bundle)
            holder.itemView.context.startActivity(intent)
        }
    }
}