package com.systec.mdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.systec.mdapp.R;
import com.systec.mdapp.model.movie;

import java.util.List;

public class Movie_adapter extends RecyclerView.Adapter<Movie_adapter.MyViewHolder> {
    Context context;
    List<movie> mMovies;

    public Movie_adapter(Context context, List<movie> movies) {
        this.context = context;
        this.mMovies = movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Movie_adapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(mMovies.get(position).getTitle());
        holder.imgMovies.setImageResource(mMovies.get(position).getThumbnail());

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

        }
    }
}
