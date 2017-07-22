package com.example.shedeline.flickster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.shedeline.flickster.adapters.MovieArrayAdapter;
import com.example.shedeline.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class Movie_activity extends AppCompatActivity {
  ArrayList<Movie> movie;
  MovieArrayAdapter movieAdapter;
  ListView lvItems;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_activity);

    lvItems = (ListView) findViewById(R.id.LvMovies);
    movie= new ArrayList<>();
    movieAdapter = new MovieArrayAdapter(this, movie);
    lvItems.setAdapter(movieAdapter);

    String url=  "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";



    AsyncHttpClient client = new AsyncHttpClient();

    client.get(url,new JsonHttpResponseHandler(){

      @Override
      public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        JSONArray movieJsonResults = null;
        try {
          movieJsonResults= response.getJSONArray("results");
          movie.addAll(Movie.fromJSONArray(movieJsonResults));
          movieAdapter.notifyDataSetChanged();
          Log.d("DEBUG", movie.toString());

        } catch (JSONException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
      }
    });
  }
}

