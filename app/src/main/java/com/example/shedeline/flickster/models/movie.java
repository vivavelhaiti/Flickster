package com.example.shedeline.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shedeline on 7/20/2017.
 */

public class movie {
  public String getPosterPath() {
    return  String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public String getOverview() {
    return overview;
  }

  String posterPath;
  String originalTitle;
  String overview;

  public movie (JSONObject jsonObject) throws JSONException{
    this.posterPath = jsonObject.getString("poster_path");
    this.originalTitle = jsonObject.getString("original_title");
    this.overview = jsonObject.getString("overview");
  }


  public static ArrayList <movie> fromJSONArray (JSONArray array){
    ArrayList <movie> results = new ArrayList<>();
 for (int x = 0; x< array.length(); x ++){
   try {
     results.add(new movie(array.getJSONObject(x)));
   } catch (JSONException e) {
     e.printStackTrace();
   }
 }
   return results;
  }
}
