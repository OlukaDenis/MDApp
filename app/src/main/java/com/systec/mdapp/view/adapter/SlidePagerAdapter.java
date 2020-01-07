package com.systec.mdapp.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.systec.mdapp.R;
import com.systec.mdapp.model.Slide;

import java.util.List;

import static com.systec.mdapp.utils.Constants.BACKDROP_URL_BASE_PATH;
import static com.systec.mdapp.utils.Constants.IMAGE_URL_BASE_PATH;

public class SlidePagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<Slide> mList;
    private static final String TAG = "SlidePagerAdapter";

    public SlidePagerAdapter(Context context, List<Slide> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        Log.i(TAG, "instantiateItem: "+mList.size());
        if (mList.size() > 5){
            return 5;
        } else {
            return mList.size();
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_item, null);
        ImageView slideImg = slideLayout.findViewById(R.id.slide_img);
        ImageView shadow = slideLayout.findViewById(R.id.imageView3);
//        FloatingActionButton play = slideLayout.findViewById(R.id);
        TextView slideText = slideLayout.findViewById(R.id.slide_title);

//        String image_url = IMAGE_URL_BASE_PATH + mList.get(position).getPosterPath();
        String backdrop_url = BACKDROP_URL_BASE_PATH + mList.get(position).getBackdropPath();

        Picasso.get()
                .load(backdrop_url)
                .placeholder(R.drawable.ic_movie_default)
                .error(R.drawable.ic_movie_default)
                .into(slideImg);
        slideText.setText(mList.get(position).getTitle());
        container.addView(slideLayout);
        return slideLayout;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}
