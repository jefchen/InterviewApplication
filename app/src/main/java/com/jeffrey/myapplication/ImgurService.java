package com.jeffrey.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jefchen on 9/17/17.
 */

public interface ImgurService {

    @GET("gallery/search/top/month/{page}")
    Call<SearchResult> search(@Path("page") int page, @Query("q") String query);
}
