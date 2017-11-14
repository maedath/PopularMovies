package com.example.lsitec218maeda.popularmovies;

        import android.content.Context;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;

        import com.example.lsitec218maeda.popularmovies.data.Movie;
        import com.squareup.picasso.Picasso;

/**
 * Created by lsitec218.maeda on 10/11/17.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Movie[] mMovies;
    private String[] posterURLs;

    public ImageAdapter(MainActivity c, String[] posterURLs) {
        mContext = c;
        this.posterURLs=posterURLs;
    }

    public int getCount() {
        return posterURLs.length;
    }

    public Movie getItem(int position) {
        return  mMovies[position];
    }

    public void setMoviesData(Movie[] movies){
        mMovies=movies;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setAdjustViewBounds(true);
        Picasso.with(imageView.getContext()).load(posterURLs[position]).into(imageView);
        return imageView;
    }
}