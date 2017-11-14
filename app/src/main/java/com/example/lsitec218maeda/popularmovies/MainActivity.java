package com.example.lsitec218maeda.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.lsitec218maeda.popularmovies.data.Movie;
import com.example.lsitec218maeda.popularmovies.utilities.JsonUtils;
import com.example.lsitec218maeda.popularmovies.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String POPULAR_MOVIES = "popular";
    private static final String TOP_RATED = "top_rated";
    private GridView mGridView;
    private Context mContext;
    private ImageAdapter mImageAdapter;
    private String mUserPreference = POPULAR_MOVIES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext =this;
        mGridView = findViewById(R.id.gridview);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Class destinationClass= DetailActivity.class;
                Intent intentToStartDetailActivity = new Intent(mContext, destinationClass);
                //send Movie instance to DetailActivity. It is loaded as an offline data
                intentToStartDetailActivity.putExtra("Movies", mImageAdapter.getItem(position));
                startActivity(intentToStartDetailActivity);
            }
        });
        //load data from default user preference, if has internet connection
        loadData();
    }

    private void loadData(){
        if(isOnline()) {
            new FetchPopularMoviesTask().execute(mUserPreference);
        }
        else{
            Toast.makeText(this, "Please check Internet connection!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public class FetchPopularMoviesTask extends AsyncTask<String, Void, Movie[]> {
        @Override
        protected Movie[] doInBackground(String... params) {
            URL popularMoviesUrl = NetworkUtils.buildUrl(params[0]);
            String jsonMoviesResponse;
            Movie[] movies;
            try {
                jsonMoviesResponse = NetworkUtils.getResponseFromHttpUrl(popularMoviesUrl);
                movies = JsonUtils
                        .getStringsFromJson(MainActivity.this, jsonMoviesResponse);
                return movies;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] popularMovies) {
            String posterURLs[];
            URL moviesPosterUrl;
            if (popularMovies != null) {
                posterURLs= new String[popularMovies.length];
                for(int i=0; i< popularMovies.length; i++){
                    moviesPosterUrl = NetworkUtils.buildMoviePosterUrl("w185", popularMovies[i].getPosterPath());
                    posterURLs[i]= moviesPosterUrl.toString();
                    Log.v("onPostExecute", "Built URI " + posterURLs[i]);
                }
                mImageAdapter=new ImageAdapter((MainActivity) mContext,posterURLs);
                mImageAdapter.setMoviesData(popularMovies);
                mGridView.setAdapter(mImageAdapter);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort_most_popular) {
            mUserPreference =POPULAR_MOVIES;
            loadData();
            return true;
        }
        if (id == R.id.action_sort_top_rated) {
            mUserPreference =TOP_RATED;
            loadData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
