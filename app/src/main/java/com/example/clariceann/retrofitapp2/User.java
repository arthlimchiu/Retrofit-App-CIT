package com.example.clariceann.retrofitapp2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by clariceann on 03/09/16.
 */
public class User {

    @SerializedName("name")
    @Expose
    public String fullname;

    @SerializedName("location")
    @Expose
    public String location;

    @SerializedName("email")
    @Expose
    public String email;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
