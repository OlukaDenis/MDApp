package com.systec.mdapp.view.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.systec.mdapp.R;
import com.systec.mdapp.model.Movie;
import com.systec.mdapp.viewmodel.MovieDetailsViewModel;

import static com.systec.mdapp.utils.Constants.IMAGE_URL_BASE_PATH;
import static com.systec.mdapp.utils.Constants.BACKDROP_URL_BASE_PATH;

public class MovieDetailsFragment extends Fragment {

    private MovieDetailsViewModel mViewModel;
    private Movie mMovie;
    private static final String TAG = "MovieDetailsFragment";
    private View mView;
    private TextView tvTitle, tvPlot;
    private ImageView imgCover, imgPoster;
    private String mImage_url;
    private String mImgae_cover;

    public static MovieDetailsFragment newInstance() {
        return new MovieDetailsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null){
            mMovie = bundle.getParcelable(getString(R.string.intent_movie_bundle));
            Log.d(TAG, "onCreate: Got Bundle " + mMovie.getTitle());
            Toast.makeText(getContext(), mMovie.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        initViews();
        inflateViews();

        return mView;
    }

    private void inflateViews() {
        mImage_url = IMAGE_URL_BASE_PATH + mMovie.getPosterPath();
        mImgae_cover = BACKDROP_URL_BASE_PATH + mMovie.getBackdropPath();
        tvTitle.setText(mMovie.getTitle());
        tvPlot.setText(mMovie.getOverview());

        Picasso.get()
                .load(mImage_url)
                .placeholder(R.drawable.ic_movie_default)
                .error(R.drawable.ic_movie_default)
                .into(imgPoster);

        Picasso.get()
                .load(mImgae_cover)
                .placeholder(R.drawable.ic_movie_default)
                .error(R.drawable.ic_movie_default)
                .into(imgCover);
    }

    private void initViews() {
        tvTitle = mView.findViewById(R.id.details_movie_title);
        tvPlot = mView.findViewById(R.id.details_movie_disc);
        imgCover = mView.findViewById(R.id.detail_movie_cover);
        imgPoster = mView.findViewById(R.id.detail_movie_img);


    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        // TODO: Use the ViewModel


    }

}
