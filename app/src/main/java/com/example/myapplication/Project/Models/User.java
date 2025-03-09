package com.example.myapplication.Project.Models;

public class User {
    private String GoogleId;
    private String Email;
    private String DisplayName;
    private String PhotoUrl;

    public User(String googleId, String email, String displayName, String photoUrl) {
        this.GoogleId = googleId;
        this.Email = email;
        this.DisplayName = displayName;
        this.PhotoUrl = photoUrl;
    }
}

