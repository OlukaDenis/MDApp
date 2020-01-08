package com.systec.mdapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.systec.mdapp.R;
import com.systec.mdapp.model.Movie;
import com.systec.mdapp.view.interfaces.MovieitemClickListener;

import java.util.List;

import static com.systec.mdapp.utils.Constants.BACKDROP_URL_BASE_PATH;
import static com.systec.mdapp.utils.Constants.IMAGE_URL_BASE_PATH;

public class Movie_adapter extends RecyclerView.Adapter<Movie_adapter.MyViewHolder> {
    Context context;
    List<Movie> mMovies;
    MovieitemClickListener mMovieitemClickListener;

    public Movie_adapter(Context context, List<Movie> Movies,MovieitemClickListener listener) {
        this.context = context;
        this.mMovies = Movies;
        mMovieitemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mMovieitemClickListener = (MovieitemClickListener) context;
    }

    @Override
    public void onBindViewHolder(@NonNull Movie_adapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(mMovies.get(position).getTitle());


        String image_url = IMAGE_URL_BASE_PATH + mMovies.get(position).getPosterPath();
        String backdrop_url = BACKDROP_URL_BASE_PATH + mMovies.get(position).getBackdropPath();

        Picasso.get()
                .load(backdrop_url)
                .placeholder(R.drawable.ic_movie_default)
                .error(R.drawable.ic_movie_default)
                .into(holder.imgMovies);

    }


    @Override
    public int getItemCount() {
        return mMovies.size();
    }
    public  class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private ImageView imgMovies;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_title);
            imgMovies = itemView.findViewById(R.id.item_movie_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mMovieitemClickListener.inflateMovieDetailsFragment(view,mMovies.get(getAdapterPosition()));
//                    Toast.makeText(context, mMovies.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
