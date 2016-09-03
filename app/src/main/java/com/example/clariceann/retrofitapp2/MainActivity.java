package com.example.clariceann.retrofitapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // Make sure you've imported ButterKnife in your build.gradle file
    // compile 'com.jakewharton:butterknife:8.4.0'
    // apt 'com.jakewharton:butterknife-compiler:8.4.0'
    // http://jakewharton.github.io/butterknife/
    @BindView(R.id.username) EditText mUsername;
    @BindView(R.id.name) TextView mName;
    @BindView(R.id.location) TextView mLocation;
    @BindView(R.id.email) TextView mEmail;
    @BindView(R.id.status) TextView mStatus;
    @BindView(R.id.get_user) Button mGetUser;

    GithubApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Make sure you've imported this in your build.gradle file
        // compile 'com.squareup.retrofit2:retrofit:2.1.0'
        // compile 'com.squareup.retrofit2:converter-gson:2.1.0'
        // http://square.github.io/retrofit/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(GithubApi.class);
    }

    @OnClick(R.id.get_user) void connectToGithubApi(){
        if (mUsername.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
        } else {
            mStatus.setVisibility(View.VISIBLE);
            Call<User> call = api.getUser(mUsername.getText().toString());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()) {
                        User user = response.body();
                        mName.setText(user.getFullname());
                        mLocation.setText(user.getLocation());
                        mEmail.setText(user.getEmail());
                    } else {
                        Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                    mStatus.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // Do nothing
                }
            });
        }
    }

}
