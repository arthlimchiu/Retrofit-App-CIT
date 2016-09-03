package com.example.clariceann.retrofitapp2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by clariceann on 03/09/16.
 */
public interface GithubApi {

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);
}
