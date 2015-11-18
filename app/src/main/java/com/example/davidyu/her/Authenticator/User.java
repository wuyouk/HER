package com.example.davidyu.her.Authenticator;

/**
 * Class to hold user information
 */
public class User {

    String name, username, password, token;

    public User(String name, String username, String password, String token){
        this.name = name;
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public User(){

    }

    public User(String username, String password, String token){
        this.username = username;
        this.password = password;
        this.name = "name";
        this.token = token;
    }

}
